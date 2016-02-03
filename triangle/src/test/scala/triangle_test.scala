import org.scalatest.{Matchers, FlatSpec}

class TriangleTest extends FlatSpec with Matchers {
  it should "calc Equilateral" in {
    Triangle(2, 2, 2).triangleType should equal(TriangleType.Equilateral)
  }

  it should "calc Equilateral2" in {
    Triangle(10, 10, 10).triangleType should equal(TriangleType.Equilateral)
  }

  it should "calc Isosceles" in {
    Triangle(3, 4, 4).triangleType should equal(TriangleType.Isosceles)
  }

  it should "calc Isosceles2" in {
    Triangle(4, 3, 4).triangleType should equal(TriangleType.Isosceles)
  }

  it should "calc Scalene" in {
    Triangle(3, 4, 5).triangleType should equal(TriangleType.Scalene)
  }

  it should "calc Illogical" in {
    Triangle(1, 1, 50).triangleType should equal(TriangleType.Illogical)
  }

  it should "calc Illogical with zero length side" in {
    Triangle(0, 2, 1).triangleType should equal(TriangleType.Illogical)
  }

  it should "calc Illogical with negative length side" in {
    Triangle(1, 1, -1).triangleType should equal(TriangleType.Illogical)
  }
}
