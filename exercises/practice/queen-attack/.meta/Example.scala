case class Queen(row: Int, column: Int)

object Queen:
  def create(row: Int, column: Int): Option[Queen] =
    Option.when(0 <= row && row < 8 && 0 <= column && column < 8)(Queen(row, column))

object QueenAttack:
  def canAttack(white: Queen, black: Queen): Boolean =
    val rows    = (white.row - black.row).abs
    val columns = (white.column - black.column).abs
    rows == 0 || columns == 0 || rows == columns
