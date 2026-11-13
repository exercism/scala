enum Bearing:
  case North, East, South, West

case class Robot(bearing: Bearing, coordinates: (Int, Int)):

  private val (x, y) = coordinates

  def advance: Robot = bearing match
    case Bearing.North => copy(coordinates = (x, y + 1))
    case Bearing.South => copy(coordinates = (x, y - 1))
    case Bearing.East  => copy(coordinates = (x + 1, y))
    case Bearing.West  => copy(coordinates = (x - 1, y))

  def turnRight: Robot = copy(bearing = Bearing.fromOrdinal((bearing.ordinal + 1) % 4))

  def turnLeft: Robot = copy(bearing = Bearing.fromOrdinal((bearing.ordinal + 3) % 4))

  def simulate(instructions: String): Robot = instructions.foldLeft(this):
    case (robot, 'A')     => robot.advance
    case (robot, 'L')     => robot.turnLeft
    case (robot, 'R')     => robot.turnRight
    case (_, instruction) => throw IllegalArgumentException(s"Invalid instruction: $instruction")
