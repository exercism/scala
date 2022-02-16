object RunLengthEncoding {
  type Plain = String
  type Encoded = String

  def encode(str: Plain): Encoded = {
    def encodeGroup(xs: Seq[Char]): Seq[Char] =
      if (xs.length > 1) s"${xs.length}${xs.head}"
      else xs.mkString

    (splitByEquals(str) flatMap encodeGroup).mkString
  }

  def decode(str: Encoded): Plain = {
    val nextChar: ((Int, Seq[Char]), Char) => (Int, Seq[Char]) = {
      case ((n, xs), x) =>
        if (x.isDigit) (n * 10 + x.asDigit, xs)
        else if (n == 0) (0, x +: xs)
        else (0, Seq.fill(n)(x) ++ xs)
    }

    val result = str.foldLeft((0, Seq.empty[Char]))(nextChar)
    result._2.reverse.mkString
  }

  private def splitByEquals[T](xs: Seq[T]): Seq[Seq[T]] =
    xs match {
      case Seq() => Seq()
      case x +: xss =>
        val fs = xs takeWhile (_ == x)
        fs +: splitByEquals(xs drop fs.length)
    }
}
