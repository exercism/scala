import org.scalatest.{FunSuite, Matchers}

class PythagoreanTripletTest extends FunSuite with Matchers {
  test("isPythagorean") {
    PythagoreanTriplet.isPythagorean((3, 4, 5)) should be (true)
    PythagoreanTriplet.isPythagorean((3, 5, 4)) should be (true)
    PythagoreanTriplet.isPythagorean((4, 3, 5)) should be (true)
    PythagoreanTriplet.isPythagorean((4, 5, 3)) should be (true)
    PythagoreanTriplet.isPythagorean((5, 3, 4)) should be (true)
    PythagoreanTriplet.isPythagorean((5, 4, 3)) should be (true)
    PythagoreanTriplet.isPythagorean((3, 3, 3)) should be (false)
    PythagoreanTriplet.isPythagorean((5, 6, 7)) should be (false)
  }

  test("pythagoreanTriplets 1 to 10") {
    PythagoreanTriplet.pythagoreanTriplets(1, 10) should be (Seq((3, 4, 5), (6, 8, 10)))
  }

  test("pythagoreanTriplets 11 to 20") {
    PythagoreanTriplet.pythagoreanTriplets(11, 20) should be (Seq((12, 16, 20)))
  }

  test("pythagoreanTriplets 56 to 95") {
    PythagoreanTriplet.pythagoreanTriplets(56, 95) should be (Seq((57, 76, 95), (60, 63, 87)))
  }
}
