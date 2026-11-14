object DifferenceOfSquares:

  def sumOfSquares(n: Int): Int = n * (n + 1) * (2 * n + 1) / 6

  def squareOfSum(n: Int): Int =
    val sum = n * (n + 1) / 2
    sum * sum

  def differenceOfSquares(n: Int): Int = squareOfSum(n) - sumOfSquares(n)
