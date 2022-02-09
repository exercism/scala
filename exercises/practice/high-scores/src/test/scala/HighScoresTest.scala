import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 2.0.0 */
class HighScoresTest extends AnyFunSuite with Matchers {

  test("Latest score") {
    HighScores.latest(List(100, 0, 90, 30)) should be(30)
  }

  test("Personal best") {
    pending
    HighScores.personalBest(List(40, 100, 70)) should be(100)
  }

  test("Personal top") {
    pending
    HighScores.personalTop(List(50, 30, 10)) should be(List(50, 30, 10))
  }

  test("Personal top highest to lowest") {
    pending
    HighScores.personalTop(List(20, 10, 30)) should be(List(30, 20, 10))
  }

  test("Personal top when there is a tie") {
    pending
    HighScores.personalTop(List(40, 20, 40, 30)) should be(List(40, 40, 30))
  }

  test("Personal top when there are less than 3") {
    pending
    HighScores.personalTop(List(30, 70)) should be(List(70, 30))
  }

  test("Personal top when there is only one") {
    pending
    HighScores.personalTop(List(40)) should be(List(40))
  }

  test("Personal top from a long list") {
    pending
    HighScores.personalTop(
      List(10, 30, 90, 30, 100, 20, 10, 0, 30, 40, 40, 70, 70)) should be(
      List(100, 90, 70))
  }

  test("Message for new personal best") {
    pending
    HighScores.report(List(20, 40, 0, 30, 70)) should be(
      "Your latest score was 70. That's your personal best!")
  }

  test("Message when latest score is not the highest score") {
    pending
    HighScores.report(List(20, 100, 0, 30, 70)) should be(
      "Your latest score was 70. That's 30 short of your personal best!")
  }

  test("Message for repeated personal best") {
    pending
    HighScores.report(List(20, 70, 50, 70, 30)) should be(
      "Your latest score was 30. That's 40 short of your personal best!")
  }
}
