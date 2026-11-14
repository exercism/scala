object PythagoreanTriplet:
  // From a + b + c = n and a² + b² = c² follows b = n(n - 2a) / 2(n - a), so each a gives at most one triplet.
  def pythagoreanTripletsSum(n: Int): Seq[(Int, Int, Int)] =
    for
      a          <- 1 until n / 3
      numerator   = n.toLong * (n - 2 * a)
      denominator = 2L * (n - a)
      if numerator % denominator == 0
      b = (numerator / denominator).toInt
      if a < b
    yield (a, b, n - a - b)
