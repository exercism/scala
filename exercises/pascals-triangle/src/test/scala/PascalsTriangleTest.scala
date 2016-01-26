import org.scalatest.{Matchers, FlatSpec}

class PascalsTriangleTest extends FlatSpec with Matchers {
  it should "handle 1 row" in {
    PascalsTriangle.triangle(1) should be (List(List(1)))
  }

  it should "handle 2 rows" in {
    PascalsTriangle.triangle(2) should be (List(List(1), List(1, 1)))
  }

  it should "handle 3 rows" in {
    PascalsTriangle.triangle(3) should be (List(List(1), List(1, 1), List(1, 2, 1)))
  }

  it should "handle 4 rows" in {
    PascalsTriangle.triangle(4) should be (List(List(1), List(1, 1),
      List(1, 2, 1), List(1, 3, 3, 1)))
  }

  it should "handle 5 rows" in {
    PascalsTriangle.triangle(5) should be (List(List(1), List(1, 1),
      List(1, 2, 1), List(1, 3, 3, 1), List(1, 4, 6, 4, 1)))
  }

  it should "handle 20 rows" in {
    PascalsTriangle.triangle(20).takeRight(1).head should be (List(1, 19,
      171, 969, 3876, 11628, 27132, 50388, 75582, 92378,
      92378, 75582, 50388, 27132, 11628, 3876, 969, 171,
      19, 1))
  }
}
