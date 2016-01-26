case class Atbash() {
  def encode(s: String): String =
    s.foldLeft("")((acc, c) => acc + substitute(c)).grouped(5).mkString(" ")

  private def substitute(c: Char) =
    if (c.isDigit) c.toString
    else if (c.isLetter) ('a' + ('z' - c.toLower)).toChar.toString
    else ""
}
