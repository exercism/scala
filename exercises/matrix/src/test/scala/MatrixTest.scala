import org.scalatest.{Matchers, FlatSpec}

class MatrixTest extends FlatSpec with Matchers {
  it should "extract a row" in {
    Matrix("1 2\n10 20").rows(0) should be (Vector(1, 2))
    Matrix("1 2\n10 20\n100 200").rows(2) should be (Vector(100, 200))
  }

  it should "extract a col" in {
    Matrix("1 2\n10 20").cols(0) should be (Vector(1, 10))
    Matrix("1 2\n10 20\n100 200").cols(1) should be (Vector(2, 20, 200))
  }

  it should "support equality" in {
    Matrix("1 2\n10 20") should be (Matrix("1 2\n10 20"))
    Matrix("1 2\n10 20") should not be Matrix("20 10\n100 200")
  }
}
