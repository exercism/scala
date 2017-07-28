import org.scalatest.{Matchers, FunSuite}

/** @version 2.0.0 */
class CryptoSquareTest extends FunSuite with Matchers {

  test("Lowercase") {
    CryptoSquare.normalizedPlaintext("Hello") should be ("hello")
  }

  test("Remove spaces") {
    pending
    CryptoSquare.normalizedPlaintext("Hi there") should be ("hithere")
  }

  test("Remove punctuation") {
    pending
    CryptoSquare.normalizedPlaintext("@1, 2%, 3 Go!") should be ("123go")
  }

  test("empty plaintext results in an empty rectangle") {
    pending
    CryptoSquare.plaintextSegments("") should be (List())
  }

  test("4 character plaintext results in an 2x2 rectangle") {
    pending
    CryptoSquare.plaintextSegments("Ab Cd") should be (List("ab", "cd"))
  }

  test("9 character plaintext results in an 3x3 rectangle") {
    pending
    CryptoSquare.plaintextSegments("This is fun!") should be (List("thi", "sis", "fun"))
  }

  test("54 character plaintext results in an 8x7 rectangle") {
    pending
    CryptoSquare.plaintextSegments("If man was meant to stay on the ground, god would have given us roots.") should be (List("ifmanwas", "meanttos", "tayonthe", "groundgo", "dwouldha", "vegivenu", "sroots"))
  }

  test("empty plaintext results in an empty encode") {
    pending
    CryptoSquare.encoded("") should be ("")
  }

  test("Non-empty plaintext results in the combined plaintext segments") {
    pending
    CryptoSquare.encoded("If man was meant to stay on the ground, god would have given us roots.") should be ("imtgdvsfearwermayoogoanouuiontnnlvtwttddesaohghnsseoau")
  }

  test("empty plaintext results in an empty ciphertext") {
    pending
    CryptoSquare.ciphertext("") should be ("")
  }

  test("9 character plaintext results in 3 chunks of 3 characters") {
    pending
    CryptoSquare.ciphertext("This is fun!") should be ("tsf hiu isn")
  }

  test("54 character plaintext results in 7 chunks, the last two padded with spaces") {
    pending
    CryptoSquare.ciphertext("If man was meant to stay on the ground, god would have given us roots.") should be ("imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn  sseoau ")
  }
}