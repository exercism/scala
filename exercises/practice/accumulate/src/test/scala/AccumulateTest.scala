import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class AccumulateTest extends AnyFunSuite with Matchers:

  private val accumulate = Accumulate()

  test("accumulate empty"):
    accumulate.accumulate[Int, Int](x => x * x, List()) should be(List())

  test("accumulate squares"):
    pending
    accumulate.accumulate[Int, Int](x => x * x, List(1, 2, 3)) should be(List(1, 4, 9))

  test("accumulate upcases"):
    pending
    accumulate.accumulate[String, String](_.toUpperCase, List("Hello", "world")) should be(List("HELLO", "WORLD"))

  test("accumulate reversed strings"):
    pending
    accumulate.accumulate[String, String](_.reverse, List("the", "quick", "brown", "fox", "etc")) should be(List("eht", "kciuq", "nworb", "xof", "cte"))

  test("accumulate recursively"):
    pending
    accumulate.accumulate[String, List[String]](x => accumulate.accumulate[String, String](y => x + y, List("1", "2", "3")), List("a", "b", "c")) should be(
      List(List("a1", "a2", "a3"), List("b1", "b2", "b3"), List("c1", "c2", "c3")),
    )
