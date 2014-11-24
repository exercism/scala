import org.scalatest.{FlatSpec, Matchers}

class PrimeFactorsTest extends FlatSpec with Matchers {
  private val calc = PrimeFactors()

  it should "calc empty list" in {
    calc.primeFactors(1) should be (List())
  }

  it should "calc prime factors of 2" in {
    calc.primeFactors(2) should be (List(2))
  }

  it should "calc prime factors of 3" in {
    calc.primeFactors(3) should be (List(3))
  }

  it should "calc prime factors of 4" in {
    calc.primeFactors(4) should be (List(2, 2))
  }

  it should "calc prime factors of 6" in {
    calc.primeFactors(6) should be (List(2, 3))
  }

  it should "calc prime factors of 8" in {
    calc.primeFactors(8) should be (List(2, 2, 2))
  }

  it should "calc prime factors of 9" in {
    calc.primeFactors(9) should be (List(3, 3))
  }

  it should "calc prime factors of 27" in {
    calc.primeFactors(27) should be (List(3, 3, 3))
  }

  it should "calc prime factors of 625" in {
    calc.primeFactors(625) should be (List(5, 5, 5, 5))
  }

  it should "calc prime factors of 901255" in {
    calc.primeFactors(901255) should be (List(5, 17, 23, 461))
  }

  it should "calc prime factors of 93819012551" in {
    calc.primeFactors(Long.MaxValue) should be (List(7, 7, 73, 127, 337, 92737, 649657))
  }
}

