import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class OcrNumbersTest extends AnyFunSuite with Matchers {

  test("Recognizes 0") {
    OcrNumbers.convert(List(" _ ",
                            "| |",
                            "|_|",
                            "   ")) should be(Some("0"))
  }

  test("Recognizes 1") {
    pending
    OcrNumbers.convert(List("   ",
                            "  |",
                            "  |",
                            "   ")) should be(Some("1"))
  }

  test("Unreadable but correctly sized inputs return ?") {
    pending
    OcrNumbers.convert(List("   ",
                            "  _",
                            "  |",
                            "   ")) should be(Some("?"))
  }

  test("Input with no lines returns None") {
    pending
    OcrNumbers.convert(List()) should be(None)
  }

  test(
    "Input with a number of lines that is not a multiple of four returns None") {
    pending
    OcrNumbers.convert(List(" _ ",
                            "| |",
                            "   ")) should be(None)
  }

  test("Input with empty columns returns None") {
    pending
    OcrNumbers.convert(List("", "", "", "")) should be(None)
  }


  test(
    "Input with a number of columns that is not a multiple of three returns None") {
    pending
    OcrNumbers.convert(List("    ",
                            "   |",
                            "   |",
                            "    ")) should be(None)
  }

  test("Recognizes 110101100") {
    pending
    OcrNumbers.convert(
      List("       _     _        _  _ ",
           "  |  || |  || |  |  || || |",
           "  |  ||_|  ||_|  |  ||_||_|",
           "                           ")) should be(Some("110101100"))
  }

  test("Garbled numbers in a string are replaced with ?") {
    pending
    OcrNumbers.convert(
      List("       _     _           _ ",
           "  |  || |  || |     || || |",
           "  |  | _|  ||_|  |  ||_||_|",
           "                           ")) should be(Some("11?10?1?0"))
  }

  test("Recognizes 2") {
    pending
    OcrNumbers.convert(List(" _ ",
                            " _|",
                            "|_ ",
                            "   ")) should be(Some("2"))
  }

  test("Recognizes 3") {
    pending
    OcrNumbers.convert(List(" _ ",
                            " _|",
                            " _|",
                            "   ")) should be(Some("3"))
  }

  test("Recognizes 4") {
    pending
    OcrNumbers.convert(List("   ",
                            "|_|",
                            "  |",
                            "   ")) should be(Some("4"))
  }

  test("Recognizes 5") {
    pending
    OcrNumbers.convert(List(" _ ",
                            "|_ ",
                            " _|",
                            "   ")) should be(Some("5"))
  }

  test("Recognizes 6") {
    pending
    OcrNumbers.convert(List(" _ ",
                            "|_ ",
                            "|_|",
                            "   ")) should be(Some("6"))
  }

  test("Recognizes 7") {
    pending
    OcrNumbers.convert(List(" _ ",
                            "  |",
                            "  |",
                            "   ")) should be(Some("7"))
  }

  test("Recognizes 8") {
    pending
    OcrNumbers.convert(List(" _ ",
                            "|_|",
                            "|_|",
                            "   ")) should be(Some("8"))
  }

  test("Recognizes 9") {
    pending
    OcrNumbers.convert(List(" _ ",
                            "|_|",
                            " _|",
                            "   ")) should be(Some("9"))
  }

  test("Recognizes string of decimal numbers") {
    pending
    OcrNumbers.convert(
      List("    _  _     _  _  _  _  _  _ ",
           "  | _| _||_||_ |_   ||_||_|| |",
           "  ||_  _|  | _||_|  ||_| _||_|",
           "                              ")) should be(Some("1234567890"))
  }

  test(
    "Numbers separated by empty lines are recognized. Lines are joined by commas.") {
    pending
    OcrNumbers.convert(
      List("    _  _ ",
           "  | _| _|",
           "  ||_  _|",
           "         ",
           "    _  _ ",
           "|_||_ |_ ",
           "  | _||_|",
           "         ",
           " _  _  _ ",
           "  ||_||_|",
           "  ||_| _|",
           "         ")) should be(Some("123,456,789"))
  }
}
