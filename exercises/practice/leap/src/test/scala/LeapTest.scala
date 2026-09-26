import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.3.0 */
class LeapTest extends AnyFunSuite with Matchers {

  test("year not divisible by 4 in common year") {
    Leap.leapYear(2015) should be (false)
  }

  test("year divisible by 2, not divisible by 4 in common year") {
    pending
    Leap.leapYear(1970) should be (false)
  }

  test("year divisible by 4, not divisible by 100 in leap year") {
    pending
    Leap.leapYear(1996) should be (true)
  }

  test("year divisible by 4 and 5 is still a leap year") {
    pending
    Leap.leapYear(1960) should be (true)
  }

  test("year divisible by 100, not divisible by 400 in common year") {
    pending
    Leap.leapYear(2100) should be (false)
  }

  test("year divisible by 100 but not by 3 is still not a leap year") {
    pending
    Leap.leapYear(1900) should be (false)
  }

  test("year divisible by 400 is leap year") {
    pending
    Leap.leapYear(2000) should be (true)
  }

  test("year divisible by 400 but not by 125 is still a leap year") {
    pending
    Leap.leapYear(2400) should be (true)
  }

  test("year divisible by 200, not divisible by 400 in common year") {
    pending
    Leap.leapYear(1800) should be (false)
  }
}
