import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class LuhnTest extends AnyFunSuite with Matchers:

  test("single digit strings can not be valid"):
    Luhn.valid("1") should be(false)

  test("a single zero is invalid"):
    pending
    Luhn.valid("0") should be(false)

  test("a simple valid SIN that remains valid if reversed"):
    pending
    Luhn.valid("059") should be(true)

  test("a simple valid SIN that becomes invalid if reversed"):
    pending
    Luhn.valid("59") should be(true)

  test("a valid Canadian SIN"):
    pending
    Luhn.valid("055 444 285") should be(true)

  test("invalid Canadian SIN"):
    pending
    Luhn.valid("055 444 286") should be(false)

  test("invalid credit card"):
    pending
    Luhn.valid("8273 1232 7352 0569") should be(false)

  test("invalid long number with an even remainder"):
    pending
    Luhn.valid("1 2345 6789 1234 5678 9012") should be(false)

  test("invalid long number with a remainder divisible by 5"):
    pending
    Luhn.valid("1 2345 6789 1234 5678 9013") should be(false)

  test("valid number with an even number of digits"):
    pending
    Luhn.valid("095 245 88") should be(true)

  test("valid number with an odd number of spaces"):
    pending
    Luhn.valid("234 567 891 234") should be(true)

  test("valid strings with a non-digit added at the end become invalid"):
    pending
    Luhn.valid("059a") should be(false)

  test("valid strings with punctuation included become invalid"):
    pending
    Luhn.valid("055-444-285") should be(false)

  test("valid strings with symbols included become invalid"):
    pending
    Luhn.valid("055# 444$ 285") should be(false)

  test("single zero with space is invalid"):
    pending
    Luhn.valid(" 0") should be(false)

  test("more than a single zero is valid"):
    pending
    Luhn.valid("0000 0") should be(true)

  test("input digit 9 is correctly converted to output digit 9"):
    pending
    Luhn.valid("091") should be(true)

  test("very long input is valid"):
    pending
    Luhn.valid("9999999999 9999999999 9999999999 9999999999") should be(true)

  test("valid luhn with an odd number of digits and non zero first digit"):
    pending
    Luhn.valid("109") should be(true)

  test("using ascii value for non-doubled non-digit isn't allowed"):
    pending
    Luhn.valid("055b 444 285") should be(false)

  test("using ascii value for doubled non-digit isn't allowed"):
    pending
    Luhn.valid(":9") should be(false)

  test("non-numeric, non-space char in the middle with a sum that's divisible by 10 isn't allowed"):
    pending
    Luhn.valid("59%59") should be(false)
