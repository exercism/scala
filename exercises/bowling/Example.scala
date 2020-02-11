import scala.annotation.tailrec

sealed trait Bowling {
  def roll(pins: Int): Bowling

  def score(): Either[Error, Int]
}

object Bowling {
  def apply(): Bowling = new BowlingImpl(List.empty)

  private class BowlingImpl(rolls: List[Int]) extends Bowling {
    override def roll(pins: Int): Bowling = new BowlingImpl(rolls ++ List(pins))

    override def score(): Either[Error, Int] =
      if (rolls.isEmpty) {
        Left(Error("Unstarted game cannot be scored"))
      } else {
        val fs = frames(rolls, 1)
        if (fs.length != 10) {
          Left(Error("Invalid number of frames - 10 expected"))
        } else {
          score(fs, 0)
        }
      }

    @tailrec
    private def score(frames: List[List[Int]], acc: Int): Either[Error, Int] =
      frames match {
        case x::xs => val frameScore = scoreFrame(x, xs, acc)
          frameScore match {
            case Right(sum) => score(xs, sum)
            case error => error
          }
        case _ => Right(acc)
      }

    private def scoreFrame(frame: List[Int], remainingFrames: List[List[Int]], acc: Int): Either[Error, Int] = {
      if (frame.exists(s => s < 0)) {
        Left(Error("rolls can not score negative points"))
      } else if (frame.exists(s => s > 10)) {
        Left(Error("a roll can not score more than 10 points"))
      } else if (remainingFrames.nonEmpty && frame.sum > 10) {
        Left(Error("two rolls in a frame can not score more than 10 points"))
      } else if (remainingFrames.isEmpty && !isValidFinalFrame(frame)) {
        Left(Error("invalid final frame"))
      } else {
        val score = if (strike(frame)) {
          acc + frame.sum + strikeBonus(remainingFrames)
        } else if (spare(frame)) {
          acc + frame.sum + spareBonus(remainingFrames)
        } else {
          acc + frame.sum
        }

        Right(score)
      }
    }

    private def frames(rolls: List[Int], i: Int): List[List[Int]] = {
      if (rolls.isEmpty) {
        List.empty
      }
      else {
        val throws = numThrows(rolls, i)
        rolls.take(throws)::frames(rolls.drop(throws), i + 1)
      }
    }

    private def numThrows(rolls: List[Int], frameNum: Int): Int = {
      if (frameNum == 10) {
        if (strike(rolls) || spare(rolls)) 3
        else 2
      } else if (strike(rolls)) {
        1
      } else {
        2
      }
    }

    private def strike(rolls: List[Int]): Boolean =
      rolls.headOption.getOrElse(0) == 10

    private def spare(rolls: List[Int]): Boolean =
      rolls.take(2).sum == 10

    private def strikeBonus(frames: List[List[Int]]): Int =
      frames.take(2).flatten.take(2).sum

    private def spareBonus(frames: List[List[Int]]): Int =
      frames match {
        case x::xs => x.head
        case _ => 0
      }

    private def isValidFinalFrame(rolls: List[Int]): Boolean = {
      val isStrike = strike(rolls)
      val isSpare = spare(rolls)

      if (rolls.length == 2) {
        !isStrike && !isSpare
      } else if (rolls.length == 3) {
        (isStrike || isSpare) &&
          (if (isStrike) {
            rolls(1) == 10 || (rolls(1) + rolls(2) <= 10)
          } else {
            isSpare || rolls(1) + rolls(2) <= 10
          })
      } else {
        false
      }
    }
  }
}

case class Error(errorText: String)