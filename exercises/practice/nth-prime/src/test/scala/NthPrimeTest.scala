import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 2.1.0 */
class NthPrimeTest extends AnyFunSuite with Matchers {
  
  test("first prime") {
    NthPrime.prime(1) should be (Some(2))
  }

  test("second prime") {
    pending
    NthPrime.prime(2) should be (Some(3))
  }

  test("sixth prime") {
    pending
    NthPrime.prime(6) should be (Some(13))
  }

  test("big prime") {
    pending
    NthPrime.prime(10001) should be (Some(104743))
  }

  test("there is no zeroth prime") {
    pending
    NthPrime.prime(0) should be (None)
  }
}