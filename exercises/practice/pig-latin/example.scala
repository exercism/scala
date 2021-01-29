object PigLatin {
  private val vowelRegex = "(^|\\s+)(a|e|i|o|u|yt|xr)(\\w+)".r
  private val consonantRegex = "(^|\\s+)(?<consonant>ch|qu|thr|th|sch|yt|rh|\\wqu|\\w)(?<rest>\\w+)".r
  private val vowelReplacement = "$1$2$3ay"
  private val consonantReplacement = "$1$3$2ay"

  def translate(phrase: String): String =
    vowelRegex.findFirstIn(phrase) match {
      case Some(x) => vowelRegex.replaceAllIn(phrase, vowelReplacement)
      case None => consonantRegex.replaceAllIn(phrase, consonantReplacement)
    }
}
