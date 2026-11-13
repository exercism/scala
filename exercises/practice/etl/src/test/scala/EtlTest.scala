import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class EtlTest extends AnyFunSuite with Matchers:

  test("single letter"):
    val legacy   = Map(
      1 -> Seq("A"),
    )
    val expected = Map(
      "a" -> 1,
    )
    Etl.transform(legacy) should be(expected)

  test("single score with multiple letters"):
    pending
    val legacy   = Map(
      1 -> Seq("A", "E", "I", "O", "U"),
    )
    val expected = Map(
      "a" -> 1,
      "e" -> 1,
      "i" -> 1,
      "o" -> 1,
      "u" -> 1,
    )
    Etl.transform(legacy) should be(expected)

  test("multiple scores with multiple letters"):
    pending
    val legacy   = Map(
      1 -> Seq("A", "E"),
      2 -> Seq("D", "G"),
    )
    val expected = Map(
      "a" -> 1,
      "d" -> 2,
      "e" -> 1,
      "g" -> 2,
    )
    Etl.transform(legacy) should be(expected)

  test("multiple scores with differing numbers of letters"):
    pending
    val legacy   = Map(
      1  -> Seq("A", "E", "I", "O", "U", "L", "N", "R", "S", "T"),
      2  -> Seq("D", "G"),
      3  -> Seq("B", "C", "M", "P"),
      4  -> Seq("F", "H", "V", "W", "Y"),
      5  -> Seq("K"),
      8  -> Seq("J", "X"),
      10 -> Seq("Q", "Z"),
    )
    val expected = Map(
      "a" -> 1,
      "b" -> 3,
      "c" -> 3,
      "d" -> 2,
      "e" -> 1,
      "f" -> 4,
      "g" -> 2,
      "h" -> 4,
      "i" -> 1,
      "j" -> 8,
      "k" -> 5,
      "l" -> 1,
      "m" -> 3,
      "n" -> 1,
      "o" -> 1,
      "p" -> 3,
      "q" -> 10,
      "r" -> 1,
      "s" -> 1,
      "t" -> 1,
      "u" -> 1,
      "v" -> 4,
      "w" -> 4,
      "x" -> 8,
      "y" -> 4,
      "z" -> 10,
    )
    Etl.transform(legacy) should be(expected)
