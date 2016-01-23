object Series {
  def digits(s: String): List[Int] = s.map(c => c.asDigit).toList

  def slices(n: Int, s: String): List[List[Int]] = {
    assert(n > 0, "slice length must be greater than 0")
    digits(s).tails.filter(xs => xs.length >= n).map(xs => xs.take(n)).toList
  }

  def largestProduct(n: Int, s: String): Option[Int] =
    slices(n, s) match {
      case Nil => None
      case xs => Some(xs.map(slice => slice.foldLeft(1)(_ * _)).max)
    }
}
