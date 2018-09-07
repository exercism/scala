object RotationalCipher {
  private def rotate(c: Char, key: Int): Char = {
    if (!c.isLetter)
      return c

    val base = if (c.isUpper) 'A'.toInt else 'a'.toInt
    ((c.toInt - base + key) % 26 + base).toChar
  }

  def rotate(text: String, key: Int): String =
    text.map(rotate(_, key)).concat("")
}
