import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.0.1 */
class BowlingTest extends AnyFunSuite with Matchers {

  test("should be able to score a game with all zeros") {
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(0))
  }

  test("should be able to score a game with no strikes or spares") {
    pending
    val score = List(3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(90))
  }

  test("a spare followed by zeros is worth ten points") {
    pending
    val score = List(6, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(10))
  }

  test("points scored in the roll after a spare are counted twice") {
    pending
    val score = List(6, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(16))
  }

  test("consecutive spares each get a one roll bonus") {
    pending
    val score = List(5, 5, 3, 7, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(31))
  }

  test("a spare in the last frame gets a one roll bonus that is counted once") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 3, 7).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(17))
  }

  test("a strike earns ten points in a frame with a single roll") {
    pending
    val score = List(10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(10))
  }

  test("points scored in the two rolls after a strike are counted twice as a bonus") {
    pending
    val score = List(10, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(26))
  }

  test("consecutive strikes each get the two roll bonus") {
    pending
    val score = List(10, 10, 10, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(81))
  }

  test("a strike in the last frame gets a two roll bonus that is counted once") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 7, 1).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(18))
  }

  test("rolling a spare with the two roll bonus does not get a bonus roll") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 7, 3).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(20))
  }

  test("strikes with the two roll bonus do not get bonus rolls") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 10).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(30))
  }

  test("a strike with the one roll bonus after a spare in the last frame does not get a bonus") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 3, 10).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(20))
  }

  test("all strikes is a perfect game") {
    pending
    val score = List(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(300))
  }

  test("rolls cannot score negative points") {
    pending
    val score = List().foldLeft(Bowling())((acc, roll) => acc.roll(roll)).roll(-1).score()
    score.isLeft should be (true)
  }

  test("a roll cannot score more than 10 points") {
    pending
    val score = List().foldLeft(Bowling())((acc, roll) => acc.roll(roll)).roll(11).score()
    score.isLeft should be (true)
  }

  test("two rolls in a frame cannot score more than 10 points") {
    pending
    val score = List(5).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).roll(6).score()
    score.isLeft should be (true)
  }

  test("bonus roll after a strike in the last frame cannot score more than 10 points") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).roll(11).score()
    score.isLeft should be (true)
  }

  test("two bonus rolls after a strike in the last frame cannot score more than 10 points") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 5).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).roll(6).score()
    score.isLeft should be (true)
  }

  test("two bonus rolls after a strike in the last frame can score more than 10 points if one is a strike") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 6).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score should be (Right(26))
  }

  test("the second bonus rolls after a strike in the last frame cannot be a strike if the first one is not a strike") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 6).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).roll(10).score()
    score.isLeft should be (true)
  }

  test("second bonus roll after a strike in the last frame cannot score more than 10 points") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).roll(11).score()
    score.isLeft should be (true)
  }

  test("an unstarted game cannot be scored") {
    pending
    val score = List().foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score.isLeft should be (true)
  }

  test("an incomplete game cannot be scored") {
    pending
    val score = List(0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score.isLeft should be (true)
  }

  test("cannot roll if game already has ten frames") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).roll(0).score()
    score.isLeft should be (true)
  }

  test("bonus rolls for a strike in the last frame must be rolled before score can be calculated") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score.isLeft should be (true)
  }

  test("both bonus rolls for a strike in the last frame must be rolled before score can be calculated") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score.isLeft should be (true)
  }

  test("bonus roll for a spare in the last frame must be rolled before score can be calculated") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 3).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score.isLeft should be (true)
  }
}