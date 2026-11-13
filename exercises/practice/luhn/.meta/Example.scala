object Luhn:

  def valid(input: String): Boolean =
    val digits = input.filterNot(_ == ' ')
    digits.length > 1 && digits.forall(_.isDigit) && checksum(digits) % 10 == 0

  private def checksum(digits: String): Int =
    digits.reverseIterator.map(_.asDigit).zipWithIndex.map((digit, i) => if i % 2 == 1 then double(digit) else digit).sum

  private def double(digit: Int): Int = if digit > 4 then digit * 2 - 9 else digit * 2
