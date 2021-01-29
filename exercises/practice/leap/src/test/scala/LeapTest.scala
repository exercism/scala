import org.scalatest.{Matchers, FunSuite}

/** @version 1.3.0 */
class LeapTest extends FunSuite with Matchers {

  test("year not divisible by 4: common year") {
    Leap.leapYear(2015) should be (false)
  }

  test("year divisible by 4, not divisible by 100: leap year") {
    pending
    Leap.leapYear(1996) should be (true)
  }

  test("year divisible by 100, not divisible by 400: common year") {
    pending
    Leap.leapYear(2100) should be (false)
  }

  test("year divisible by 400: leap year") {
    pending
    Leap.leapYear(2000) should be (true)
  }
}
