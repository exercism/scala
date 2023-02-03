# Recursion

```scala
import scala.annotation.tailrec;

object Bearing extends Enumeration {
  val North = Value(0)
  val East = Value(1)
  val South = Value(2)
  val West = Value(3)
}

case class Robot(bearing: Bearing.Value, pos: (Int, Int)) {

  val DirectionCount = 4

  def turnRight: Robot =
    Robot(Bearing.apply((bearing.id + 1) % DirectionCount), pos)

  def turnLeft: Robot =
    Robot(
      Bearing.apply((DirectionCount + (bearing.id - 1)) % DirectionCount),
      pos
    )

  def coordinates: (Int, Int) = pos

  def advance: Robot =
    bearing match {
      case Bearing.North => Robot(bearing, (pos._1, pos._2 + 1))
      case Bearing.South => Robot(bearing, (pos._1, pos._2 - 1))
      case Bearing.East  => Robot(bearing, (pos._1 + 1, pos._2))
      case Bearing.West  => Robot(bearing, (pos._1 - 1, pos._2))
    }

  @tailrec
  final def simulate(orders: String): Robot =
    if (orders.isEmpty) this
    else
      (orders.head match {
        case 'L' => turnLeft
        case 'R' => turnRight
        case 'A' => advance
      }).simulate(orders.tail)
}
```

TODO

```scala
enum Bearing {
  case North, East, South, West
}

// code snipped

def turnRight: Robot =
  Robot(Bearing.fromOrdinal((bearing.ordinal + 1) % DirectionCount), pos)

def turnLeft: Robot =
  Robot(
    Bearing.fromOrdinal(
      (DirectionCount + (bearing.ordinal - 1)) % DirectionCount
    ),
    pos
  )
```

TODO
