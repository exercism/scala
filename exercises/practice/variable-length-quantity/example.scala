import scala.annotation.tailrec

object VariableLengthQuantity {
  private val sevenBitsMask = 0x7f
  private val eightBitMask = 0x80

  def encode(n: Int): List[Int] = {
    @tailrec
    def encode_internal(n: Int, acc: List[Int]): List[Int] = {
      if (n == 0) {
        acc
      } else {
        var tmp = n & sevenBitsMask
        if (acc.nonEmpty) {
          tmp = tmp | eightBitMask
        }

        encode_internal(n >>> 7, tmp :: acc)
      }
    }

    if (n == 0)
      List(0)
    else
      encode_internal(n, List())
  }

  def encode(nums: List[Int]): List[Int] = {
    nums.foldRight(List[Int]())((n, acc) => encode(n) ::: acc)
  }

  def decode(encoded: List[Int]): Either[String, List[Int]] = {
    @tailrec
    def decode_internal(encoded: List[Int], tmp: Int, acc: List[Int]): Either[String, List[Int]] = {
      encoded match {
        case Nil => if (tmp == 0 && acc.nonEmpty)
          Right(acc.reverse)
        else
          Left("Incomplete sequence")
        case x::xs =>
          if ((tmp & 0xfe000000) > 0) {
            Left("Overflow")
          } else {
            val newTmp = (tmp << 7) | (x & sevenBitsMask)
            if ((x & eightBitMask) == 0) {
              decode_internal(xs, 0, newTmp :: acc)
            } else {
              decode_internal(xs, newTmp, acc)
            }
          }
      }
    }

    decode_internal(encoded, 0, List())
  }
}
