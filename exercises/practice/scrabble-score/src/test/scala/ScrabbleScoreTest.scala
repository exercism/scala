import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class ScrabbleScoreTest extends AnyFunSuite with Matchers {

  test("lowercase letter") {
    ScrabbleScore.score("a") should be(1)
  }

  test("uppercase letter") {
    pending
    ScrabbleScore.score("A") should be(1)
  }

  test("valuable letter") {
    pending
    ScrabbleScore.score("f") should be(4)
  }

  test("short word") {
    pending
    ScrabbleScore.score("at") should be(2)
  }

  test("short, valuable word") {
    pending
    ScrabbleScore.score("zoo") should be(12)
  }

  test("medium word") {
    pending
    ScrabbleScore.score("street") should be(6)
  }

  test("medium, valuable word") {
    pending
    ScrabbleScore.score("quirky") should be(22)
  }

  test("long, mixed-case word") {
    pending
    ScrabbleScore.score("OxyphenButazone") should be(41)
  }

  test("english-like word") {
    pending
    ScrabbleScore.score("pinata") should be(8)
  }

  test("empty input") {
    pending
    ScrabbleScore.score("") should be(0)
  }

  test("entire alphabet available") {
    pending
    ScrabbleScore.score("abcdefghijklmnopqrstuvwxyz") should be(87)
  }
}
