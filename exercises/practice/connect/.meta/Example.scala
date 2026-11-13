import scala.annotation.tailrec

enum Color:
  case White, Black

case class Connect(lines: List[String]):

  private type Cell = (Int, Int)

  private val board  = lines.toVector
  private val height = board.size
  private val width  = board.headOption.fold(0)(_.length)

  def winner: Option[Color] = Color.values.find(connects)

  private def stone(color: Color): Char = color match
    case Color.White => 'O'
    case Color.Black => 'X'

  private def owns(color: Color)(cell: Cell): Boolean =
    val (x, y) = cell
    x >= 0 && x < width && y >= 0 && y < height && board(y)(x) == stone(color)

  private def atGoal(color: Color)(cell: Cell): Boolean = color match
    case Color.White => cell._2 == height - 1
    case Color.Black => cell._1 == width - 1

  private def neighbors(cell: Cell): List[Cell] =
    val (x, y) = cell
    List((x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1), (x - 1, y + 1), (x + 1, y - 1))

  private def connects(color: Color): Boolean =
    val edge = color match
      case Color.White => (0 until width).map((_, 0))
      case Color.Black => (0 until height).map((0, _))

    val start = edge.filter(owns(color)).toList
    search(color, start, start.toSet)

  @tailrec
  private def search(color: Color, frontier: List[Cell], seen: Set[Cell]): Boolean = frontier match
    case Nil                              => false
    case cell :: _ if atGoal(color)(cell) => true
    case cell :: rest                     =>
      val next = neighbors(cell).filter(owns(color)).filterNot(seen)
      search(color, next ++ rest, seen ++ next)
