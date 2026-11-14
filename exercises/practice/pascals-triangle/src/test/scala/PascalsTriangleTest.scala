import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class PascalsTriangleTest extends AnyFunSuite with Matchers:

  test("zero rows"):
    val expected = List()
    PascalsTriangle.rows(0) should be(expected)

  test("single row"):
    pending
    val expected = List(
      List(1),
    )
    PascalsTriangle.rows(1) should be(expected)

  test("two rows"):
    pending
    val expected = List(
      List(1),
      List(1, 1),
    )
    PascalsTriangle.rows(2) should be(expected)

  test("three rows"):
    pending
    val expected = List(
      List(1),
      List(1, 1),
      List(1, 2, 1),
    )
    PascalsTriangle.rows(3) should be(expected)

  test("four rows"):
    pending
    val expected = List(
      List(1),
      List(1, 1),
      List(1, 2, 1),
      List(1, 3, 3, 1),
    )
    PascalsTriangle.rows(4) should be(expected)

  test("five rows"):
    pending
    val expected = List(
      List(1),
      List(1, 1),
      List(1, 2, 1),
      List(1, 3, 3, 1),
      List(1, 4, 6, 4, 1),
    )
    PascalsTriangle.rows(5) should be(expected)

  test("six rows"):
    pending
    val expected = List(
      List(1),
      List(1, 1),
      List(1, 2, 1),
      List(1, 3, 3, 1),
      List(1, 4, 6, 4, 1),
      List(1, 5, 10, 10, 5, 1),
    )
    PascalsTriangle.rows(6) should be(expected)

  test("ten rows"):
    pending
    val expected = List(
      List(1),
      List(1, 1),
      List(1, 2, 1),
      List(1, 3, 3, 1),
      List(1, 4, 6, 4, 1),
      List(1, 5, 10, 10, 5, 1),
      List(1, 6, 15, 20, 15, 6, 1),
      List(1, 7, 21, 35, 35, 21, 7, 1),
      List(1, 8, 28, 56, 70, 56, 28, 8, 1),
      List(1, 9, 36, 84, 126, 126, 84, 36, 9, 1),
    )
    PascalsTriangle.rows(10) should be(expected)
