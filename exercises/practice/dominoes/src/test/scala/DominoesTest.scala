import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class DominoesTest extends AnyFunSuite with Matchers:

  private def check(input: List[(Int, Int)], hasResult: Boolean): Unit =
    val result = Dominoes.chain(input)
    if hasResult then checkChain(result.getOrElse(fail("should have had a chain, but didn't")), input)
    else result should be(None)

  private def checkChain(result: List[(Int, Int)], input: List[(Int, Int)]): Unit =
    def sortDomino(domino: (Int, Int)): (Int, Int) = if domino._1 > domino._2 then domino.swap else domino

    def consecutivesShouldMatch(dominoes: List[((Int, Int), Int)]): Unit =
      for case List((a @ (_, x), i1), (b @ (y, _), i2)) <- dominoes.sliding(2) do assert(x == y, s"dominoes $i1 and $i2 don't match: $a $b")

    assert(result.map(sortDomino).sorted == input.map(sortDomino).sorted, "doesn't consist of input dominoes")
    consecutivesShouldMatch(result.zipWithIndex)
    if result.nonEmpty then consecutivesShouldMatch(List((result.last, result.length - 1), (result.head, 0)))

  test("empty input = empty output"):
    check(List(), true)

  test("singleton input = singleton output"):
    pending
    check(List((1, 1)), true)

  test("singleton that can't be chained"):
    pending
    check(List((1, 2)), false)

  test("three elements"):
    pending
    check(List((1, 2), (3, 1), (2, 3)), true)

  test("can reverse dominoes"):
    pending
    check(List((1, 2), (1, 3), (2, 3)), true)

  test("can't be chained"):
    pending
    check(List((1, 2), (4, 1), (2, 3)), false)

  test("disconnected - simple"):
    pending
    check(List((1, 1), (2, 2)), false)

  test("disconnected - double loop"):
    pending
    check(List((1, 2), (2, 1), (3, 4), (4, 3)), false)

  test("disconnected - single isolated"):
    pending
    check(List((1, 2), (2, 3), (3, 1), (4, 4)), false)

  test("need backtrack"):
    pending
    check(List((1, 2), (2, 3), (3, 1), (2, 4), (2, 4)), true)

  test("separate loops"):
    pending
    check(List((1, 2), (2, 3), (3, 1), (1, 1), (2, 2), (3, 3)), true)

  test("nine elements"):
    pending
    check(List((1, 2), (5, 3), (3, 1), (1, 2), (2, 4), (1, 6), (2, 3), (3, 4), (5, 6)), true)

  test("separate three-domino loops"):
    pending
    check(List((1, 2), (2, 3), (3, 1), (4, 5), (5, 6), (6, 4)), false)
