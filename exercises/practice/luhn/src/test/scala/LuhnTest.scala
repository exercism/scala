import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.2.0 */
class LuhnTest extends AnyFunSuite with Matchers {

  test("single digit strings can not be valid") {
    Luhn.valid("1") should be(false)
  }

  test("a single zero is invalid") {
    pending
    Luhn.valid("0") should be(false)
  }

  test("a simple valid SIN that remains valid if reversed") {
    pending
    Luhn.valid("059") should be(true)
  }

  test("a simple valid SIN that becomes invalid if reversed") {
    pending
    Luhn.valid("59") should be(true)
  }

  test("a valid Canadian SIN") {
    pending
    Luhn.valid("055 444 285") should be(true)
  }

  test("invalid Canadian SIN") {
    pending
    Luhn.valid("055 444 286") should be(false)
  }

  test("invalid credit card") {
    pending
    Luhn.valid("8273 1232 7352 0569") should be(false)
  }

  test("valid strings with a non-digit included become invalid") {
    pending
    Luhn.valid("055a 444 285") should be(false)
  }

  test("valid strings with punctuation included become invalid") {
    pending
    Luhn.valid("055-444-285") should be(false)
  }

  test("valid strings with symbols included become invalid") {
    pending
    Luhn.valid("055£ 444$ 285") should be(false)
  }

  test("single zero with space is invalid") {
    pending
    Luhn.valid(" 0") should be(false)
  }

  test("more than a single zero is valid") {
    pending
    Luhn.valid("0000 0") should be(true)
  }

  test("input digit 9 is correctly converted to output digit 9") {
    pending
    Luhn.valid("091") should be(true)
  }

  test("strings with non-digits is invalid") {
    pending
    Luhn.valid(":9") should be(false)
  }
}
