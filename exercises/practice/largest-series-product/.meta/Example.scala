object Series {
  private def digits(s: String): List[Int] = s.filter(_.isDigit).map(_.asDigit).toList

  private def slices(n: Int, digits: List[Int]): List[List[Int]] =
    digits.tails.filter(xs => xs.length >= n).map(_.take(n)).toList

  def largestProduct(n: Int, s: String): Option[Int] =
    if (n < 0)
      None
    else {
      val ds = digits(s)
      if (ds.length < s.length)
        None
      else {
        slices(n, ds) match {
          case Nil => None
          case xs => Some(xs.map(_.product).max)
        }
      }
    }
}
