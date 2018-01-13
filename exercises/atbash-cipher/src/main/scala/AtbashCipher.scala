object AtbashCipher {

  private val alphabet = 'a' to 'z'
  private val reverseAlphabet = alphabet.reverse
  private val encodeMap: Map[Char, Char] = alphabet.zip(reverseAlphabet).toMap
  private val decodeMap: Map[Char, Char] = reverseAlphabet.zip(alphabet).toMap

  def encode(input: String): String =
    input.map(char => encodeMap.get(char)).flatten.mkString
  def decode(input: String): String =
    input.map(char => decodeMap.get(char)).flatten.mkString
}
