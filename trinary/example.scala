import scala.annotation.tailrec

trait BaseConverter {
  def baseToInt(base: Int, s: String): Int = {
    if (s.isEmpty) throw new IllegalArgumentException("Invalid base string")

    s.foldLeft(0){case (acc, c) => accumulate(base, acc, c)}
  }

  private def accumulate(base: Int, acc: Int, c: Char) = {
    if (base <= 0) throw new IllegalArgumentException("Invalid base")
    if (!c.isDigit || c.asDigit < 0 || c.asDigit >= base)
      throw new IllegalArgumentException("Invalid base char - " + c)

    c.asDigit + (acc * base)
  }

  def intToBase(base: Int, n: Int): String = {
    if (base <= 0) throw new IllegalArgumentException("Invalid base")
    if (n < 0) throw new IllegalArgumentException("Only positive numbers supported")

    @tailrec
    def internalToBase(n: Int, acc: String): String = {
      val baseChar = ((n % base) + '0').toChar
      val accum = baseChar + acc

      n match {
        case 0 => if (acc.isEmpty) accum else acc
        case _ => internalToBase(n / base, accum)
      }
    }

    internalToBase(n, "")
  }
}

object Trinary extends BaseConverter {
  private val base = 3

  def trinaryToInt(s: String): Int = baseToInt(base, s)

  def intToTrinary(n: Int): String = intToBase(base, n)
}
