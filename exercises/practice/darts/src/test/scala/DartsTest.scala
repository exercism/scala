import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.0.0 */
class DartsTest extends AnyFunSuite with Matchers {

  test("A dart lands outside the target") {
    Darts.score(15.3, 13.2) should be (0)
  }

  test("A dart lands just in the border of the target") {
    pending
    Darts.score(10, 0) should be (1)
  }

  test("A dart lands in the middle circle") {
    pending
    Darts.score(3, 3.7) should be (5)
  }

  test("A dart lands right in the border between outside and middle circles") {
    pending
    Darts.score(0, 5) should be (5)
  }

  test("A dart lands in the inner circle") {
    pending
    Darts.score(0, 0) should be (10)
  }
}