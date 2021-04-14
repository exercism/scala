import scala.annotation.tailrec

object CollatzConjecture {

  def isEven(v: Int): Boolean = v % 2 == 0

  @tailrec
  def collatz_h(n: Int, acc: Int): Option[Int] = {
    if (n <= 0)
      None
    else if (n == 1)
      Some(acc)
    else if (isEven(n))
      collatz_h(n / 2, acc + 1)
    else
      collatz_h(n * 3 + 1, acc + 1)
  }

  def steps(n: Int): Option[Int] = collatz_h(n, 0)
}
