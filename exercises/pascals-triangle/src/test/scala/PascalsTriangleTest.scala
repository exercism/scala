import org.scalatest.{Matchers, FunSuite}

/** @version 1.0.0 */
class PascalsTriangleTest extends FunSuite with Matchers {

  test("zero rows") {
    PascalsTriangle.rows(0) should be (List())
  }

  test("single row") {
    pending
    PascalsTriangle.rows(1) should be (List(List(1)))
  }

  test("two rows") {
    pending
    PascalsTriangle.rows(2) should be (List(List(1), List(1, 1)))
  }

  test("three rows") {
    pending
    PascalsTriangle.rows(3) should be (List(List(1), List(1, 1), List(1, 2, 1)))
  }

  test("four rows") {
    pending
    PascalsTriangle.rows(4) should be (List(List(1), List(1, 1), List(1, 2, 1), List(1, 3, 3, 1)))
  }

  test("negative rows") {
    pending
    PascalsTriangle.rows(-1) should be (List())
  }
}