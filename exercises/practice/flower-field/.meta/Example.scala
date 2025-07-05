class Board(rows: List[String]) {
  lazy val annotate: List[String] =
    (for (y <- 0 until numRows) yield annotateRow(y)).toList

  private lazy val numRows = rows.length
  private lazy val numCols = if (rows.isEmpty) 0 else rows.head.length
  private lazy val boardVector = rows.toVector.map(_.toVector)

  private def annotateRow(y: Int) =
    (for (x <- 0 until numCols) yield annotateSquare(x, y)).mkString

  private def annotateSquare(x: Int, y: Int) =
    if (hasFlower(x, y)) flowerChar
    else neighborsWithFlowers(x, y) match {
      case 0 => ' '
      case n => (n + '0').toChar
    }

  private def neighborsWithFlowers(x: Int, y: Int) = (for (
    yNeighbor <- Math.max(0, y - 1) to Math.min(numRows - 1, y + 1);
    xNeighbor <- Math.max(0, x - 1) to Math.min(numCols - 1, x + 1)
      if (yNeighbor != y || xNeighbor != x) && hasFlower(xNeighbor, yNeighbor)
  ) yield ()).length

  private def hasFlower(x: Int, y: Int) = isFlowerChar(boardVector(y)(x))
  private def isFlowerChar(c: Char) = c == flowerChar
  private val flowerChar = '*'
}

object FlowerField {
  def annotate(board: List[String]): List[String] = new Board(board).annotate
}
