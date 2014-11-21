class Binary(s: String) {
  val toDecimal: Int = s.foldLeft(0)((r, c) => r * 2 + convertChar(c))

  private def convertChar(c: Char): Int = if (c == '1') 1 else 0
}

object Binary {
  def apply(s: String) = new Binary(s)
}
