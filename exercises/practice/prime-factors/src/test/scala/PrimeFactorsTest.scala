import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class PrimeFactorsTest extends AnyFunSuite with Matchers:

  test("no factors"):
    PrimeFactors.factors(1) should be(List())

  test("prime number"):
    pending
    PrimeFactors.factors(2) should be(List(2))

  test("another prime number"):
    pending
    PrimeFactors.factors(3) should be(List(3))

  test("square of a prime"):
    pending
    PrimeFactors.factors(9) should be(List(3, 3))

  test("product of first prime"):
    pending
    PrimeFactors.factors(4) should be(List(2, 2))

  test("cube of a prime"):
    pending
    PrimeFactors.factors(8) should be(List(2, 2, 2))

  test("product of second prime"):
    pending
    PrimeFactors.factors(27) should be(List(3, 3, 3))

  test("product of third prime"):
    pending
    PrimeFactors.factors(625) should be(List(5, 5, 5, 5))

  test("product of first and second prime"):
    pending
    PrimeFactors.factors(6) should be(List(2, 3))

  test("product of primes and non-primes"):
    pending
    PrimeFactors.factors(12) should be(List(2, 2, 3))

  test("product of primes"):
    pending
    PrimeFactors.factors(901255) should be(List(5, 17, 23, 461))

  test("factors include a large prime"):
    pending
    PrimeFactors.factors(93819012551L) should be(List(11, 9539, 894119))
