sealed trait Bowling {
  def roll(pins: Int): Bowling

  def score(): Either[Error, Int]
}

object Bowling {
  def apply(): Bowling = ???
}

case class Error(errorText: String)