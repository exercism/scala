object SumOfMultiples {
  def sum(factors: Set[Int], limit: Int): Int = {
    def isDivisibleBy(x: Int)(y: Int): Boolean = x % y == 0
    def isMultiple(x: Int): Boolean = factors exists isDivisibleBy(x)

    val multiples = (1 until limit) filter isMultiple
    multiples.sum
  }
}

