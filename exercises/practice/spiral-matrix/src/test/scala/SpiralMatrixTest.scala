import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class SpiralMatrixTest extends AnyFunSuite with Matchers {

  test("empty spiral") {
    SpiralMatrix.spiralMatrix(0) should be(List())
  }

  test("trivial spiral") {
    pending
    SpiralMatrix.spiralMatrix(1) should be(List(List(1)))
  }

  test("spiral of size 2") {
    pending
    SpiralMatrix.spiralMatrix(2) should be(List(List(1, 2),
                                                List(4, 3)))
  }

  test("spiral of size 3") {
    pending
    SpiralMatrix.spiralMatrix(3) should be(
      List(List(1, 2, 3), 
           List(8, 9, 4), 
           List(7, 6, 5)))
  }

  test("spiral of size 4") {
    pending
    SpiralMatrix.spiralMatrix(4) should be(
      List(List(1, 2, 3, 4),
           List(12, 13, 14, 5),
           List(11, 16, 15, 6),
           List(10, 9, 8, 7)))
  }

  test("spiral of size 5") {
    pending
    SpiralMatrix.spiralMatrix(5) should be(
      List(List(1, 2, 3, 4, 5),
           List(16, 17, 18, 19, 6),
           List(15, 24, 25, 20, 7),
           List(14, 23, 22, 21, 8),
           List(13, 12, 11, 10, 9)))
  }
}
