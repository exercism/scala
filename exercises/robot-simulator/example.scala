case class Robot(bearing: Bearing, coordinates: (Int, Int)) {
  val x = coordinates._1
  val y = coordinates._2

  def advance(): Robot = bearing match {
    case Bearing.North => Robot(bearing, (x, y + 1))
    case Bearing.South => Robot(bearing, (x, y - 1))
    case Bearing.East => Robot(bearing, (x + 1, y))
    case Bearing.West => Robot(bearing, (x - 1, y))
  }

  def turnRight(): Robot = bearing match {
    case Bearing.North => Robot(Bearing.East, coordinates)
    case Bearing.South => Robot(Bearing.West, coordinates)
    case Bearing.East => Robot(Bearing.South, coordinates)
    case Bearing.West => Robot(Bearing.North, coordinates)
  }

  def turnLeft(): Robot = bearing match {
    case Bearing.North => Robot(Bearing.West,  coordinates)
    case Bearing.East  => Robot(Bearing.North, coordinates)
    case Bearing.South => Robot(Bearing.East,  coordinates)
    case Bearing.West  => Robot(Bearing.South, coordinates)
  }

  def simulate(instructions: String): Robot =  instructions.foldLeft(this){
    case (acc, instruction) => instruction match {
      case 'A' => acc.advance()
      case 'L' => acc.turnLeft()
      case 'R' => acc.turnRight()
      case _ => throw new IllegalArgumentException("Invalid instruction - " + instruction)
    }}
}

sealed trait Bearing
object Bearing {
  case object North extends Bearing
  case object East  extends Bearing
  case object South extends Bearing
  case object West  extends Bearing
}
