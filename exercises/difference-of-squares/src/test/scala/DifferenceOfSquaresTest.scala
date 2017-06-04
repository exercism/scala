import org.scalatest.{Matchers, FunSuite}

/** @version 1.1.0 */
class DifferenceOfSquaresTest extends FunSuite with Matchers {

  test("square of sum 1") {
    DifferenceOfSquares.squareOfSum(1) should be (1)
  }

  test("square of sum 5") {
    pending
    DifferenceOfSquares.squareOfSum(5) should be (225)
  }

  test("square of sum 100") {
    pending
    DifferenceOfSquares.squareOfSum(100) should be (25502500)
  }

  test("sum of squares 1") {
    pending
    DifferenceOfSquares.sumOfSquares(1) should be (1)
  }

  test("sum of squares 5") {
    pending
    DifferenceOfSquares.sumOfSquares(5) should be (55)
  }

  test("sum of squares 100") {
    pending
    DifferenceOfSquares.sumOfSquares(100) should be (338350)
  }

  test("difference of squares 1") {
    pending
    DifferenceOfSquares.differenceOfSquares(1) should be (0)
  }

  test("difference of squares 5") {
    pending
    DifferenceOfSquares.differenceOfSquares(5) should be (170)
  }

  test("difference of squares 100") {
    pending
    DifferenceOfSquares.differenceOfSquares(100) should be (25164150)
  }
}