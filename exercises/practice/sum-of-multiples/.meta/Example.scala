object SumOfMultiples {
  def sum(factors: Set[Int], limit: Int): Int = {
    // 0 has no multiples in 1 until limit, and x % 0 throws
    def isDivisibleBy(x: Int)(y: Int): Boolean = y != 0 && x % y == 0
    def isMultiple(x: Int): Boolean = factors exists isDivisibleBy(x)

    val multiples = (1 until limit) filter isMultiple
    multiples.sum
  }
}

