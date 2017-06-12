
import org.scalatest.{Matchers, FunSuite}

/** @version 1.1.0 */
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

  test("all caps words") { 
    pending
    Acronym.abbreviate("PHP: Hypertext Preprocessor") should be ("PHP")
  }

  test("non-acronym all caps word") { 
    pending
    Acronym.abbreviate("GNU Image Manipulation Program") should be ("GIMP")
  }

  test("hyphenated") { 
    pending
    Acronym.abbreviate("Complementary metal-oxide semiconductor") should be ("CMOS")
  }
}