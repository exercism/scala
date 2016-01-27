class DNA(strand: String) {
  strand foreach validate

  lazy val nucleotideCounts = {
    Map('A' -> 0, 'T' -> 0, 'C' -> 0, 'G' -> 0) ++
    strand.toCharArray.groupBy(identity).mapValues(_.length)
  }

  private def validate(nucleotide: Char) =
    if (!DNA.isNucleotide(nucleotide)) throw new IllegalArgumentException
}

object DNA {
  val nucleotides = "ATCG"
  def isNucleotide(c: Char) = nucleotides.contains(c)
}
