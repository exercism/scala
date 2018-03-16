object AtbashCipher {
  def decode(s: String) = {
    s.toCharArray.map(encodeChar).mkString
  }

  def encode(s: String) = {
    decode(s).grouped(5).mkString(" ")
  }

  private def encodeChar(c: Char) = c match {
    case d if d.isDigit => d.toString
    case s if s.isLetter => ('a' + ('z' - c.toLower)).toChar.toString
    case _ => ""
  }
}