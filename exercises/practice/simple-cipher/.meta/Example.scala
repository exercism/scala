import scala.util.Random

case class Cipher(keyOpt: Option[String]):
  require(keyOpt.forall(_.matches("[a-z]+")), "the key must be lowercase letters")

  // Without a key, a random one of 100 lowercase letters.
  val key: String = keyOpt.getOrElse(Seq.fill(100)(('a' + Random.nextInt(26)).toChar).mkString)

  def encode(text: String): String = shift(text, 1)

  def decode(text: String): String = shift(text, -1)

  // Each letter moves by the key's letter at the same position; the key repeats as often as needed.
  private def shift(text: String, direction: Int): String =
    text.zipWithIndex.map((letter, i) => rotate(letter, direction * (key(i % key.length) - 'a'))).mkString

  private def rotate(letter: Char, by: Int): Char = ('a' + Math.floorMod(letter - 'a' + by, 26)).toChar
