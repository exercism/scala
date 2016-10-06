import org.scalatest.{FlatSpec, Matchers}

class PrimeFactorsTest extends FlatSpec with Matchers {
  it should "calc empty list" in {
    PrimeFactors.forNumber(1) should be (List())
  }

  it should "calc prime factors of 2" in {
    PrimeFactors.forNumber(2) should be (List(2))
  }

  it should "calc prime factors of 3" in {
    PrimeFactors.forNumber(3) should be (List(3))
  }

  it should "calc prime factors of 4" in {
    PrimeFactors.forNumber(4) should be (List(2, 2))
  }

  it should "calc prime factors of 6" in {
    PrimeFactors.forNumber(6) should be (List(2, 3))
  }

  it should "calc prime factors of 8" in {
    PrimeFactors.forNumber(8) should be (List(2, 2, 2))
  }

  it should "calc prime factors of 9" in {
    PrimeFactors.forNumber(9) should be (List(3, 3))
  }

  it should "calc prime factors of 27" in {
    PrimeFactors.forNumber(27) should be (List(3, 3, 3))
  }

  it should "calc prime factors of 625" in {
    PrimeFactors.forNumber(625) should be (List(5, 5, 5, 5))
  }

  it should "calc prime factors of 901255" in {
    PrimeFactors.forNumber(901255) should be (List(5, 17, 23, 461))
  }

  it should "calc prime factors of 93819012551" in {
    PrimeFactors.forNumber(Long.MaxValue) should be (List(7, 7, 73, 127, 337, 92737, 649657))
  }
}

