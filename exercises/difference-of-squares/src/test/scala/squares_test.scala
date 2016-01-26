import org.scalatest.{Matchers, FlatSpec}

class SquaresTest extends FlatSpec with Matchers {
  it should "calc square of sums to 5" in {
    val result = Squares().squareOfSums(5)
    result should equal (225)
  }

  it should "calc sum of squares to 5" in {
    val result = Squares().sumOfSquares(5)
    result should equal (55)
  }

  it should "calc difference of sums to 5" in {
    val result = Squares().difference(5)
    result should equal (170)
  }

  it should "calc square of sums to 10" in {
    val result = Squares().squareOfSums(10)
    result should equal (3025)
  }

  it should "calc sum of squares to 10" in {
    val result = Squares().sumOfSquares(10)
    result should equal (385)
  }

  it should "calc difference of sums to 10" in {
    val result = Squares().difference(10)
    result should equal (2640)
  }

  it should "calc square of sums to 100" in {
    val result = Squares().squareOfSums(100)
    result should equal (25502500)
  }

  it should "calc sum of squares to 100" in {
    val result = Squares().sumOfSquares(100)
    result should equal (338350)
  }

  it should "calc difference of sums to 100" in {
    val result = Squares().difference(100)
    result should equal (25164150)
  }
}
