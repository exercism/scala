import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class DifferenceOfSquaresTest extends AnyFunSuite with Matchers:

  test("Square the sum of the numbers up to the given number - square of sum 1"):
    DifferenceOfSquares.squareOfSum(1) should be(1)

  test("Square the sum of the numbers up to the given number - square of sum 5"):
    pending
    DifferenceOfSquares.squareOfSum(5) should be(225)

  test("Square the sum of the numbers up to the given number - square of sum 100"):
    pending
    DifferenceOfSquares.squareOfSum(100) should be(25502500)

  test("Sum the squares of the numbers up to the given number - sum of squares 1"):
    pending
    DifferenceOfSquares.sumOfSquares(1) should be(1)

  test("Sum the squares of the numbers up to the given number - sum of squares 5"):
    pending
    DifferenceOfSquares.sumOfSquares(5) should be(55)

  test("Sum the squares of the numbers up to the given number - sum of squares 100"):
    pending
    DifferenceOfSquares.sumOfSquares(100) should be(338350)

  test("Subtract sum of squares from square of sums - difference of squares 1"):
    pending
    DifferenceOfSquares.differenceOfSquares(1) should be(0)

  test("Subtract sum of squares from square of sums - difference of squares 5"):
    pending
    DifferenceOfSquares.differenceOfSquares(5) should be(170)

  test("Subtract sum of squares from square of sums - difference of squares 100"):
    pending
    DifferenceOfSquares.differenceOfSquares(100) should be(25164150)
