import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class OcrNumbersTest extends AnyFunSuite with Matchers {

  test("Recognizes 0") {
    OcrNumbers.convert(List(" _ ",
                            "| |",
                            "|_|",
                            "   ")) should be("0")
  }

  test("Recognizes 1") {
    pending
    OcrNumbers.convert(List("   ",
                            "  |",
                            "  |",
                            "   ")) should be("1")
  }

  test("Unreadable but correctly sized inputs return ?") {
    pending
    OcrNumbers.convert(List("   ",
                            "  _",
                            "  |",
                            "   ")) should be("?")
  }

  test(
    "Input with a number of lines that is not a multiple of four raises an error") {
    pending
    OcrNumbers.convert(List(" _ ",
                            "| |",
                            "   ")) should be("?")
  }

  test(
    "Input with a number of columns that is not a multiple of three raises an error") {
    pending
    OcrNumbers.convert(List("    ",
                            "   |",
                            "   |",
                            "    ")) should be("?")
  }

  test("Recognizes 110101100") {
    pending
    OcrNumbers.convert(
      List("       _     _        _  _ ",
           "  |  || |  || |  |  || || |",
           "  |  ||_|  ||_|  |  ||_||_|",
           "                           ")) should be("110101100")
  }

  test("Garbled numbers in a string are replaced with ?") {
    pending
    OcrNumbers.convert(
      List("       _     _           _ ",
           "  |  || |  || |     || || |",
           "  |  | _|  ||_|  |  ||_||_|",
           "                           ")) should be("11?10?1?0")
  }

  test("Recognizes 2") {
    pending
    OcrNumbers.convert(List(" _ ",
                            " _|",
                            "|_ ",
                            "   ")) should be("2")
  }

  test("Recognizes 3") {
    pending
    OcrNumbers.convert(List(" _ ",
                            " _|",
                            " _|",
                            "   ")) should be("3")
  }

  test("Recognizes 4") {
    pending
    OcrNumbers.convert(List("   ",
                            "|_|",
                            "  |",
                            "   ")) should be("4")
  }

  test("Recognizes 5") {
    pending
    OcrNumbers.convert(List(" _ ",
                            "|_ ",
                            " _|",
                            "   ")) should be("5")
  }

  test("Recognizes 6") {
    pending
    OcrNumbers.convert(List(" _ ",
                            "|_ ",
                            "|_|",
                            "   ")) should be("6")
  }

  test("Recognizes 7") {
    pending
    OcrNumbers.convert(List(" _ ",
                            "  |",
                            "  |",
                            "   ")) should be("7")
  }

  test("Recognizes 8") {
    pending
    OcrNumbers.convert(List(" _ ",
                            "|_|",
                            "|_|",
                            "   ")) should be("8")
  }

  test("Recognizes 9") {
    pending
    OcrNumbers.convert(List(" _ ",
                            "|_|",
                            " _|",
                            "   ")) should be("9")
  }

  test("Recognizes string of decimal numbers") {
    pending
    OcrNumbers.convert(
      List("    _  _     _  _  _  _  _  _ ",
           "  | _| _||_||_ |_   ||_||_|| |",
           "  ||_  _|  | _||_|  ||_| _||_|",
           "                              ")) should be("1234567890")
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
           "         ")) should be("123,456,789")
  }
}
