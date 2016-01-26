class Dna {

  val dnaToRna = Map(
    'G' -> 'C',
    'C' -> 'G',
    'T' -> 'A',
    'A' -> 'U'
  )

  def toRna(dna: String) = dna.map(dnaToRna)
}

object Dna {
  def apply() = new Dna()
}
