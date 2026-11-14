object SumOfMultiples:
  // Collecting the multiples in a Set counts each one once. The factor 0 has no multiples in 1 until limit.
  def sum(factors: Set[Int], limit: Int): Int =
    factors.filter(_ > 0).flatMap(factor => factor until limit by factor).sum
