object Queens {

  def boardString(white: Option[Position], black: Option[Position]): String = ???

  def canAttack(white: Position, black: Position): Boolean = ???
}

case class Position(x: Int, y: Int)
