import scala.annotation.tailrec

class Accumulate {
  def accumulate[A, B](f: (A) => B, list : List[A]): List[B] = list match {
    case Nil => Nil
    case h :: t => f(h) :: accumulate(f, t)
  }

  def accumulateTailRecursive[A, B](f: (A) => B, list : List[A]): List[B] = {
    @tailrec
    def go(result: List[B], remaining: List[A]): List[B] = remaining match {
      case Nil => result.reverse
      case h :: t => go(f(h) :: result, t)
    }
    go(Nil, list)
  }
}
