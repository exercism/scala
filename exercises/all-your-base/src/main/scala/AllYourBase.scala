import scala.annotation.tailrec

object AllYourBase {
  def rebase(inputBase: Int, inputDigits: List[Int], outputBase: Int): Option[List[Int]] = {
    if(outputBase > 10 || inputBase > 10 || inputBase < 2 || outputBase < 2) None
    else {
      val powers = (inputDigits.size - 1) to 0
      val numDec =
        powers
          .zip(inputDigits)
          .foldLeft(0.0)((accum, current) => accum + (current._2 * math.pow(inputBase.toDouble, current._1.toDouble)))
          .toInt

      @tailrec
      def go(number: Int, accum: List[Int]): List[Int] =
        if (number == 0) accum.reverse
        else go(number / outputBase, (number % outputBase) :: accum)

      Some(go(numDec, Nil))
    }
  }
}
