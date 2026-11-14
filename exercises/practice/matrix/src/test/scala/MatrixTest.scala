import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class MatrixTest extends AnyFunSuite with Matchers:

  test("extract row from one number matrix"):
    Matrix("1").row(0) should be(Vector(1))

  test("can extract row"):
    pending
    Matrix("1 2\n3 4").row(1) should be(Vector(3, 4))

  test("extract row where numbers have different widths"):
    pending
    Matrix("1 2\n10 20").row(1) should be(Vector(10, 20))

  test("can extract row from non-square matrix with no corresponding column"):
    pending
    Matrix("1 2 3\n4 5 6\n7 8 9\n8 7 6").row(3) should be(Vector(8, 7, 6))

  test("extract column from one number matrix"):
    pending
    Matrix("1").column(0) should be(Vector(1))

  test("can extract column"):
    pending
    Matrix("1 2 3\n4 5 6\n7 8 9").column(2) should be(Vector(3, 6, 9))

  test("can extract column from non-square matrix with no corresponding row"):
    pending
    Matrix("1 2 3 4\n5 6 7 8\n9 8 7 6").column(3) should be(Vector(4, 8, 6))

  test("extract column where numbers have different widths"):
    pending
    Matrix("89 1903 3\n18 3 1\n9 4 800").column(1) should be(Vector(1903, 3, 4))
