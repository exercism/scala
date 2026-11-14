object RotationalCipher:
  def rotate(text: String, key: Int): String = text.map(rotate(_, key))

  // Only the 26 ASCII letters rotate; everything else stays as it is.
  private def rotate(c: Char, key: Int): Char =
    if c >= 'a' && c <= 'z' then shift(c, 'a', key)
    else if c >= 'A' && c <= 'Z' then shift(c, 'A', key)
    else c

  private def shift(c: Char, base: Char, key: Int): Char =
    (base + Math.floorMod(c - base + key, 26)).toChar
