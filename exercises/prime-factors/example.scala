import scala.annotation.tailrec

// Algorithm from - http://fldit-www.cs.uni-dortmund.de/~peter/PS07/HR.pdf
object PrimeFactors {
  def factors(n: Long): List[Long] = {
    assert(n >= 1, "Argument must be positive")

    @tailrec
    def primeAcc(n: Long, xs: List[Long]): List[Long] = {
      if (n == 1) xs
      else {
        val p = ld(n)
        primeAcc(n / p, p :: xs)
      }
    }

    primeAcc(n, List()).reverse
  }

  private def ld(n: Long) = ldf(2, n)

  @tailrec
  private def ldf(k: Long, n: Long): Long =
    if (n % k == 0) k
    else if (k * k > n) n
    else ldf(k + 1, n)
}


