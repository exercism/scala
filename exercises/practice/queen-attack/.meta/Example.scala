object QueenAttack {
  def canAttack(white: Queen, black: Queen): Boolean = {
    val canAttackHoriz = white.x == black.x
    val canAttackVert = white.y == black.y
    val deltaRow = Math.abs(white.x - black.x)
    val deltaCol = Math.abs(white.y - black.y)
    val canAttackDiag = deltaRow == deltaCol

    canAttackHoriz || canAttackVert || canAttackDiag
  }
}

case class Queen(x: Int, y: Int)

object Queen {
  def create(x: Int, y: Int): Option[Queen] = {
    val min = 0
    val max = 7
    if (x >= min && x <= max && y >= min && y <= max)
      Some(Queen(x, y))
    else
      None
  }
}

