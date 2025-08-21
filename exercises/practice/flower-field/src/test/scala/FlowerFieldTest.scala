import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class FlowerFieldTest extends AnyFunSuite with Matchers {

  test("no rows") {
    FlowerField.annotate(List()) should be(List())
  }

  test("no columns") {
    pending
    FlowerField.annotate(List("")) should be(List(""))
  }

  test("no flowers") {
    pending
    FlowerField.annotate(List("   ",
                              "   ",
                              "   ")) should be(
      List("   ",
           "   ",
           "   "))
  }

  test("garden with only flowers") {
    pending
    FlowerField.annotate(List("***",
                              "***",
                              "***")) should be(
      List("***",
           "***",
           "***"))
  }

  test("flower surrounded by spaces") {
    pending
    FlowerField.annotate(List("   ",
                              " * ",
                              "   ")) should be(
      List("111",
           "1*1",
           "111"))
  }

  test("space surrounded by flowers") {
    pending
    FlowerField.annotate(List("***",
                              "* *",
                              "***")) should be(
      List("***",
           "*8*",
           "***"))
  }

  test("horizontal line") {
    pending
    FlowerField.annotate(List(" * * ")) should be(List("1*2*1"))
  }

  test("horizontal line, flowers at edges") {
    pending
    FlowerField.annotate(List("*   *")) should be(List("*1 1*"))
  }

  test("vertical line") {
    pending
    FlowerField.annotate(List(" ",
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

  test("vertical line, flowers at edges") {
    pending
    FlowerField.annotate(List("*",
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
    FlowerField.annotate(List("  *  ",
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

  test("large garden") {
    pending
    FlowerField.annotate(List(" *  * ",
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
