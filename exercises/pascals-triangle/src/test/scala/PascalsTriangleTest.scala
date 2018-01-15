import org.scalatest.{Matchers, FunSuite}

/** @version 1.0.0 */
class PascalsTriangleTest extends FunSuite with Matchers {

  test("zero rows") {
    PascalsTriangle.rows(0) should be (List())
  }

  test("single row") {
    PascalsTriangle.rows(1) should be (List(List(1)))
  }

  test("two rows") {
    PascalsTriangle.rows(2) should be (List(List(1), List(1, 1)))
  }

  test("three rows") {
    PascalsTriangle.rows(3) should be (List(List(1), List(1, 1), List(1, 2, 1)))
  }

  test("four rows") {
    PascalsTriangle.rows(4) should be (List(List(1), List(1, 1), List(1, 2, 1), List(1, 3, 3, 1)))
  }

  test("negative rows") {
    PascalsTriangle.rows(-1) should be (List())
  }
}