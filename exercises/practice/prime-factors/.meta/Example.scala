import scala.annotation.tailrec

object PrimeFactors:
  // Trial division: a divisor that divides n is always prime, because every smaller prime has already been divided out.
  def factors(n: Long): List[Long] =
    @tailrec
    def loop(n: Long, divisor: Long, found: List[Long]): List[Long] =
      if n == 1 then found.reverse
      else if divisor * divisor > n then (n :: found).reverse
      else if n % divisor == 0 then loop(n / divisor, divisor, divisor :: found)
      else loop(n, divisor + 1, found)

    loop(n, 2, Nil)
