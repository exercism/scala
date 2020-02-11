import NumberType.NumberType

object PerfectNumbers {
  def classify(n: Int): Either[String, NumberType] = {
    if (n <= 0)
      Left("Classification is only possible for natural numbers.")
    else {
      val sumOfFactors
      = (1 until n)
        .foldLeft(0)((acc, i) => if (n % i == 0) acc + i else acc)

      if (sumOfFactors < n)
        Right(NumberType.Deficient)
      else if (sumOfFactors > n)
        Right(NumberType.Abundant)
      else
        Right(NumberType.Perfect)
    }
  }
}

object NumberType extends Enumeration {
  type NumberType = Value

  val Perfect = Value("Perfect")
  val Abundant = Value("Abundant")
  val Deficient = Value("Deficient")
}