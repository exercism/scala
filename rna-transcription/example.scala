class Dna {

  def toRna(dnaStrand: String) : String = dnaStrand.map(toRna)

  private def toRna(nucleotide: Char) : Char =
    nucleotide match {
      case 'G' => 'C'
      case 'C' => 'G'
      case 'T' => 'A'
      case 'A' => 'U'
      case _ => throw new IllegalArgumentException("Invalid nucleotide - " + nucleotide)
    }
}

object Dna {

  def apply() = new Dna
}
