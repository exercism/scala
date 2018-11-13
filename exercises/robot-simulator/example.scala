import Bearing.Bearing

case class Robot(bearing: Bearing, coordinates: (Int, Int)) {
  val x = coordinates._1
  val y = coordinates._2

  def advance: Robot = bearing match {
    case Bearing.North => Robot(bearing, (x, y + 1))
    case Bearing.South => Robot(bearing, (x, y - 1))
    case Bearing.East => Robot(bearing, (x + 1, y))
    case Bearing.West => Robot(bearing, (x - 1, y))
  }

  def turnRight: Robot =
    if (bearing.id == Bearing.maxId - 1) Robot(Bearing(0), (x, y))
    else Robot(Bearing(bearing.id + 1), (x, y))

  def turnLeft: Robot =
    if (bearing.id == 0) Robot(Bearing(Bearing.maxId - 1), (x, y))
    else Robot(Bearing(bearing.id - 1), (x, y))

  def simulate(instructions: String): Robot =  instructions.foldLeft(this){
    case (acc, instruction) => instruction match {
      case 'A' => acc.advance()
      case 'L' => acc.turnLeft()
      case 'R' => acc.turnRight()
      case _ => throw new IllegalArgumentException("Invalid instruction - " + instruction)
    }}
}

object Bearing extends Enumeration {
  type Bearing = Value

  val North = Value(0, "North")
  val East = Value(1, "East")
  val South = Value(2, "South")
  val West = Value(3, "West")
}
