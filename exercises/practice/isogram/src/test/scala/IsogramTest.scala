import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class IsogramTest extends AnyFunSuite with Matchers:

  test("empty string"):
    Isogram.isIsogram("") should be(true)

  test("isogram with only lower case characters"):
    pending
    Isogram.isIsogram("isogram") should be(true)

  test("word with one duplicated character"):
    pending
    Isogram.isIsogram("eleven") should be(false)

  test("word with one duplicated character from the end of the alphabet"):
    pending
    Isogram.isIsogram("zzyzx") should be(false)

  test("longest reported english isogram"):
    pending
    Isogram.isIsogram("subdermatoglyphic") should be(true)

  test("word with duplicated character in mixed case"):
    pending
    Isogram.isIsogram("Alphabet") should be(false)

  test("word with duplicated character in mixed case, lowercase first"):
    pending
    Isogram.isIsogram("alphAbet") should be(false)

  test("hypothetical isogrammic word with hyphen"):
    pending
    Isogram.isIsogram("thumbscrew-japingly") should be(true)

  test("hypothetical word with duplicated character following hyphen"):
    pending
    Isogram.isIsogram("thumbscrew-jappingly") should be(false)

  test("isogram with duplicated hyphen"):
    pending
    Isogram.isIsogram("six-year-old") should be(true)

  test("made-up name that is an isogram"):
    pending
    Isogram.isIsogram("Emily Jung Schwartzkopf") should be(true)

  test("duplicated character in the middle"):
    pending
    Isogram.isIsogram("accentor") should be(false)

  test("same first and last characters"):
    pending
    Isogram.isIsogram("angola") should be(false)

  test("word with duplicated character and with two hyphens"):
    pending
    Isogram.isIsogram("up-to-date") should be(false)
