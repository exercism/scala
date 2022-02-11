import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.2.0 */
class RotationalCipherTest extends AnyFunSuite with Matchers {

  test("rotate a by 0, same output as input") {
    RotationalCipher.rotate("a", 0) should be("a")
  }

  test("rotate a by 1") {
    pending
    RotationalCipher.rotate("a", 1) should be("b")
  }

  test("rotate a by 26, same output as input") {
    pending
    RotationalCipher.rotate("a", 26) should be("a")
  }

  test("rotate m by 13") {
    pending
    RotationalCipher.rotate("m", 13) should be("z")
  }

  test("rotate n by 13 with wrap around alphabet") {
    pending
    RotationalCipher.rotate("n", 13) should be("a")
  }

  test("rotate capital letters") {
    pending
    RotationalCipher.rotate("OMG", 5) should be("TRL")
  }

  test("rotate spaces") {
    pending
    RotationalCipher.rotate("O M G", 5) should be("T R L")
  }

  test("rotate numbers") {
    pending
    RotationalCipher.rotate("Testing 1 2 3 testing", 4) should be(
      "Xiwxmrk 1 2 3 xiwxmrk")
  }

  test("rotate punctuation") {
    pending
    RotationalCipher.rotate("Let's eat, Grandma!", 21) should be(
      "Gzo'n zvo, Bmviyhv!")
  }

  test("rotate all letters") {
    pending
    RotationalCipher.rotate("The quick brown fox jumps over the lazy dog.", 13) should be(
      "Gur dhvpx oebja sbk whzcf bire gur ynml qbt.")
  }
}
