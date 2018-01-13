object Binary {
  def apply(s: String) = new Binary(s)
}

class Binary(s: String) {
  def toDecimal: Int = s.foldLeft(Option(0))((accum, char) => {
    accum.map(_ * 2 + (if(char == '1') 1 else 0))
  }).getOrElse(0)
}