import scala.annotation.tailrec

object AllYourBase {
  def rebase(inputBase: Int, inputDigits: List[Int], outputBase: Int): Option[List[Int]] = {
    if(inputDigits.headOption.getOrElse(0) == 0 ||
        outputBase < 2 ||
        inputBase < 2 ||
        inputDigits.find(i => i < 0 || i >= inputBase).isDefined) None
    else {
      val powers = (inputDigits.size - 1) to 0 by -1
      val numDec =
        powers
          .zip(inputDigits)
          .foldLeft(0.0)((accum, current) => {
            val (power, digit) = current
            accum + (digit * math.pow(inputBase.toDouble, power.toDouble))
          })
          .toInt

      @tailrec
      def go(number: Int, accum: List[Int]): List[Int] =
        if (number == 0) accum
        else go(number / outputBase, (number % outputBase) :: accum)

      Some(go(numDec, Nil))
    }
  }
}
