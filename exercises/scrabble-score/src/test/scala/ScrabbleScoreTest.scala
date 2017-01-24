import org.scalatest.{Matchers, FunSuite}

class scrabble_score_test extends FunSuite with Matchers {
  test("score letter lowercase") {
    Scrabble.scoreLetter('a') should be (1)
  }

  test("score letter uppercase") {
    pending
    Scrabble.scoreLetter('A') should be (1)
  }

  test("score word") {
    pending
    Scrabble.scoreWord("at") should be (2)
  }

  test("score word with dupe letters") {
    pending
    Scrabble.scoreWord("street") should be (6)
  }

  test("score quirky word") {
    pending
    Scrabble.scoreWord("quirky") should be (22)
  }

  test("score capitalized word") {
    pending
    Scrabble.scoreWord("OXYPHENBUTAZONE") should be (41)
  }

  test("score mixed case word") {
    pending
    Scrabble.scoreWord("Oxyphenbutazone") should be (41)
  }
}

