import org.scalatest.{Matchers, FlatSpec}

class SaddlePointsSpecs extends FlatSpec with Matchers {
  it should "handle one saddle" in {
    val points = Matrix(List(List(9, 8, 7), List(5, 3, 2), List(6, 6, 7))).saddlePoints
    points should be (Set((1, 0)))
  }

  it should "handle multiple saddles" in {
    val points = Matrix(List(List(5, 3, 5, 4), List(6, 4, 7, 3), List(5, 1, 5, 3))).saddlePoints
    points should be (Set((0, 0), (0, 2), (2, 0), (2, 2)))
  }

  it should "handle no saddles" in {
    val points = Matrix(List(List(2, 1), List(1, 2))).saddlePoints
    points should be (Set())
  }

  it should "handle empty matrix" in {
    val points = Matrix(List()).saddlePoints
    points should be (Set())
  }
}
