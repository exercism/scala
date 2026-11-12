object ArmstrongNumbers:
  def isArmstrongNumber(number: Int): Boolean =
    val digits = number.toString.map(_.asDigit)
    digits.map(BigInt(_).pow(digits.length)).sum == number
