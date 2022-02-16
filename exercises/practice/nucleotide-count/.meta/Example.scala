import DNA._

class DNA(dna: String) {

  def count(char: Char): OrError[Int] =
    for {
      nucleotide <- toNucleotide(char)
      counts <- nucleotideCounts
    } yield counts(nucleotide)

  lazy val nucleotideCounts: OrError[NucleotideCounts] = {
    val zeroCounts: Either[String, NucleotideCounts] =
      Right(EmptyNucleotideCounts)

    dna.foldLeft(zeroCounts) { case (nucleotideCounts, char) =>
      for {
        nucleotide <- toNucleotide(char)
        counts <- nucleotideCounts
      } yield counts updated (nucleotide, counts.getOrElse(nucleotide, 0) + 1)
    }
  }

  private def toNucleotide(char: Char): OrError[Nucleotide] =
    if (DnaNucleotides.contains(char)) Right(char)
    else Left(s"invalid nucleotide '$char'")
}

object DNA {
  type Nucleotide = Char
  type NucleotideCounts = Map[Nucleotide,Int]
  type OrError[T] = Either[String, T]

  val DnaNucleotides: Set[Nucleotide] = Set('A', 'T', 'C', 'G')

  val EmptyNucleotideCounts: NucleotideCounts =
    (DnaNucleotides map (_ -> 0)).toMap
}
