import org.scalatest.{FunSuite, Matchers}

class BowlingSuite extends FunSuite with Matchers {
  // returns the final score of a bowling game
  test("should be able to score a game with all zeros") {
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 0)
      case Left(_) => fail("should be able to score a game with all zeros")
    }
  }

  test("should be able to score a game with no strikes or spares") {
    pending
    val score = List(3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6, 3, 6).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 90)
      case Left(_) => fail("should be able to score a game with no strikes or spares")
    }
  }

  test("a spare followed by zeros is worth ten points") {
    pending
    val score = List(6, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 10)
      case Left(_) => fail("a spare followed by zeros is worth ten points")
    }
  }

  test("points scored in the roll after a spare are counted twice") {
    pending
    val score = List(6, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 16)
      case Left(_) => fail("points scored in the roll after a spare are counted twice")
    }
  }

  test("consecutive spares each get a one roll bonus") {
    pending
    val score = List(5, 5, 3, 7, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 31)
      case Left(_) => fail("consecutive spares each get a one roll bonus")
    }
  }

  test("a spare in the last frame gets a one roll bonus that is counted once") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 3, 7).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 17)
      case Left(_) => fail("a spare in the last frame gets a one roll bonus that is counted once")
    }
  }

  test("a strike earns ten points in a frame with a single roll") {
    pending
    val score = List(10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 10)
      case Left(_) => fail("a strike earns ten points in a frame with a single roll")
    }
  }

  test("points scored in the two rolls after a strike are counted twice as a bonus") {
    pending
    val score = List(10, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 26)
      case Left(_) => fail("points scored in the two rolls after a strike are counted twice as a bonus")
    }
  }

  test("consecutive strikes each get the two roll bonus") {
    pending
    val score = List(10, 10, 10, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 81)
      case Left(_) => fail("consecutive strikes each get the two roll bonus")
    }
  }

  test("a strike in the last frame gets a two roll bonus that is counted once") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 7, 1).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 18)
      case Left(_) => fail("a strike in the last frame gets a two roll bonus that is counted once")
    }
  }

  test("rolling a spare with the two roll bonus does not get a bonus roll") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 7, 3).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 20)
      case Left(_) => fail("rolling a spare with the two roll bonus does not get a bonus roll")
    }
  }

  test("strikes with the two roll bonus do not get bonus rolls") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 10).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 30)
      case Left(_) => fail("strikes with the two roll bonus do not get bonus rolls")
    }
  }

  test("a strike with the one roll bonus after a spare in the last frame does not get a bonus") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 3, 10).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 20)
      case Left(_) => fail("a strike with the one roll bonus after a spare in the last frame does not get a bonus")
    }
  }

  test("all strikes is a perfect game") {
    pending
    val score = List(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 300)
      case Left(_) => fail("all strikes is a perfect game")
    }
  }

  test("rolls can not score negative points") {
    pending
    val score = List(-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(_) => fail("Unexpected score returned. Failure expected")
      case Left(_) =>
    }
  }

  test("a roll can not score more than 10 points") {
    pending
    val score = List(11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(_) => fail("Unexpected score returned. Failure expected")
      case Left(_) =>
    }
  }

  test("two rolls in a frame can not score more than 10 points") {
    pending
    val score = List(5, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(_) => fail("Unexpected score returned. Failure expected")
      case Left(_) =>
    }
  }

  test("two bonus rolls after a strike in the last frame can not score more than 10 points") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 5, 6).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(_) => fail("Unexpected score returned. Failure expected")
      case Left(_) =>
    }
  }

  test("two bonus rolls after a strike in the last frame can score more than 10 points if one is a strike") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 6).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(n) => assert(n == 26)
      case Left(_) => fail("two bonus rolls after a strike in the last frame can score more than 10 points if one is a strike")
    }
  }

  test("the second bonus rolls after a strike in the last frame can not be a strike if the first one is not a strike") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 6, 10).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(_) => fail("Unexpected score returned. Failure expected")
      case Left(_) =>
    }
  }

  test("an unstarted game can not be scored") {
    pending
    val score = List().foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(_) => fail("Unexpected score returned. Failure expected")
      case Left(_) =>
    }
  }

  test("an incomplete game can not be scored") {
    pending
    val score = List(0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(_) => fail("Unexpected score returned. Failure expected")
      case Left(_) =>
    }
  }

  test("a game with more than ten frames can not be scored") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(_) => fail("Unexpected score returned. Failure expected")
      case Left(_) =>
    }
  }

  test("bonus rolls for a strike in the last frame must be rolled before score can be calculated") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(_) => fail("Unexpected score returned. Failure expected")
      case Left(_) =>
    }
  }

  test("both bonus rolls for a strike in the last frame must be rolled before score can be calculated") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(_) => fail("Unexpected score returned. Failure expected")
      case Left(_) =>
    }
  }

  test("bonus roll for a spare in the last frame must be rolled before score can be calculated") {
    pending
    val score = List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 3).foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score match {
      case Right(_) => fail("Unexpected score returned. Failure expected")
      case Left(_) =>
    }
  }
}