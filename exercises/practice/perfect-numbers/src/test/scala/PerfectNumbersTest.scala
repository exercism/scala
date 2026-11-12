import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class PerfectNumbersTest extends AnyFunSuite with Matchers:

  test("Perfect numbers - Smallest perfect number is classified correctly"):
    PerfectNumbers.classify(6) should be(Right(NumberType.Perfect))

  test("Perfect numbers - Medium perfect number is classified correctly"):
    pending
    PerfectNumbers.classify(28) should be(Right(NumberType.Perfect))

  test("Perfect numbers - Large perfect number is classified correctly"):
    pending
    PerfectNumbers.classify(33550336) should be(Right(NumberType.Perfect))

  test("Abundant numbers - Smallest abundant number is classified correctly"):
    pending
    PerfectNumbers.classify(12) should be(Right(NumberType.Abundant))

  test("Abundant numbers - Medium abundant number is classified correctly"):
    pending
    PerfectNumbers.classify(30) should be(Right(NumberType.Abundant))

  test("Abundant numbers - Large abundant number is classified correctly"):
    pending
    PerfectNumbers.classify(33550335) should be(Right(NumberType.Abundant))

  test("Abundant numbers - Perfect square abundant number is classified correctly"):
    pending
    PerfectNumbers.classify(196) should be(Right(NumberType.Abundant))

  test("Deficient numbers - Smallest prime deficient number is classified correctly"):
    pending
    PerfectNumbers.classify(2) should be(Right(NumberType.Deficient))

  test("Deficient numbers - Smallest non-prime deficient number is classified correctly"):
    pending
    PerfectNumbers.classify(4) should be(Right(NumberType.Deficient))

  test("Deficient numbers - Medium deficient number is classified correctly"):
    pending
    PerfectNumbers.classify(32) should be(Right(NumberType.Deficient))

  test("Deficient numbers - Large deficient number is classified correctly"):
    pending
    PerfectNumbers.classify(33550337) should be(Right(NumberType.Deficient))

  test("Deficient numbers - Edge case (no factors other than itself) is classified correctly"):
    pending
    PerfectNumbers.classify(1) should be(Right(NumberType.Deficient))

  test("Invalid inputs - Zero is rejected (as it is not a positive integer)"):
    pending
    PerfectNumbers.classify(0) should be(Left("Classification is only possible for natural numbers."))

  test("Invalid inputs - Negative integer is rejected (as it is not a positive integer)"):
    pending
    PerfectNumbers.classify(-1) should be(Left("Classification is only possible for natural numbers."))
