import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.4.0 */
class PhoneNumberTest extends AnyFunSuite with Matchers {

  test("cleans the number") {
    PhoneNumber.clean("(223) 456-7890") should be(Some("2234567890"))
  }

  test("cleans numbers with dots") {
    pending
    PhoneNumber.clean("223.456.7890") should be(Some("2234567890"))
  }

  test("cleans numbers with multiple spaces") {
    pending
    PhoneNumber.clean("223 456   7890   ") should be(Some("2234567890"))
  }

  test("invalid when 9 digits") {
    pending
    PhoneNumber.clean("123456789") should be(None)
  }

  test("invalid when 11 digits does not start with a 1") {
    pending
    PhoneNumber.clean("22234567890") should be(None)
  }

  test("valid when 11 digits and starting with 1") {
    pending
    PhoneNumber.clean("12234567890") should be(Some("2234567890"))
  }

  test("valid when 11 digits and starting with 1 even with punctuation") {
    pending
    PhoneNumber.clean("+1 (223) 456-7890") should be(Some("2234567890"))
  }

  test("invalid when more than 11 digits") {
    pending
    PhoneNumber.clean("321234567890") should be(None)
  }

  test("invalid with letters") {
    pending
    PhoneNumber.clean("123-abc-7890") should be(None)
  }

  test("invalid with punctuations") {
    pending
    PhoneNumber.clean("123-@:!-7890") should be(None)
  }

  test("invalid if area code starts with 0") {
    pending
    PhoneNumber.clean("(023) 456-7890") should be(None)
  }

  test("invalid if area code starts with 1") {
    pending
    PhoneNumber.clean("(123) 456-7890") should be(None)
  }

  test("invalid if exchange code starts with 0") {
    pending
    PhoneNumber.clean("(223) 056-7890") should be(None)
  }

  test("invalid if exchange code starts with 1") {
    pending
    PhoneNumber.clean("(223) 156-7890") should be(None)
  }
}
