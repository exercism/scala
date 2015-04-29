import scala.util.Random

case class Cipher(keyOpt: Option[String]) {
  require(keyOpt.isEmpty || keyOpt.get.matches("^[a-z]+$"))

  private val alphabet = ('a' to 'z').toVector
  private lazy val randomKey = Seq.fill(26)((Random.nextInt(25) + 'a').toChar).mkString

  val key = keyOpt.getOrElse(randomKey)

  def encode(s: String): String =
    (for (i <- 0 until s.length) yield encodeChar(s, i)).mkString

  private def encodeChar(plainText: String, index: Int) = {
    var alphaIdx = alphabet.indexOf(plainText(index)) + alphabet.indexOf(key(index))

    if (alphaIdx >= alphabet.length) alphaIdx -= alphabet.length

    alphabet(alphaIdx)
  }

  def decode(s: String): String =
    (for (i <- 0 until s.length) yield decodeChar(s, i)).mkString

  private def decodeChar(cipherText: String, index: Int) = {
    var alphaIdx = alphabet.indexOf(cipherText(index)) - alphabet.indexOf(key(index))

    if (alphaIdx < 0) alphaIdx += alphabet.length

    alphabet(alphaIdx)
  }
}
