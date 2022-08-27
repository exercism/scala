object PythagoreanTriplet {
  private def sqr(x: Int) = x * x

  private def sort(triplet: (Int, Int, Int)): (Int, Int, Int) =
    triplet match {
      case (a, b, c) if b < a => sort((b, a, c))
      case (a, b, c) if c < b => sort((a, c, b))
      case (a, b, c) => triplet
    }

  def isPythagorean(triplet: (Int, Int, Int)): Boolean = {
    val (a, b, c) = sort(triplet)
    sqr(a) + sqr(b) == sqr(c)
  }

  def pythagoreanTriplets(minFactor: Int, maxFactor:Int): Seq[(Int, Int, Int)] =
    for {a <- minFactor to maxFactor
         b <- a to maxFactor
         c2 = sqr(a) + sqr(b)
         c = Math.sqrt(c2).asInstanceOf[Int]
         if c <= maxFactor && c2 == sqr(c)} yield (a, b, c)

  def pythagoreanTripletsSum(sum: Int): Seq[(Int, Int, Int)] = {
    val max: Int = sum / 2
      for { a <- 1 to max
            b <- a to max
            c = sum - a - b
            if isPythagorean(a, b, c) } yield (a,b,c)
  }
}
