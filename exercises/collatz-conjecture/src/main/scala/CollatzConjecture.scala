import scala.annotation.tailrec

object CollatzConjecture {
  def steps(n: Int): Option[Int] = {
    @tailrec
    def go(n: Int, accum: Int): Int = {
      println(s"go($n,$accum)")
      if (n == 1) accum
      else if (n % 2 == 0) go(n / 2, accum + 1)
      else go((3 * n) + 1, accum + 1)
    }
    if(n <= 0) None
    else Some(go(n, 0))
  }
}
