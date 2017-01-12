object Luhn {

  def validate(numberStr: String): Boolean = {
    def isCandidate(numberStr: String): Boolean =
      numberStr.length > 1 &&
      numberStr.forall(c => c.isDigit || c.isSpaceChar)

    def toLong(numberStr: String): Long =
      numberStr.filter(_.isDigit).toLong

    val number =
      Option(numberStr) filter isCandidate map toLong

    number map (checksum(_) == 0) getOrElse false
  }

  private def checkDigit(number: Long): Int = (number % 10).toInt

  private def addends(number: Long): List[Int] = {
    val zippedDigits = digits(number).zipWithIndex.reverse

    zippedDigits.map{case (m, i) => if (isOdd(i)) dbl(m) else m}
  }

  private def checksum(number: Long): Int = addends(number).sum % 10

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

