import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class TriangleTest extends AnyFunSuite with Matchers:

  test("equilateral triangle - all sides are equal"):
    Triangle(2, 2, 2).equilateral should be(true)

  test("equilateral triangle - any side is unequal"):
    pending
    Triangle(2, 3, 2).equilateral should be(false)

  test("equilateral triangle - no sides are equal"):
    pending
    Triangle(5, 4, 6).equilateral should be(false)

  test("equilateral triangle - all zero sides is not a triangle"):
    pending
    Triangle(0, 0, 0).equilateral should be(false)

  test("equilateral triangle - sides may be floats"):
    pending
    Triangle(0.5, 0.5, 0.5).equilateral should be(true)

  test("isosceles triangle - last two sides are equal"):
    pending
    Triangle(3, 4, 4).isosceles should be(true)

  test("isosceles triangle - first two sides are equal"):
    pending
    Triangle(4, 4, 3).isosceles should be(true)

  test("isosceles triangle - first and last sides are equal"):
    pending
    Triangle(4, 3, 4).isosceles should be(true)

  test("isosceles triangle - equilateral triangles are also isosceles"):
    pending
    Triangle(4, 4, 4).isosceles should be(true)

  test("isosceles triangle - no sides are equal"):
    pending
    Triangle(2, 3, 4).isosceles should be(false)

  test("isosceles triangle - first triangle inequality violation"):
    pending
    Triangle(1, 1, 3).isosceles should be(false)

  test("isosceles triangle - second triangle inequality violation"):
    pending
    Triangle(1, 3, 1).isosceles should be(false)

  test("isosceles triangle - third triangle inequality violation"):
    pending
    Triangle(3, 1, 1).isosceles should be(false)

  test("isosceles triangle - sides may be floats"):
    pending
    Triangle(0.5, 0.4, 0.5).isosceles should be(true)

  test("scalene triangle - no sides are equal"):
    pending
    Triangle(5, 4, 6).scalene should be(true)

  test("scalene triangle - all sides are equal"):
    pending
    Triangle(4, 4, 4).scalene should be(false)

  test("scalene triangle - first and second sides are equal"):
    pending
    Triangle(4, 4, 3).scalene should be(false)

  test("scalene triangle - first and third sides are equal"):
    pending
    Triangle(3, 4, 3).scalene should be(false)

  test("scalene triangle - second and third sides are equal"):
    pending
    Triangle(4, 3, 3).scalene should be(false)

  test("scalene triangle - may not violate triangle inequality"):
    pending
    Triangle(7, 3, 2).scalene should be(false)

  test("scalene triangle - sides may be floats"):
    pending
    Triangle(0.5, 0.4, 0.6).scalene should be(true)
