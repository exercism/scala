import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class WordCountTest extends AnyFunSuite with Matchers:

  test("count one word"):
    WordCount("word").countWords should be(Map("word" -> 1))

  test("count one of each word"):
    pending
    WordCount("one of each").countWords should be(Map("one" -> 1, "of" -> 1, "each" -> 1))

  test("multiple occurrences of a word"):
    pending
    WordCount("one fish two fish red fish blue fish").countWords should be(Map("one" -> 1, "fish" -> 4, "two" -> 1, "red" -> 1, "blue" -> 1))

  test("handles cramped lists"):
    pending
    WordCount("one,two,three").countWords should be(Map("one" -> 1, "two" -> 1, "three" -> 1))

  test("handles expanded lists"):
    pending
    WordCount("one,\ntwo,\nthree").countWords should be(Map("one" -> 1, "two" -> 1, "three" -> 1))

  test("ignore punctuation"):
    pending
    WordCount("car: carpet as java: javascript!!&@$%^&").countWords should be(Map("car" -> 1, "carpet" -> 1, "as" -> 1, "java" -> 1, "javascript" -> 1))

  test("include numbers"):
    pending
    WordCount("testing, 1, 2 testing").countWords should be(Map("testing" -> 2, "1" -> 1, "2" -> 1))

  test("normalize case"):
    pending
    WordCount("go Go GO Stop stop").countWords should be(Map("go" -> 3, "stop" -> 2))

  test("with apostrophes"):
    pending
    WordCount("'First: don't laugh. Then: don't cry. You're getting it.'").countWords should be(
      Map("first" -> 1, "don't" -> 2, "laugh" -> 1, "then" -> 1, "cry" -> 1, "you're" -> 1, "getting" -> 1, "it" -> 1),
    )

  test("with quotations"):
    pending
    WordCount("Joe can't tell between 'large' and large.").countWords should be(
      Map("joe" -> 1, "can't" -> 1, "tell" -> 1, "between" -> 1, "large" -> 2, "and" -> 1),
    )

  test("substrings from the beginning"):
    pending
    WordCount("Joe can't tell between app, apple and a.").countWords should be(
      Map("joe" -> 1, "can't" -> 1, "tell" -> 1, "between" -> 1, "app" -> 1, "apple" -> 1, "and" -> 1, "a" -> 1),
    )

  test("multiple spaces not detected as a word"):
    pending
    WordCount(" multiple   whitespaces").countWords should be(Map("multiple" -> 1, "whitespaces" -> 1))

  test("alternating word separators not detected as a word"):
    pending
    WordCount(",\n,one,\n ,two \n 'three'").countWords should be(Map("one" -> 1, "two" -> 1, "three" -> 1))

  test("quotation for word with apostrophe"):
    pending
    WordCount("can, can't, 'can't'").countWords should be(Map("can" -> 1, "can't" -> 2))
