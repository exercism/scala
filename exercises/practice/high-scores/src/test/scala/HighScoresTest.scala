import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class HighScoresTest extends AnyFunSuite with Matchers:

  test("Latest score"):
    HighScores.latest(List(100, 0, 90, 30)) should be(30)

  test("Personal best"):
    pending
    HighScores.personalBest(List(40, 100, 70)) should be(100)

  test("Top 3 scores - Personal top three from a list of scores"):
    pending
    HighScores.personalTop(List(10, 30, 90, 30, 100, 20, 10, 0, 30, 40, 40, 70, 70)) should be(List(100, 90, 70))

  test("Top 3 scores - Personal top highest to lowest"):
    pending
    HighScores.personalTop(List(20, 10, 30)) should be(List(30, 20, 10))

  test("Top 3 scores - Personal top when there is a tie"):
    pending
    HighScores.personalTop(List(40, 20, 40, 30)) should be(List(40, 40, 30))

  test("Top 3 scores - Personal top when there are less than 3"):
    pending
    HighScores.personalTop(List(30, 70)) should be(List(70, 30))

  test("Top 3 scores - Personal top when there is only one"):
    pending
    HighScores.personalTop(List(40)) should be(List(40))
