import org.scalatest.{Matchers, FlatSpec}

class PrimeTest extends FlatSpec with Matchers {
  it should "calculate 1st prime" in {
    Prime.nth(1) should be (Some(2))
  }

  it should "calculate 2nd prime" in {
    pending
    Prime.nth(2) should be (Some(3))
  }

  it should "calculate 6th prime" in {
    pending
    Prime.nth(6) should be (Some(13))
  }

  it should "calculate 1000th prime" in {
    pending
    Prime.nth(1000) should be (Some(7919))
  }

  it should "calculate 10000th prime" in {
    pending
    Prime.nth(10000) should be (Some(104729))
  }

  it should "calculate 10001th prime" in {
    pending
    Prime.nth(10001) should be (Some(104743))
  }

  it should "not find zeroth prime" in {
    pending
    Prime.nth(0) should be (None)
  }
}
