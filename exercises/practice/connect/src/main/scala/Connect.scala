enum Color:
  case White, Black

case class Connect(lines: List[String]):
  def winner: Option[Color] = ???
