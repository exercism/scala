object RotationalCipher {
  private def rotate(c: Char, key: Int): Char = {
    def charPassedZ = (rotated: Char) => {
      val zChar = if (c.isUpper) 'Z' else 'z'
      rotated > zChar
    }

    if (!c.isLetter)
      return c

    val tmp = c + key
    if (charPassedZ(tmp.toChar))
      (tmp - 26).toChar
    else
      tmp.toChar
  }

  def rotate(text: String, key: Int): String =
    text.map(rotate(_, key)).concat("")
}
