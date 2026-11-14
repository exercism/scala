object RnaTranscription:
  private val complement = Map('G' -> 'C', 'C' -> 'G', 'T' -> 'A', 'A' -> 'U')

  // None when the strand holds anything but the four DNA nucleotides.
  def toRna(dna: String): Option[String] =
    Option.when(dna.forall(complement.contains))(dna.map(complement))
