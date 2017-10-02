import org.scalatest.{Matchers, FunSuite}

/** @version 1.0.0 */
class WordCountTest extends FunSuite with Matchers {

  test("count one word") {
    WordCount("word").countwords should be(Map(("word", 1)))
  }

  test("count one of each word") {
    pending
    WordCount("one of each").countwords should be(
      Map(("one", 1), ("of", 1), ("each", 1)))
  }

  test("multiple occurrences of a word") {
    pending
    WordCount("one fish two fish red fish blue fish").countwords should be(
      Map(("blue", 1), ("two", 1), ("fish", 4), ("one", 1), ("red", 1)))
  }

  test("handles cramped lists") {
    pending
    WordCount("one,two,three").countwords should be(
      Map(("one", 1), ("two", 1), ("three", 1)))
  }

  test("handles expanded lists") {
    pending
    WordCount("one,\ntwo,\nthree").countwords should be(
      Map(("one", 1), ("two", 1), ("three", 1)))
  }

  test("ignore punctuation") {
    pending
    WordCount("car: carpet as java: javascript!!&@$%^&").countwords should be(
      Map(("as", 1), ("car", 1), ("java", 1), ("carpet", 1), ("javascript", 1)))
  }

  test("include numbers") {
    pending
    WordCount("testing, 1, 2 testing").countwords should be(
      Map(("testing", 2), ("1", 1), ("2", 1)))
  }

  test("normalize case") {
    pending
    WordCount("go Go GO Stop stop").countwords should be(
      Map(("go", 3), ("stop", 2)))
  }

  test("with apostrophes") {
    pending
    WordCount("First: don\'t laugh. Then: don\'t cry.").countwords should be(
      Map(("laugh", 1), ("don't", 2), ("then", 1), ("first", 1), ("cry", 1)))
  }

  test("with quotations") {
    pending
    WordCount("Joe can\'t tell between \'large\' and large.").countwords should be(
      Map(("can't", 1),
          ("large", 2),
          ("joe", 1),
          ("between", 1),
          ("tell", 1),
          ("and", 1)))
  }
}
