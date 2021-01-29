object Hexadecimal {
  private val hexChars  = 'a' to 'f'

  def hexToInt(s: String): Int =
    s.foldLeft(Some(0): Option[Int]){case (acc, c) => accumulate(acc, c)}.getOrElse(0)

  private def accumulate(acc: Option[Int], c: Char) =
    if (c.isDigit || (hexChars contains c.toLower))
      acc.map(x => c.asDigit + (x * 16))
    else
      None
}
