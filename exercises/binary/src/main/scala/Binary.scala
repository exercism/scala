object Binary {
  def apply(s: String) = new Binary(s)
}

class Binary(s: String) {
  def toDecimal: Int =
    s.foldLeft(Option(0))((accumOpt, char) => {
        for{
          accum <- accumOpt if char >= '0' && char <= '1'
        } yield (accum * 2) + (char - '0')
      }).getOrElse(0)
}