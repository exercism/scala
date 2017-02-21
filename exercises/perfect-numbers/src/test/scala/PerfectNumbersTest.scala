import org.scalacheck.Gen
import org.scalatest.prop.PropertyChecks
import org.scalatest.{Matchers, PropSpec}

class PerfectNumbersTest extends PropSpec with Matchers with PropertyChecks {

  property("deficient numbers") {
    val deficientVals = Gen.oneOf(3, 7, 15, 33550337)
    forAll (deficientVals) {
      n: Int => PerfectNumbers.classify(n) should be (NumberType.Deficient)
    }
  }

  property("perfect numbers") {
    pending
    val perfectVals = Gen.oneOf(6, 28, 496, 33550336)
    forAll (perfectVals) {
      n: Int => PerfectNumbers.classify(n) should be (NumberType.Perfect)
    }
  }

  property("abundant numbers") {
    pending
    val abundantVals = Gen.oneOf(12, 18, 20, 33550335)
    forAll (abundantVals) {
      n: Int => PerfectNumbers.classify(n) should be (NumberType.Abundant)
    }
  }
}
