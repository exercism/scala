import scala.annotation.tailrec

object RomanNumerals:

  private val Numerals = List(
    1000 -> "M",
    900  -> "CM",
    500  -> "D",
    400  -> "CD",
    100  -> "C",
    90   -> "XC",
    50   -> "L",
    40   -> "XL",
    10   -> "X",
    9    -> "IX",
    5    -> "V",
    4    -> "IV",
    1    -> "I",
  )

  def roman(n: Int): String = fromNumber(n, "")

  @tailrec
  private def fromNumber(n: Int, numerals: String): String =
    Numerals.find((value, _) => value <= n) match
      case Some((value, numeral)) => fromNumber(n - value, numerals + numeral)
      case None                   => numerals
