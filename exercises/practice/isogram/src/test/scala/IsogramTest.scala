import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.4.0 */
class IsogramTest extends AnyFunSuite with Matchers {

  test("empty string") {
    Isogram.isIsogram("") should be(true)
  }

  test("isogram with only lower case characters") {
    pending
    Isogram.isIsogram("isogram") should be(true)
  }

  test("word with one duplicated character") {
    pending
    Isogram.isIsogram("eleven") should be(false)
  }

  test("word with one duplicated character from the end of the alphabet") {
    pending
    Isogram.isIsogram("zzyzx") should be(false)
  }

  test("longest reported english isogram") {
    pending
    Isogram.isIsogram("subdermatoglyphic") should be(true)
  }

  test("word with duplicated character in mixed case") {
    pending
    Isogram.isIsogram("Alphabet") should be(false)
  }

  test("hypothetical isogrammic word with hyphen") {
    pending
    Isogram.isIsogram("thumbscrew-japingly") should be(true)
  }

  test("isogram with duplicated hyphen") {
    pending
    Isogram.isIsogram("six-year-old") should be(true)
  }

  test("made-up name that is an isogram") {
    pending
    Isogram.isIsogram("Emily Jung Schwartzkopf") should be(true)
  }

  test("duplicated character in the middle") {
    pending
    Isogram.isIsogram("accentor") should be(false)
  }
}
