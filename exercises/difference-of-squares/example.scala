import scala.math.pow

object DifferenceOfSquares {

  def sumOfSquares(n: Int): Int = n * (n + 1) * (2 * n + 1) / 6

  def squareOfSum(n: Int): Int = pow((n * (n + 1) / 2), 2).toInt

  def differenceOfSquares(n: Int): Int = squareOfSum(n) - sumOfSquares(n)
}
