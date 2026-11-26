import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate_tests.
  */
class CipherTest extends AnyFunSuite with Matchers:

  test("Random key cipher - Can encode"):
    val cipher = Cipher(None)
    cipher.encode("aaaaaaaaaa") should be(cipher.key.substring(0, 10))

  test("Random key cipher - Can decode"):
    pending
    val cipher = Cipher(None)
    cipher.decode(cipher.key.substring(0, 10)) should be("aaaaaaaaaa")

  test(
    "Random key cipher - Is reversible. I.e., if you apply decode to an encoded result, you must see the same plaintext encode parameter as a result of the decode method",
  ):
    pending
    val cipher = Cipher(None)
    cipher.decode(cipher.encode("abcdefghij")) should be("abcdefghij")

  test("Random key cipher - Key is made only of lowercase letters"):
    pending
    val cipher = Cipher(None)
    cipher.key should fullyMatch regex "^[a-z]+$"

  test("Substitution cipher - Can encode"):
    pending
    val cipher = Cipher(Some("abcdefghij"))
    cipher.encode("aaaaaaaaaa") should be("abcdefghij")

  test("Substitution cipher - Can decode"):
    pending
    val cipher = Cipher(Some("abcdefghij"))
    cipher.decode("abcdefghij") should be("aaaaaaaaaa")

  test(
    "Substitution cipher - Is reversible. I.e., if you apply decode to an encoded result, you must see the same plaintext encode parameter as a result of the decode method",
  ):
    pending
    val cipher = Cipher(Some("abcdefghij"))
    cipher.decode(cipher.encode("abcdefghij")) should be("abcdefghij")

  test("Substitution cipher - Can double shift encode"):
    pending
    val cipher = Cipher(Some("iamapandabear"))
    cipher.encode("iamapandabear") should be("qayaeaagaciai")

  test("Substitution cipher - Can wrap on encode"):
    pending
    val cipher = Cipher(Some("abcdefghij"))
    cipher.encode("zzzzzzzzzz") should be("zabcdefghi")

  test("Substitution cipher - Can wrap on decode"):
    pending
    val cipher = Cipher(Some("abcdefghij"))
    cipher.decode("zabcdefghi") should be("zzzzzzzzzz")

  test("Substitution cipher - Can encode messages longer than the key"):
    pending
    val cipher = Cipher(Some("abc"))
    cipher.encode("iamapandabear") should be("iboaqcnecbfcr")

  test("Substitution cipher - Can decode messages longer than the key"):
    pending
    val cipher = Cipher(Some("abc"))
    cipher.decode("iboaqcnecbfcr") should be("iamapandabear")

  // Not in the canonical data: the instructions ask for a key of lowercase letters only.
  test("Invalid key - contains caps"):
    pending
    assertThrows[IllegalArgumentException](Cipher(Some("ABCD")))

  test("Invalid key - contains numerics"):
    pending
    assertThrows[IllegalArgumentException](Cipher(Some("123")))

  test("Invalid key - is empty"):
    pending
    assertThrows[IllegalArgumentException](Cipher(Some("")))
