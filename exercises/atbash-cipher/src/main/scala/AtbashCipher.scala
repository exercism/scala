object AtbashCipher {

  private val alphabet = ('a' to 'z')
  private val reverseAlphabet = alphabet.reverse
  private val encodeMap: Map[Char, Char] = alphabet.zip(reverseAlphabet).toMap
  private val decodeMap: Map[Char, Char] = reverseAlphabet.zip(alphabet).toMap

  def encode(input: String): String = {
    encodeDecode(input, encodeMap)
      .foldLeft((0,""))((accum, char) => {
        if(accum._1 == 5)
          (1, accum._2 + " " + char)
        else
          (accum._1 + 1, accum._2 + char)
      })._2
  }
  def decode(input: String): String = {
    encodeDecode(input, decodeMap).mkString
  }

  def encodeDecode(input: String, charMap: Map[Char, Char]): Seq[Char] = {
    input.toLowerCase
      .map(char => if(char.isDigit) Some(char) else charMap.get(char))
      .flatten
  }
}
