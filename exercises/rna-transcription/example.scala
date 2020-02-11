object RnaTranscription {
  private type Strand = String
  private type Nucleotide = Char

  def toRna(dna: Strand): Option[Strand] =
    dna.foldRight (Option("")) { case (char, rna) =>
      for {
        rnaNucleotide <- toRnaNucleotide(char)
        rnaStrand <- rna
      } yield rnaNucleotide +: rnaStrand
  }

  private val DnaToRna =
    Map('G' -> 'C', 'C' -> 'G', 'T' -> 'A', 'A' -> 'U')

  private def toRnaNucleotide(char: Char): Option[Nucleotide] =
     DnaToRna get char
}
