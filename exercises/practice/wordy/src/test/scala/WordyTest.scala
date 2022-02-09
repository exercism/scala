import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class WordyTest extends AnyFunSuite with Matchers {

  test("addition") {
    Wordy.answer("What is 1 plus 1?") should be(Some(2))
  }

  test("more addition") {
    pending
    Wordy.answer("What is 53 plus 2?") should be(Some(55))
  }

  test("addition with negative numbers") {
    pending
    Wordy.answer("What is -1 plus -10?") should be(Some(-11))
  }

  test("large addition") {
    pending
    Wordy.answer("What is 123 plus 45678?") should be(Some(45801))
  }

  test("subtraction") {
    pending
    Wordy.answer("What is 4 minus -12?") should be(Some(16))
  }

  test("multiplication") {
    pending
    Wordy.answer("What is -3 multiplied by 25?") should be(Some(-75))
  }

  test("division") {
    pending
    Wordy.answer("What is 33 divided by -3?") should be(Some(-11))
  }

  test("multiple additions") {
    pending
    Wordy.answer("What is 1 plus 1 plus 1?") should be(Some(3))
  }

  test("addition and subtraction") {
    pending
    Wordy.answer("What is 1 plus 5 minus -2?") should be(Some(8))
  }

  test("multiple subtraction") {
    pending
    Wordy.answer("What is 20 minus 4 minus 13?") should be(Some(3))
  }

  test("subtraction then addition") {
    pending
    Wordy.answer("What is 17 minus 6 plus 3?") should be(Some(14))
  }

  test("multiple multiplication") {
    pending
    Wordy.answer("What is 2 multiplied by -2 multiplied by 3?") should be(
      Some(-12))
  }

  test("addition and multiplication") {
    pending
    Wordy.answer("What is -3 plus 7 multiplied by -2?") should be(Some(-8))
  }

  test("multiple division") {
    pending
    Wordy.answer("What is -12 divided by 2 divided by -3?") should be(Some(2))
  }

  test("unknown operation") {
    pending
    Wordy.answer("What is 52 cubed?") should be(None)
  }

  test("Non math question") {
    pending
    Wordy.answer("Who is the President of the United States?") should be(None)
  }
}
