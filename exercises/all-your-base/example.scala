import scala.annotation.tailrec

object AllYourBase {
  def rebase(inputBase: Int, inputDigits: List[Int], outputBase: Int): Option[List[Int]] = {
    if (inputBase < 2 || outputBase < 2)
      None
    else if (inputDigits.isEmpty)
      Some(List(0))
    else {
      fromDigits(0, inputBase, inputDigits) match {
        case None => None
        case x if x.sum == 0 => Some(List(0))
        case Some(x) => Some(toDigits(outputBase, x, List()))
      }
    }
  }

  @tailrec
  private def fromDigits(acc: Int, base: Int, digits: List[Int]): Option[Int] = {
    digits match {
      case x::xs => if (x >= 0 && x < base)
        fromDigits(acc * base + x, base, xs)
      else
        None
      case Nil => Option(acc)
    }
  }

  @tailrec
  private def toDigits(base: Int, x: Int, acc: List[Int]): List[Int] = {
    x match {
      case 0 => acc
      case _ => toDigits(base, x / base, x % base :: acc)
    }
  }
}
