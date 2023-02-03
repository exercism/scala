# `foldLeft()`

```java
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

  def simulate(orders: String): Robot =
    orders.foldLeft(this)((robbie, cmd) =>
      cmd match {
        case 'L' => robbie.turnLeft
        case 'R' => robbie.turnRight
        case 'A' => robbie.advance
      }
    )
}
```

TODO
