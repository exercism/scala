import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class MinesweeperTest extends AnyFunSuite with Matchers {

  test("no rows") {
    Minesweeper.annotate(List()) should be(List())
  }

  test("no columns") {
    pending
    Minesweeper.annotate(List("")) should be(List(""))
  }

  test("no mines") {
    pending
    Minesweeper.annotate(List("   ",
                              "   ",
                              "   ")) should be(
      List("   ",
           "   ",
           "   "))
  }

  test("minefield with only mines") {
    pending
    Minesweeper.annotate(List("***",
                              "***",
                              "***")) should be(
      List("***",
           "***",
           "***"))
  }

  test("mine surrounded by spaces") {
    pending
    Minesweeper.annotate(List("   ",
                              " * ",
                              "   ")) should be(
      List("111",
           "1*1",
           "111"))
  }

  test("space surrounded by mines") {
    pending
    Minesweeper.annotate(List("***",
                              "* *",
                              "***")) should be(
      List("***",
           "*8*",
           "***"))
  }

  test("horizontal line") {
    pending
    Minesweeper.annotate(List(" * * ")) should be(List("1*2*1"))
  }

  test("horizontal line, mines at edges") {
    pending
    Minesweeper.annotate(List("*   *")) should be(List("*1 1*"))
  }

  test("vertical line") {
    pending
    Minesweeper.annotate(List(" ",
                              "*",
                              " ",
                              "*",
                              " ")) should be(
      List("1",
           "*",
           "2",
           "*",
           "1"))
  }

  test("vertical line, mines at edges") {
    pending
    Minesweeper.annotate(List("*",
                              " ",
                              " ",
                              " ",
                              "*")) should be(
      List("*",
           "1",
           " ",
           "1",
           "*"))
  }

  test("cross") {
    pending
    Minesweeper.annotate(List("  *  ",
                              "  *  ",
                              "*****",
                              "  *  ",
                              "  *  ")) should be(
      List(" 2*2 ",
           "25*52",
           "*****",
           "25*52",
           " 2*2 "))
  }

  test("large minefield") {
    pending
    Minesweeper.annotate(List(" *  * ",
                              "  *   ",
                              "    * ",
                              "   * *",
                              " *  * ",
                              "      ")) should be(
      List("1*22*1",
           "12*322",
           " 123*2",
           "112*4*",
           "1*22*2",
           "111111"))
  }
}
