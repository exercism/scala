import scala.annotation.tailrec

object VariableLengthQuantity:

  private val Payload      = 0x7f
  private val Continuation = 0x80

  def encode(numbers: List[Int]): List[Int] = numbers.flatMap(encodeOne)

  def decode(bytes: List[Int]): Either[String, List[Int]] =
    if bytes.lastOption.forall(_ >= Continuation) then Left("incomplete sequence")
    else
      val (numbers, _) = bytes.foldLeft((List.empty[Int], 0)):
        case ((numbers, value), byte) =>
          val next = (value << 7) | (byte & Payload)
          if byte < Continuation then (next :: numbers, 0) else (numbers, next)
      Right(numbers.reverse)

  /** The last byte holds the lowest 7 bits; every byte before it has the continuation bit set. */
  private def encodeOne(number: Int): List[Int] =
    @tailrec
    def loop(rest: Int, bytes: List[Int]): List[Int] =
      if rest == 0 then bytes else loop(rest >>> 7, ((rest & Payload) | Continuation) :: bytes)

    loop(number >>> 7, List(number & Payload))
