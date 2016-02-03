import scala.math.pow

class Squares {

  def sumOfSquares(n: Int): Int = n * (n + 1) * (2 * n + 1) / 6

  def squareOfSums(n: Int): Int = pow((n * (n + 1) / 2), 2).toInt

  def difference(n: Int): Int = squareOfSums(n) - sumOfSquares(n)
}

object Squares {
  def apply() = new Squares
}
