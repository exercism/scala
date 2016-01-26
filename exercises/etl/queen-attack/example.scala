case class Queens() {

  def boardString(white: Option[Position], black: Option[Position]): String = {

    def rowString(row: Int): String =
      (0 until 8).flatMap(col => tileString(row, col)).mkString

    def tileString(row: Int, col: Int): String =
      tileChar(Position(row, col)).toString + tileSep(col)

    def tileChar(pos: Position): Char =
      Some(pos) match {
        case `white` => 'W'
        case `black` => 'B'
        case _ => '_'
      }

    def tileSep(col: Int) =
      if (col == 7) '\n'
      else ' '

    (0 until 8).flatMap(row => rowString(row)).mkString
  }

  def canAttack(white: Position, black: Position): Boolean = {
    val canAttackHoriz = white.x == black.x
    val canAttackVert = white.y == black.y
    val deltaRow = Math.abs(white.x - black.x)
    val deltaCol = Math.abs(white.y - black.y)
    val canAttackDiag = deltaRow == deltaCol

    canAttackHoriz || canAttackVert || canAttackDiag
  }
}

case class Position(x: Int, y: Int)
