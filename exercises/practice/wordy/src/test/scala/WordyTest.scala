import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class WordyTest extends AnyFunSuite with Matchers:

  test("just a number"):
    Wordy.answer("What is 5?") should be(Some(5))

  test("just a zero"):
    pending
    Wordy.answer("What is 0?") should be(Some(0))

  test("just a negative number"):
    pending
    Wordy.answer("What is -123?") should be(Some(-123))

  test("addition"):
    pending
    Wordy.answer("What is 1 plus 1?") should be(Some(2))

  test("addition with a left hand zero"):
    pending
    Wordy.answer("What is 0 plus 2?") should be(Some(2))

  test("addition with a right hand zero"):
    pending
    Wordy.answer("What is 3 plus 0?") should be(Some(3))

  test("more addition"):
    pending
    Wordy.answer("What is 53 plus 2?") should be(Some(55))

  test("addition with negative numbers"):
    pending
    Wordy.answer("What is -1 plus -10?") should be(Some(-11))

  test("large addition"):
    pending
    Wordy.answer("What is 123 plus 45678?") should be(Some(45801))

  test("subtraction"):
    pending
    Wordy.answer("What is 4 minus -12?") should be(Some(16))

  test("multiplication"):
    pending
    Wordy.answer("What is -3 multiplied by 25?") should be(Some(-75))

  test("division"):
    pending
    Wordy.answer("What is 33 divided by -3?") should be(Some(-11))

  test("multiple additions"):
    pending
    Wordy.answer("What is 1 plus 1 plus 1?") should be(Some(3))

  test("addition and subtraction"):
    pending
    Wordy.answer("What is 1 plus 5 minus -2?") should be(Some(8))

  test("multiple subtraction"):
    pending
    Wordy.answer("What is 20 minus 4 minus 13?") should be(Some(3))

  test("subtraction then addition"):
    pending
    Wordy.answer("What is 17 minus 6 plus 3?") should be(Some(14))

  test("multiple multiplication"):
    pending
    Wordy.answer("What is 2 multiplied by -2 multiplied by 3?") should be(Some(-12))

  test("addition and multiplication"):
    pending
    Wordy.answer("What is -3 plus 7 multiplied by -2?") should be(Some(-8))

  test("multiple division"):
    pending
    Wordy.answer("What is -12 divided by 2 divided by -3?") should be(Some(2))

  test("unknown operation"):
    pending
    Wordy.answer("What is 52 cubed?") should be(None)

  test("Non math question"):
    pending
    Wordy.answer("Who is the President of the United States?") should be(None)

  test("reject problem missing an operand"):
    pending
    Wordy.answer("What is 1 plus?") should be(None)

  test("reject problem with no operands or operators"):
    pending
    Wordy.answer("What is?") should be(None)

  test("reject two operations in a row"):
    pending
    Wordy.answer("What is 1 plus plus 2?") should be(None)

  test("reject two numbers in a row"):
    pending
    Wordy.answer("What is 1 plus 2 1?") should be(None)

  test("reject postfix notation"):
    pending
    Wordy.answer("What is 1 2 plus?") should be(None)

  test("reject prefix notation"):
    pending
    Wordy.answer("What is plus 1 2?") should be(None)
