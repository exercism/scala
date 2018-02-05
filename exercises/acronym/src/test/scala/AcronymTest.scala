import org.scalatest.{Matchers, FunSuite}

/** @version 1.3.0 */
class AcronymTest extends FunSuite with Matchers {

  test("basic") {
    Acronym.abbreviate("Portable Network Graphics") should be ("PNG")
  }

  test("lowercase words") {
    pending
    Acronym.abbreviate("Ruby on Rails") should be ("ROR")
  }

  test("punctuation") {
    pending
    Acronym.abbreviate("First In, First Out") should be ("FIFO")
  }

  test("all caps word") {
    pending
    Acronym.abbreviate("GNU Image Manipulation Program") should be ("GIMP")
  }

  test("punctuation without whitespace") {
    pending
    Acronym.abbreviate("Complementary metal-oxide semiconductor") should be ("CMOS")
  }
}