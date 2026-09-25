import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.2.0 */
class SumOfMultiplesTest extends AnyFunSuite with Matchers {

  test("no multiples within limit") {
    SumOfMultiples.sum(Set(3, 5), 1) should be(0)
  }

  test("one factor has multiples within limit") {
    pending
    SumOfMultiples.sum(Set(3, 5), 4) should be(3)
  }

  test("more than one multiple within limit") {
    pending
    SumOfMultiples.sum(Set(3), 7) should be(9)
  }

  test("more than one factor with multiples within limit") {
    pending
    SumOfMultiples.sum(Set(3, 5), 10) should be(23)
  }

  test("each multiple is only counted once") {
    pending
    SumOfMultiples.sum(Set(3, 5), 100) should be(2318)
  }

  test("a much larger limit") {
    pending
    SumOfMultiples.sum(Set(3, 5), 1000) should be(233168)
  }

  test("three factors") {
    pending
    SumOfMultiples.sum(Set(7, 13, 17), 20) should be(51)
  }

  test("factors not relatively prime") {
    pending
    SumOfMultiples.sum(Set(4, 6), 15) should be(30)
  }

  test("some pairs of factors relatively prime and some not") {
    pending
    SumOfMultiples.sum(Set(5, 6, 8), 150) should be(4419)
  }

  test("one factor is a multiple of another") {
    pending
    SumOfMultiples.sum(Set(5, 25), 51) should be(275)
  }

  test("much larger factors") {
    pending
    SumOfMultiples.sum(Set(43, 47), 10000) should be(2203160)
  }

  test("all numbers are multiples of 1") {
    pending
    SumOfMultiples.sum(Set(1), 100) should be(4950)
  }

  test("no factors means an empty sum") {
    pending
    SumOfMultiples.sum(Set(), 10000) should be(0)
  }

  test("the only multiple of 0 is 0") {
    pending
    SumOfMultiples.sum(Set(0), 1) should be(0)
  }

  test("the factor 0 does not affect the sum of multiples of other factors") {
    pending
    SumOfMultiples.sum(Set(3, 0), 4) should be(3)
  }

  test("solutions using include-exclude must extend to cardinality greater than 3") {
    pending
    SumOfMultiples.sum(Set(2, 3, 5, 7, 11), 10000) should be(39614537)
  }
}
