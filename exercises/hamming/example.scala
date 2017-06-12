class Hamming(strand1: String, strand2: String) {

  def distance =
    if (strand1.length == strand2.length) {
      val dist = commonPairs.count { case (a, b) => a != b }
      Some(dist)
    } else None

  private def commonPairs = strand1.zip(strand2)
}

object Hamming {
  def distance(strand1: String, strand2: String) =
    new Hamming(strand1, strand2).distance
}
