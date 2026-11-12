case class Error(errorText: String)

sealed trait Bowling:
  def roll(pins: Int): Bowling

  def score(): Either[Error, Int]

object Bowling:
  def apply(): Bowling = Game(List.empty)

  private val Pins = 10

  private def invalid    = Left(Error("Pin count exceeds pins on the lane"))
  private def incomplete = Left(Error("Score cannot be taken until the end of the game"))
  private def gameOver   = Left(Error("Cannot roll after game is over"))

  private case class Game(rolls: List[Int]) extends Bowling:
    def roll(pins: Int): Bowling = Game(rolls :+ pins)

    def score(): Either[Error, Int] = scoreFrames(rolls, frame = 1)

  private def valid(pins: Int): Boolean = 0 <= pins && pins <= Pins

  private def scoreFrames(rolls: List[Int], frame: Int): Either[Error, Int] =
    def andThen(points: => Either[Error, Int], rest: List[Int]): Either[Error, Int] =
      for
        later <- scoreFrames(rest, frame + 1)
        here  <- points
      yield here + later

    if frame == 10 then scoreLastFrame(rolls)
    else
      rolls match
        case pins :: _ if !valid(pins) => invalid
        case Pins :: rest              => andThen(withBonus(rest, 2), rest)
        case first :: second :: rest   =>
          if !valid(second) || first + second > Pins then invalid
          else if first + second == Pins then andThen(withBonus(rest, 1), rest)
          else andThen(Right(first + second), rest)
        case _                         => incomplete

  private def withBonus(rest: List[Int], bonusRolls: Int): Either[Error, Int] =
    if rest.sizeIs < bonusRolls then incomplete else Right(Pins + rest.take(bonusRolls).sum)

  private def scoreLastFrame(rolls: List[Int]): Either[Error, Int] =
    if rolls.exists(!valid(_)) then invalid
    else
      rolls match
        case Pins :: first :: second :: rest =>
          if first < Pins && first + second > Pins then invalid
          else if rest.nonEmpty then gameOver
          else Right(Pins + first + second)
        case Pins :: _                       => incomplete
        case first :: second :: rest         =>
          if first + second > Pins then invalid
          else if first + second == Pins then
            rest match
              case Nil          => incomplete
              case bonus :: Nil => Right(Pins + bonus)
              case _            => gameOver
          else if rest.nonEmpty then gameOver
          else Right(first + second)
        case _                               => incomplete
