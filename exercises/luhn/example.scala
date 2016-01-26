case class Luhn(number: Long) {
  lazy val checkDigit: Int = (number % 10).toInt

  lazy val addends: List[Int] = {
    val zippedDigits = digits(number).zipWithIndex.reverse

    zippedDigits.map{case (m, i) => if (isOdd(i)) dbl(m) else m}
  }

  lazy val checksum: Int = addends.sum % 10

  lazy val isValid: Boolean = checksum % 10 == 0

  lazy val create: Long = {
    val m = number * 10
    val luhn = Luhn(m)

    if (luhn.isValid) m else m + (10 - luhn.checksum)
  }

  private def digits(n: Long): List[Int] = n match {
    case 0 => Nil
    case _ => List((n % 10).toInt) ++ digits(n / 10)
  }

  private def dbl(n: Int) = {
    val dbled = n * 2

    if (dbled > 10) dbled - 9 else dbled
  }

  private def isOdd(i: Int) = i % 2 == 1
}
