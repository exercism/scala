object Hamming:
  def distance(strand1: String, strand2: String): Option[Int] =
    Option.when(strand1.length == strand2.length)(strand1.zip(strand2).count(_ != _))
