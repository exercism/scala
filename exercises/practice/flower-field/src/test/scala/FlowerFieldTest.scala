import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class FlowerFieldTest extends AnyFunSuite with Matchers:

  test("no rows"):
    val garden   = List()
    val expected = List()
    FlowerField.annotate(garden) should be(expected)

  test("no columns"):
    pending
    val garden   = List(
      "",
    )
    val expected = List(
      "",
    )
    FlowerField.annotate(garden) should be(expected)

  test("no flowers"):
    pending
    val garden   = List(
      "   ",
      "   ",
      "   ",
    )
    val expected = List(
      "   ",
      "   ",
      "   ",
    )
    FlowerField.annotate(garden) should be(expected)

  test("garden full of flowers"):
    pending
    val garden   = List(
      "***",
      "***",
      "***",
    )
    val expected = List(
      "***",
      "***",
      "***",
    )
    FlowerField.annotate(garden) should be(expected)

  test("flower surrounded by spaces"):
    pending
    val garden   = List(
      "   ",
      " * ",
      "   ",
    )
    val expected = List(
      "111",
      "1*1",
      "111",
    )
    FlowerField.annotate(garden) should be(expected)

  test("space surrounded by flowers"):
    pending
    val garden   = List(
      "***",
      "* *",
      "***",
    )
    val expected = List(
      "***",
      "*8*",
      "***",
    )
    FlowerField.annotate(garden) should be(expected)

  test("horizontal line"):
    pending
    val garden   = List(
      " * * ",
    )
    val expected = List(
      "1*2*1",
    )
    FlowerField.annotate(garden) should be(expected)

  test("horizontal line, flowers at edges"):
    pending
    val garden   = List(
      "*   *",
    )
    val expected = List(
      "*1 1*",
    )
    FlowerField.annotate(garden) should be(expected)

  test("vertical line"):
    pending
    val garden   = List(
      " ",
      "*",
      " ",
      "*",
      " ",
    )
    val expected = List(
      "1",
      "*",
      "2",
      "*",
      "1",
    )
    FlowerField.annotate(garden) should be(expected)

  test("vertical line, flowers at edges"):
    pending
    val garden   = List(
      "*",
      " ",
      " ",
      " ",
      "*",
    )
    val expected = List(
      "*",
      "1",
      " ",
      "1",
      "*",
    )
    FlowerField.annotate(garden) should be(expected)

  test("cross"):
    pending
    val garden   = List(
      "  *  ",
      "  *  ",
      "*****",
      "  *  ",
      "  *  ",
    )
    val expected = List(
      " 2*2 ",
      "25*52",
      "*****",
      "25*52",
      " 2*2 ",
    )
    FlowerField.annotate(garden) should be(expected)

  test("large garden"):
    pending
    val garden   = List(
      " *  * ",
      "  *   ",
      "    * ",
      "   * *",
      " *  * ",
      "      ",
    )
    val expected = List(
      "1*22*1",
      "12*322",
      " 123*2",
      "112*4*",
      "1*22*2",
      "111111",
    )
    FlowerField.annotate(garden) should be(expected)

  test("multiple adjacent flowers"):
    pending
    val garden   = List(
      " ** ",
    )
    val expected = List(
      "1**1",
    )
    FlowerField.annotate(garden) should be(expected)
