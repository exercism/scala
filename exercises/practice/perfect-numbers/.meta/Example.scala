enum NumberType:
  case Perfect, Abundant, Deficient

object PerfectNumbers:
  def classify(n: Int): Either[String, NumberType] =
    if n <= 0 then Left("Classification is only possible for natural numbers.")
    else
      val aliquotSum = (1 to n / 2).iterator.filter(n % _ == 0).sum
      if aliquotSum < n then Right(NumberType.Deficient)
      else if aliquotSum > n then Right(NumberType.Abundant)
      else Right(NumberType.Perfect)
