import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class YachtTests extends AnyFunSuite with Matchers {

  test("Yacht") {
    Yacht.score(List(5, 5, 5, 5, 5), "yacht") shouldEqual 50
  }

  test("Not Yacht") {
    pending
    Yacht.score(List(1, 3, 3, 2, 5), "yacht") shouldEqual 0
  }

  test("Ones") {
    pending
    Yacht.score(List(1, 1, 1, 3, 5), "ones") shouldEqual 3
  }

  test("Ones, out of order") {
    pending
    Yacht.score(List(3, 1, 1, 5, 1), "ones") shouldEqual 3
  }

  test("No ones") {
    pending
    Yacht.score(List(4, 3, 6, 5, 5), "ones") shouldEqual 0
  }

  test("Twos") {
    pending
    Yacht.score(List(2, 3, 4, 5, 6), "twos") shouldEqual 2
  }

  test("Fours") {
    pending
    Yacht.score(List(1, 4, 1, 4, 1), "fours") shouldEqual 8
  }

  test("Yacht counted as threes") {
    pending
    Yacht.score(List(3, 3, 3, 3, 3), "threes") shouldEqual 15
  }

  test("Yacht of 3s counted as fives") {
    pending
    Yacht.score(List(3, 3, 3, 3, 3), "fives") shouldEqual 0
  }

  test("Fives") {
    pending
    Yacht.score(List(1, 5, 3, 5, 3), "fives") shouldEqual 10
  }

  test("Sixes") {
    pending
    Yacht.score(List(2, 3, 4, 5, 6), "sixes") shouldEqual 6
  }

  test("Full house two small, three big") {
    pending
    Yacht.score(List(2, 2, 4, 4, 4), "full house") shouldEqual 16
  }

  test("Full house three small, two big") {
    pending
    Yacht.score(List(5, 3, 3, 5, 3), "full house") shouldEqual 19
  }

  test("Two pair is not a full house") {
    pending
    Yacht.score(List(2, 2, 4, 4, 5), "full house") shouldEqual 0
  }

  test("Four of a kind is not a full house") {
    pending
    Yacht.score(List(1, 4, 4, 4, 4), "full house") shouldEqual 0
  }

  test("Yacht is not a full house") {
    pending
    Yacht.score(List(2, 2, 2, 2, 2), "full house") shouldEqual 0
  }

  test("Four of a Kind") {
    pending
    Yacht.score(List(6, 6, 4, 6, 6), "four of a kind") shouldEqual 24
  }

  test("Yacht can be scored as Four of a Kind") {
    pending
    Yacht.score(List(3, 3, 3, 3, 3), "four of a kind") shouldEqual 12
  }

  test("Full house is not Four of a Kind") {
    pending
    Yacht.score(List(3, 3, 3, 5, 5), "four of a kind") shouldEqual 0
  }

  test("Little Straight") {
    pending
    Yacht.score(List(3, 5, 4, 1, 2), "little straight") shouldEqual 30
  }

  test("Little Straight as Big Straight") {
    pending
    Yacht.score(List(1, 2, 3, 4, 5), "big straight") shouldEqual 0
  }

  test("Four in order but not a little straight") {
    pending
    Yacht.score(List(1, 1, 2, 3, 4), "little straight") shouldEqual 0
  }

  test("No pairs but not a little straight") {
    pending
    Yacht.score(List(1, 2, 3, 4, 6), "little straight") shouldEqual 0
  }

  test("Minimum is 1, maximum is 5, but not a little straight") {
    pending
    Yacht.score(List(1, 1, 3, 4, 5), "little straight") shouldEqual 0
  }

  test("Big Straight") {
    pending
    Yacht.score(List(4, 6, 2, 5, 3), "big straight") shouldEqual 30
  }

  test("Big Straight as little straight") {
    pending
    Yacht.score(List(6, 5, 4, 3, 2), "little straight") shouldEqual 0
  }

  test("No pairs but not a big straight") {
    pending
    Yacht.score(List(6, 5, 4, 3, 1), "big straight") shouldEqual 0
  }

  test("Choice") {
    pending
    Yacht.score(List(3, 3, 5, 6, 6), "choice") shouldEqual 23
  }

  test("Yacht as choice") {
    pending
    Yacht.score(List(2, 2, 2, 2, 2), "choice") shouldEqual 10
  }
}
