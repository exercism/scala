enum Bearing:
  case North, East, South, West

case class Robot(bearing: Bearing, coordinates: (Int, Int)):
  def simulate(instructions: String): Robot = ???
