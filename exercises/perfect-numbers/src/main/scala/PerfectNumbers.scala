import NumberType.NumberType

object PerfectNumbers {
  def classify(n: Int): NumberType = {
    var sumOfFactors = 0

    for (i <- Range(1, n) if n % i == 0) {
      sumOfFactors = sumOfFactors + i
    }

    if (sumOfFactors < n)
      NumberType.Deficent
    else if (sumOfFactors > n)
      NumberType.Abundant
    else
      NumberType.Perfect
  }
}

object NumberType extends Enumeration {
  type NumberType = Value

  val Perfect = Value("Perfect")
  val Abundant = Value("Abundant")
  val Deficent = Value("Deficient")
}