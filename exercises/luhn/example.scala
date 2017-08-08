object Luhn {

  def valid(numberStr: String): Boolean = {
    val s = numberStr.replace(" ", "")

    s.length > 1 &&
      s.forall(c => c.isDigit) &&
      checksum(s.map(_.asDigit).toList) == 0
  }

  private def checkDigit(number: Long): Int = (number % 10).toInt

  private def addends(digits: List[Int]): List[Int] = {
    val zippedDigits = digits.reverse.zipWithIndex

    zippedDigits.map{case (m, i) => if (isOdd(i)) dbl(m) else m}
  }

  private def checksum(digits: List[Int]): Int = addends(digits).sum % 10

  private def dbl(n: Int) = {
    val dbled = n * 2

    if (dbled < 10) dbled else dbled - 9
  }

  private def isOdd(i: Int) = i % 2 == 1
}
