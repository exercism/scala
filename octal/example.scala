import scala.annotation.tailrec

object Octal {
  def octalToInt(s: String): Int = {
    if (s.isEmpty) throw new IllegalArgumentException("Invalid Octal")

    s.foldLeft(0){case (acc, c) => accumulate(acc, c)}
  }

  private def accumulate(acc: Int, c: Char) = {
    if (!c.isDigit || c.asDigit < 0 || c.asDigit > 7)
      throw new IllegalArgumentException("Invalid Octal Char - " + c)

    c.asDigit + (acc * 8)
  }

  def intToOctal(n: Int): String = {
    if (n < 0) throw new IllegalArgumentException("Only positive numbers supported")

    @tailrec
    def intToOct(n: Int, acc: String): String = {
      val octalChar = ((n % 8) + '0').toChar
      val accum = octalChar + acc

      n match {
        case 0 => if (acc.isEmpty) accum else acc
        case _ => intToOct(n / 8, accum)
      }
    }

    intToOct(n, "")
  }
}
