import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class SaddlePointsTest extends AnyFunSuite with Matchers {

  test("Can identify single saddle point") {
    Matrix(List(List(9, 8, 7), List(5, 3, 2), List(6, 6, 7))).saddlePoints should be(
      Set((1, 0)))
  }

  test("Can identify that empty matrix has no saddle points") {
    pending
    Matrix(List(List())).saddlePoints should be(Set())
  }

  test("Can identify lack of saddle points when there are none") {
    pending
    Matrix(List(List(1, 2, 3), List(3, 1, 2), List(2, 3, 1))).saddlePoints should be(
      Set())
  }

  test("Can identify multiple saddle points in a column") {
    pending
    Matrix(List(List(4, 5, 4), List(3, 5, 5), List(1, 5, 4))).saddlePoints should be(
      Set((0, 1), (1, 1), (2, 1)))
  }

  test("Can identify multiple saddle points in a row") {
    pending
    Matrix(List(List(6, 7, 8), List(5, 5, 5), List(7, 5, 6))).saddlePoints should be(
      Set((1, 0), (1, 1), (1, 2)))
  }

  test("Can identify saddle point in bottom right corner") {
    pending
    Matrix(List(List(8, 7, 9), List(6, 7, 6), List(3, 2, 5))).saddlePoints should be(
      Set((2, 2)))
  }

  test("Can identify saddle points in a non square matrix") {
    pending
    Matrix(List(List(3, 1, 3), List(3, 2, 4))).saddlePoints should be(
      Set((0, 2), (0, 0)))
  }

  test("Can identify that saddle points in a single column matrix are those with the minimum value") {
    pending
    Matrix(List(List(2), List(1), List(4), List(1))).saddlePoints should be(
      Set((1, 0), (3, 0)))
  }

  test("Can identify that saddle points in a single row matrix are those with the maximum value") {
    pending
    Matrix(List(List(2, 5, 3, 5))).saddlePoints should be(
      Set((0, 1), (0, 3)))
  }
}
