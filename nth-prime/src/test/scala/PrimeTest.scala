import org.scalatest.{Matchers, FlatSpec}

class PrimeTest extends FlatSpec with Matchers {
  it should "calculate 1st prime" in {
    Prime.nth(1) should be (2)
  }

  it should "calculate 2nd prime" in {
    Prime.nth(2) should be (3)
  }

  it should "calculate 6th prime" in {
    Prime.nth(6) should be (13)
  }

  it should "calculate 1000th prime" in {
    Prime.nth(1000) should be (7919)
  }

  it should "calculate 10000th prime" in {
    Prime.nth(10000) should be (104729)
  }

  it should "calculate 10001th prime" in {
    Prime.nth(10001) should be (104743)
  }
}
