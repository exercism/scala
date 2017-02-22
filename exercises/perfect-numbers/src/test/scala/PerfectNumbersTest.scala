import org.scalatest.{FlatSpec, Matchers}

class PerfectNumbersTest extends FlatSpec with Matchers {
  it should "handle deficient - 3" in {
    PerfectNumbers.classify(3) should be (NumberType.Deficient)
  }

  it should "handle deficient - 7" in {
    pending
    PerfectNumbers.classify(7) should be (NumberType.Deficient)
  }

  it should "handle deficient - 13" in {
    pending
    PerfectNumbers.classify(13) should be (NumberType.Deficient)
  }

  it should "handle deficient - 33550337" in {
    pending
    PerfectNumbers.classify(33550337) should be (NumberType.Deficient)
  }

  it should "handle perfect - 6" in {
    pending
    PerfectNumbers.classify(6) should be (NumberType.Perfect)
  }

  it should "handle perfect - 28" in {
    pending
    PerfectNumbers.classify(28) should be (NumberType.Perfect)
  }

  it should "handle perfect - 33550336" in {
    pending
    PerfectNumbers.classify(33550336) should be (NumberType.Perfect)
  }

  it should "handle perfect - 496" in {
    pending
    PerfectNumbers.classify(496) should be (NumberType.Perfect)
  }

  it should "handle abundant - 12" in {
    pending
    PerfectNumbers.classify(12) should be (NumberType.Abundant)
  }

  it should "handle abundant - 18" in {
    pending
    PerfectNumbers.classify(18) should be (NumberType.Abundant)
  }

  it should "handle abundant - 20" in {
    pending
    PerfectNumbers.classify(20) should be (NumberType.Abundant)
  }

  it should "handle abundant - 33550335" in {
    pending
    PerfectNumbers.classify(33550335) should be (NumberType.Abundant)
  }
}
