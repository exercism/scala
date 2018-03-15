import org.scalatest.{Matchers, FunSuite}

/** @version 1.2.0 */
class SumOfMultiplesTest extends FunSuite with Matchers {

  test("multiples of 3 or 5 up to 1") {
    SumOfMultiples.sum(Set(3, 5), 1) should be(0)
  }

  test("multiples of 3 or 5 up to 4") {
    pending
    SumOfMultiples.sum(Set(3, 5), 4) should be(3)
  }

  test("multiples of 3 up to 7") {
    pending
    SumOfMultiples.sum(Set(3), 7) should be(9)
  }

  test("multiples of 3 or 5 up to 10") {
    pending
    SumOfMultiples.sum(Set(3, 5), 10) should be(23)
  }

  test("multiples of 3 or 5 up to 100") {
    pending
    SumOfMultiples.sum(Set(3, 5), 100) should be(2318)
  }

  test("multiples of 3 or 5 up to 1000") {
    pending
    SumOfMultiples.sum(Set(3, 5), 1000) should be(233168)
  }

  test("multiples of 7, 13 or 17 up to 20") {
    pending
    SumOfMultiples.sum(Set(7, 13, 17), 20) should be(51)
  }

  test("multiples of 4 or 6 up to 15") {
    pending
    SumOfMultiples.sum(Set(4, 6), 15) should be(30)
  }

  test("multiples of 5, 6 or 8 up to 150") {
    pending
    SumOfMultiples.sum(Set(5, 6, 8), 150) should be(4419)
  }

  test("multiples of 5 or 25 up to 51") {
    pending
    SumOfMultiples.sum(Set(5, 25), 51) should be(275)
  }

  test("multiples of 43 or 47 up to 10000") {
    pending
    SumOfMultiples.sum(Set(43, 47), 10000) should be(2203160)
  }

  test("multiples of 1 up to 100") {
    pending
    SumOfMultiples.sum(Set(1), 100) should be(4950)
  }

  test("multiples of an empty list up to 10000") {
    pending
    SumOfMultiples.sum(Set(), 10000) should be(0)
  }
}
