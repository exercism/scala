import scala.annotation.tailrec

object Grains {
  type Grains = BigInt
  type ChessboardSquare = Int

  private val ChessboardSquares = 64

  def square(cbSquare: ChessboardSquare): Option[Grains] = {
    def isValidChessboardSquare(n: Int) =
      1 <= n && n <= ChessboardSquares

    def powerOfTwo(n: Int): BigInt = {
      @tailrec
      def loop(n: Int, acc: BigInt): BigInt =
        if (n == 0) acc
        else loop(n - 1, acc * 2)

      loop(n, 1)
    }

    val pred = (_:Int) - 1

    Option(cbSquare) filter isValidChessboardSquare map pred.andThen(powerOfTwo)
  }

  def total: Grains =
    ((1 to ChessboardSquares) flatMap (square _).andThen(_.toList)).sum
}
