import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class YachtTests extends AnyFunSuite with Matchers {

  test("Yacht") {
    Yacht.score(Category.Yacht, List(Die.Five, Die.Five, Die.Five, Die.Five, Die.Five)) shouldEqual 50
  }

  test("Not Yacht") {
    pending
    Yacht.score(Category.Yacht, List(Die.One, Die.Three, Die.Three, Die.Two, Die.Five)) shouldEqual 0
  }

  test("Ones") {
    Yacht.score(Category.Ones, List(Die.One, Die.One, Die.One, Die.Three, Die.Five)) shouldEqual 3
  }

  test("Ones, out of order") {
    Yacht.score(Category.Ones, List(Die.Three, Die.One, Die.One, Die.Five, Die.One)) shouldEqual 3
  }

  test("No ones") {
    Yacht.score(Category.Ones, List(Die.Four, Die.Three, Die.Six, Die.Five, Die.Five)) shouldEqual 0
  }

  test("Twos") {
    Yacht.score(Category.Twos, List(Die.Two, Die.Three, Die.Four, Die.Five, Die.Six)) shouldEqual 2
  }

  test("Fours") {
    Yacht.score(Category.Fours, List(Die.One, Die.Four, Die.One, Die.Four, Die.One)) shouldEqual 8
  }

  test("Yacht counted as threes") {
    Yacht.score(Category.Threes, List(Die.Three, Die.Three, Die.Three, Die.Three, Die.Three)) shouldEqual 15
  }

  test("Yacht of 3s counted as fives") {
    Yacht.score(Category.Fives, List(Die.Three, Die.Three, Die.Three, Die.Three, Die.Three)) shouldEqual 0
  }

  test("Fives") {
    Yacht.score(Category.Fives, List(Die.One, Die.Five, Die.Three, Die.Five, Die.Three)) shouldEqual 10
  }

  test("Sixes") {Yacht.score(Category.Sixes, List(Die.Two, Die.Three, Die.Four, Die.Five, Die.Six)) shouldEqual 6
  }

  test("Full house two small, three big") {
    Yacht.score(Category.FullHouse, List(Die.Two, Die.Two, Die.Four, Die.Four, Die.Four)) shouldEqual 16
  }

  test("Full house three small, two big") {
    Yacht.score(Category.FullHouse, List(Die.Five, Die.Three, Die.Three, Die.Five, Die.Three)) shouldEqual 19
  }

  test("Two pair is not a full house") {
    Yacht.score(Category.FullHouse, List(Die.Two, Die.Two, Die.Four, Die.Four, Die.Five)) shouldEqual 0
  }

  test("Four of a kind is not a full house") {
    Yacht.score(Category.FullHouse, List(Die.One, Die.Four, Die.Four, Die.Four, Die.Four)) shouldEqual 0
  }

  test("Yacht is not a full house") {
    Yacht.score(Category.FullHouse, List(Die.Two, Die.Two, Die.Two, Die.Two, Die.Two)) shouldEqual 0
  }

  test("Four of a Kind") {
    Yacht.score(Category.FourOfAKind, List(Die.Six, Die.Six, Die.Four, Die.Six, Die.Six)) shouldEqual 24
  }

  test("Yacht can be scored as Four of a Kind") {
    Yacht.score(Category.FourOfAKind, List(Die.Three, Die.Three, Die.Three, Die.Three, Die.Three)) shouldEqual 12
  }

  test("Full house is not Four of a Kind") {
    Yacht.score(Category.FourOfAKind, List(Die.Three, Die.Three, Die.Three, Die.Five, Die.Five)) shouldEqual 0
  }

  test("Little Straight") {
    Yacht.score(Category.LittleStraight, List(Die.Three, Die.Five, Die.Four, Die.One, Die.Two)) shouldEqual 30
  }

  test("Little Straight as Big Straight") {
    Yacht.score(Category.BigStraight, List(Die.One, Die.Two, Die.Three, Die.Four, Die.Five)) shouldEqual 0
  }

  test("Four in order but not a little straight") {
    Yacht.score(Category.LittleStraight, List(Die.One, Die.One, Die.Two, Die.Three, Die.Four)) shouldEqual 0
  }

  test("No pairs but not a little straight") {
    Yacht.score(Category.LittleStraight, List(Die.One, Die.Two, Die.Three, Die.Four, Die.Six)) shouldEqual 0
  }

  test("Minimum is 1, maximum is 5, but not a little straight") {
    Yacht.score(Category.LittleStraight, List(Die.One, Die.One, Die.Three, Die.Four, Die.Five)) shouldEqual 0
  }

  test("Big Straight") {
    Yacht.score(Category.BigStraight, List(Die.Four, Die.Six, Die.Two, Die.Five, Die.Three)) shouldEqual 30
  }

  test("Big Straight as little straight") {
    Yacht.score(Category.LittleStraight, List(Die.Six, Die.Five, Die.Four, Die.Three, Die.Two)) shouldEqual 0
  }

  test("No pairs but not a big straight") {
    Yacht.score(Category.BigStraight, List(Die.Six, Die.Five, Die.Four, Die.Three, Die.One)) shouldEqual 0
  }

  test("Choice") {
    Yacht.score(Category.Choice, List(Die.Three, Die.Three, Die.Five, Die.Six, Die.Six)) shouldEqual 23
  }

  test("Yacht as choice") {
    Yacht.score(Category.Choice, List(Die.Two, Die.Two, Die.Two, Die.Two, Die.Two)) shouldEqual 10
  }
}
