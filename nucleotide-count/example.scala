class DNA(strand: String) {
  strand.foreach(validate)

  def count(nucleotide: Char) = {
    validate(nucleotide)
    strand.count(_ == nucleotide)
  }

  def nucleotideCounts = Map(
    'A' -> count('A'),
    'T' -> count('T'),
    'C' -> count('C'),
    'G' -> count('G')
  )

  private def validate(nucleotide: Char) =
    if (!isNucleotide(nucleotide))
      throw new IllegalArgumentException

  private def isNucleotide(nucleotide: Char) =
    DNA.nucleotides.contains(nucleotide)
}

object DNA {
  val nucleotides = "ATCG"
}
