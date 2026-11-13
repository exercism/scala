class DNA(strand: String):
  def nucleotideCounts: Either[String, Map[Char, Int]] =
    strand.find(!"ACGT".contains(_)) match
      case Some(invalid) => Left(s"invalid nucleotide '$invalid'")
      case None          => Right("ACGT".map(nucleotide => nucleotide -> strand.count(_ == nucleotide)).toMap)
