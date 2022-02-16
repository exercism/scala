class Board(rows: List[String]) {
  lazy val annotate: List[String] =
    (for (y <- 0 until numRows) yield annotateRow(y)).toList

  private lazy val numRows = rows.length
  private lazy val numCols = if (rows.isEmpty) 0 else rows.head.length
  private lazy val boardVector = rows.toVector.map(_.toVector)

  private def annotateRow(y: Int) =
    (for (x <- 0 until numCols) yield annotateSquare(x, y)).mkString

  private def annotateSquare(x: Int, y: Int) =
    if (hasMine(x, y)) mineChar
    else neighborsWithMines(x, y) match {
      case 0 => ' '
      case n => (n + '0').toChar
    }

  private def neighborsWithMines(x: Int, y: Int) = (for (
    yNeighbor <- Math.max(0, y - 1) to Math.min(numRows - 1, y + 1);
    xNeighbor <- Math.max(0, x - 1) to Math.min(numCols - 1, x + 1)
      if (yNeighbor != y || xNeighbor != x) && hasMine(xNeighbor, yNeighbor)
  ) yield ()).length

  private def hasMine(x: Int, y: Int) = isMineChar(boardVector(y)(x))
  private def isMineChar(c: Char) = c == mineChar
  private val mineChar = '*'
}

object Minesweeper {
  def annotate(board: List[String]): List[String] = new Board(board).annotate
}
