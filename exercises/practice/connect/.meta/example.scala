import Color.Color

import scala.collection.mutable

object Color extends Enumeration {
  type Color = Value
  val White, Black = Value
}

case class Connect(lines: List[String]) {
  require(lines.size > 0)
  require(lines.head.length > 0)

  private val height = lines.size
  private val width = lines.head.length

  private val board = lines.map(line => line.map {
      case 'O' => Some(Color.White)
      case 'X' => Some(Color.Black)
      case _ => None
    }.toVector).toVector

  private val blackStartCoords = (0 until height).map((0, _))
  private val whiteStartCoords = (0 until width).map((_, 0))

  private def isGoalEdge(coord: (Int, Int), color: Color) = color match {
    case Color.White => coord._2 == height - 1
    case _ => coord._1 == width - 1
  }

  private def neighbors(coord: (Int, Int)) = List(
    (coord._1 + 1, coord._2),
    (coord._1 - 1, coord._2),
    (coord._1, coord._2 + 1),
    (coord._1, coord._2 - 1),
    (coord._1 - 1, coord._2 + 1),
    (coord._1 + 1, coord._2 - 1)).filter{case (x, y) => x >=0 && x < width && y >=0 && y < height}

  private def seenCoords = mutable.HashSet.empty[(Int, Int)]

  private def tryConnect(color: Color, seen: mutable.HashSet[(Int, Int)],
                          coord: (Int, Int)): Boolean = {
    val x = coord._1
    val y = coord._2
    board(y)(x) match {
      case Some(c) if c == color =>
        if (seen.contains(coord)) false
        else if (isGoalEdge(coord, color)) true
        else {
          seen.add(coord)
          val hood = neighbors(coord)
          hood.exists(tryConnect(color, seen, _))
        }
      case _ => false
    }
  }

  def winner: Option[Color] = {
    if (blackStartCoords.exists(tryConnect(Color.Black, seenCoords, _))) Some(Color.Black)
    else if (whiteStartCoords.exists(tryConnect(Color.White, seenCoords, _))) Some(Color.White)
    else None
  }
}
