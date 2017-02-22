import NumberType.NumberType

object PerfectNumbers {
  def classify(n: Int): NumberType = {
    val sumOfFactors
    = (1 until n)
      .foldLeft(0)((acc, i) => if (n % i == 0) acc + i else acc)

    if (sumOfFactors < n)
      NumberType.Deficient
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
  val Deficient = Value("Deficient")
}