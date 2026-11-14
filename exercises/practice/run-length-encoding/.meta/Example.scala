object RunLengthEncoding:
  // A run is a character followed by any repeats of it.
  private val Run = """(.)\1*""".r

  // An encoded run is an optional count followed by the character.
  private val EncodedRun = """(\d*)(\D)""".r

  def encode(str: String): String =
    Run.findAllIn(str).map(run => if run.length == 1 then run else s"${run.length}${run.head}").mkString

  def decode(str: String): String =
    EncodedRun.findAllMatchIn(str).map(m => m.group(2) * m.group(1).toIntOption.getOrElse(1)).mkString
