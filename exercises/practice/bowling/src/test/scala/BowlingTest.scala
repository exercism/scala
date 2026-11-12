import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class BowlingTest extends AnyFunSuite with Matchers:

  private def rollAll(rolls: List[Int]): Bowling =
    rolls.foldLeft(Bowling())((game, pins) => game.roll(pins))

  test("should be able to score a game with all zeros"):
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).score() should be(Right(0))

  test("should be able to score a game with no strikes or spares"):
    pending
    rollAll(List(3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6)).score() should be(Right(90))

  test("a spare followed by zeros is worth ten points"):
    pending
    rollAll(List(6, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).score() should be(Right(10))

  test("points scored in the roll after a spare are counted twice"):
    pending
    rollAll(List(6, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).score() should be(Right(16))

  test("consecutive spares each get a one roll bonus"):
    pending
    rollAll(List(5, 5, 3, 7, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).score() should be(Right(31))

  test("a spare in the last frame gets a one roll bonus that is counted once"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 3, 7)).score() should be(Right(17))

  test("a strike earns ten points in a frame with a single roll"):
    pending
    rollAll(List(10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).score() should be(Right(10))

  test("points scored in the two rolls after a strike are counted twice as a bonus"):
    pending
    rollAll(List(10, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).score() should be(Right(26))

  test("consecutive strikes each get the two roll bonus"):
    pending
    rollAll(List(10, 10, 10, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).score() should be(Right(81))

  test("a strike in the last frame gets a two roll bonus that is counted once"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 7, 1)).score() should be(Right(18))

  test("rolling a spare with the two roll bonus does not get a bonus roll"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 7, 3)).score() should be(Right(20))

  test("strikes with the two roll bonus do not get bonus rolls"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 10)).score() should be(Right(30))

  test("last two strikes followed by only last bonus with non strike points"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 0, 1)).score() should be(Right(31))

  test("a strike with the one roll bonus after a spare in the last frame does not get a bonus"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 3, 10)).score() should be(Right(20))

  test("all strikes is a perfect game"):
    pending
    rollAll(List(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10)).score() should be(Right(300))

  test("rolls cannot score negative points"):
    pending
    rollAll(List()).roll(-1).score().isLeft should be(true)

  test("a roll cannot score more than 10 points"):
    pending
    rollAll(List()).roll(11).score().isLeft should be(true)

  test("two rolls in a frame cannot score more than 10 points"):
    pending
    rollAll(List(5)).roll(6).score().isLeft should be(true)

  test("bonus roll after a strike in the last frame cannot score more than 10 points"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10)).roll(11).score().isLeft should be(true)

  test("two bonus rolls after a strike in the last frame cannot score more than 10 points"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 5)).roll(6).score().isLeft should be(true)

  test("two bonus rolls after a strike in the last frame can score more than 10 points if one is a strike"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 6)).score() should be(Right(26))

  test("the second bonus rolls after a strike in the last frame cannot be a strike if the first one is not a strike"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 6)).roll(10).score().isLeft should be(true)

  test("second bonus roll after a strike in the last frame cannot score more than 10 points"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10)).roll(11).score().isLeft should be(true)

  test("an unstarted game cannot be scored"):
    pending
    rollAll(List()).score().isLeft should be(true)

  test("an incomplete game cannot be scored"):
    pending
    rollAll(List(0, 0)).score().isLeft should be(true)

  test("cannot roll if game already has ten frames"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)).roll(0).score().isLeft should be(true)

  test("bonus rolls for a strike in the last frame must be rolled before score can be calculated"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10)).score().isLeft should be(true)

  test("both bonus rolls for a strike in the last frame must be rolled before score can be calculated"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10)).score().isLeft should be(true)

  test("bonus roll for a spare in the last frame must be rolled before score can be calculated"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 3)).score().isLeft should be(true)

  test("cannot roll after bonus roll for spare"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 3, 2)).roll(2).score().isLeft should be(true)

  test("cannot roll after bonus rolls for strike"):
    pending
    rollAll(List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 3, 2)).roll(2).score().isLeft should be(true)
