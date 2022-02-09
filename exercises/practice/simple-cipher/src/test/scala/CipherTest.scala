import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version created manually **/
class CipherTest extends AnyFunSuite with Matchers {

  test("Random key cipher - can encode/decode") {
    // Here we take advantage of the fact that plaintext of "aaa..."
    // outputs the key. This is a critical problem with shift ciphers, some
    // characters will always output the key verbatim.

    val cipher = Cipher(None)
    cipher.encode("aaaaaaaaaa") should be (cipher.key.substring(0, 10))
    cipher.decode(cipher.key.substring(0, 10)) should be ("aaaaaaaaaa")
  }

  test("Invalid key - contains caps") { 
    pending
    intercept[IllegalArgumentException] { 
      Cipher(Some("ABCD"))
    }
  }

  test("Invalid key - contains numerics") { 
    pending
    intercept[IllegalArgumentException] {
      Cipher(Some("123"))
    }
  }

  test("Invalid key - is empty") { 
    pending
    intercept[IllegalArgumentException] {
      Cipher(Some(""))
    }
  }

  test("Substitution cipher - can encode") { 
    pending
    Cipher(Some("abcdefghij")).encode("aaaaaaaaaa") should be ("abcdefghij")
  }

  test("Substitution cipher - can decode") { 
    pending
    Cipher(Some("abcdefghij")).decode("abcdefghij") should be ("aaaaaaaaaa")
  }

  test("Substitution cipher - is reversible") { 
    pending
    val cipher = Cipher(Some("abcdefghij"))
    cipher.decode(cipher.encode("abcdefghij")) should be ("abcdefghij")
  }

  test("Substitution cipher - can double shift") { 
    pending
    val cipher = Cipher(Some("iamapandabear"))
    cipher.encode("iamapandabear") should be ("qayaeaagaciai")
  }

  test("Substitution cipher - can wrap") { 
    pending
    Cipher(Some("abcdefghij")).encode("zzzzzzzzzz") should be ("zabcdefghi")
  }
}
