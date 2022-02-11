import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.6.0 */
class AcronymTest extends AnyFunSuite with Matchers {

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

  test("very long abbreviation") {
    pending
    Acronym.abbreviate("Rolling On The Floor Laughing So Hard That My Dogs Came Over And Licked Me") should be ("ROTFLSHTMDCOALM")
  }

  test("consecutive delimiters") {
    pending
    Acronym.abbreviate("Something - I made up from thin air") should be ("SIMUFTA")
  }

  test("apostrophes") {
    pending
    Acronym.abbreviate("Halley's Comet") should be ("HC")
  }
}
