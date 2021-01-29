import org.scalatest.{Matchers, FunSuite}

/** @version 1.1.0 */
class PerfectNumbersTest extends FunSuite with Matchers {

  test("Smallest perfect number is classified correctly") {
    PerfectNumbers.classify(6) should be(Right(NumberType.Perfect))
  }

  test("Medium perfect number is classified correctly") {
    pending
    PerfectNumbers.classify(28) should be(Right(NumberType.Perfect))
  }

  test("Large perfect number is classified correctly") {
    pending
    PerfectNumbers.classify(33550336) should be(Right(NumberType.Perfect))
  }

  test("Smallest abundant number is classified correctly") {
    pending
    PerfectNumbers.classify(12) should be(Right(NumberType.Abundant))
  }

  test("Medium abundant number is classified correctly") {
    pending
    PerfectNumbers.classify(30) should be(Right(NumberType.Abundant))
  }

  test("Large abundant number is classified correctly") {
    pending
    PerfectNumbers.classify(33550335) should be(Right(NumberType.Abundant))
  }

  test("Smallest prime deficient number is classified correctly") {
    pending
    PerfectNumbers.classify(2) should be(Right(NumberType.Deficient))
  }

  test("Smallest non-prime deficient number is classified correctly") {
    pending
    PerfectNumbers.classify(4) should be(Right(NumberType.Deficient))
  }

  test("Medium deficient number is classified correctly") {
    pending
    PerfectNumbers.classify(32) should be(Right(NumberType.Deficient))
  }

  test("Large deficient number is classified correctly") {
    pending
    PerfectNumbers.classify(33550337) should be(Right(NumberType.Deficient))
  }

  test("Edge case (no factors other than itself) is classified correctly") {
    pending
    PerfectNumbers.classify(1) should be(Right(NumberType.Deficient))
  }

  test("Zero is rejected (not a natural number)") {
    pending
    PerfectNumbers.classify(0) should be(
      Left("Classification is only possible for natural numbers."))
  }

  test("Negative integer is rejected (not a natural number)") {
    pending
    PerfectNumbers.classify(-1) should be(
      Left("Classification is only possible for natural numbers."))
  }
}
