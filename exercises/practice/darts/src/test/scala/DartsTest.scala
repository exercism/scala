import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.0.0 */
class DartsTest extends AnyFunSuite with Matchers {

  test("Missed target") {
    Darts.score(-9, 9) should be (0)
  }

  test("On the outer circle") {
    pending
    Darts.score(0, 10) should be (1)
  }

  test("On the middle circle") {
    pending
    Darts.score(-5, 0) should be (5)
  }

  test("On the inner circle") {
    pending
    Darts.score(0, -1) should be (10)
  }

  test("Exactly on center") {
    pending
    Darts.score(0, 0) should be (10)
  }

  test("Near the center") {
    pending
    Darts.score(-0.1, -0.1) should be (10)
  }

  test("Just within the inner circle") {
    pending
    Darts.score(0.7, 0.7) should be (10)
  }

  test("Just outside the inner circle") {
    pending
    Darts.score(0.8, -0.8) should be (5)
  }

  test("Just within the middle circle") {
    pending
    Darts.score(-3.5, 3.5) should be (5)
  }

  test("Just outside the middle circle") {
    pending
    Darts.score(-3.6, -3.6) should be (1)
  }

  test("Just within the outer circle") {
    pending
    Darts.score(-7.0, 7.0) should be (1)
  }

  test("Just outside the outer circle") {
    pending
    Darts.score(7.1, -7.1) should be (0)
  }

  test("Asymmetric position between the inner and middle circles") {
    pending
    Darts.score(0.5, -4) should be (5)
  }
}
