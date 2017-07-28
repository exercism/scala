import scala.annotation.tailrec
import scala.collection.immutable.ListMap

object RomanNumerals {
  private def numeralValues = ListMap(
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
    1    -> "I"
  )

  def roman(n: Int) = fromNumber(n, "")

  @tailrec
  private def fromNumber(n: Int, numerals: String): String = {
    numeralValues.find(_._1 <= n) match {
      case Some((threshold, numeral)) => fromNumber(n - threshold, numerals + numeral)
      case None => numerals
    }
  }
}
