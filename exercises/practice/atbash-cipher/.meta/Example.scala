object AtbashCipher:
  def encode(s: String): String = decode(s).grouped(5).mkString(" ")

  // Decoding is the same substitution, without the groups of five.
  def decode(s: String): String = s.collect:
    case c if c.isDigit  => c
    case c if c.isLetter => ('a' + 'z' - c.toLower).toChar
