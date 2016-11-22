import org.scalatest.{FunSuite, Matchers}

class DominoesSuite extends FunSuite with Matchers {

  test("empty input = empty output") {
    check(List(), true)
  }

  test("singleton input = singleton output") {
    pending
    check(List((1, 1)), true)
  }

  test("singleton that can't be chained") {
    pending
    check(List((1, 2)), false)
  }

  test("three elements") {
    pending
    check(List((1, 2), (3, 1), (2, 3)), true)
  }

  test("can reverse dominoes") {
    pending
    check(List((1, 2), (1, 3), (2, 3)), true)
  }

  test("can't be chained") {
    pending
    check(List((1, 2), (4, 1), (2, 3)), false)
  }

  test("disconnected - simple") {
    pending
    check(List((1, 1), (2, 2)), false)
  }

  test("disconnected - double loop") {
    pending
    check(List((1, 2), (2, 1), (3, 4), (4, 3)), false)
  }

  test("disconnected - single isolated") {
    pending
    check(List((1, 2), (2, 3), (3, 1), (4, 4)), false)
  }

  test("need backtrack") {
    pending
    check(List((1, 2), (2, 3), (3, 1), (2, 4), (2, 4)), true)
  }

  test("separate loops") {
    pending
    check(List((1, 2), (2, 3), (3, 1), (1, 1), (2, 2), (3, 3)), true)
  }

  test("ten elements") {
    pending
    check(List((1, 2), (5, 3), (3, 1), (1, 2), (2, 4), (1, 6), (2, 3), (3, 4), (5, 6)), true)
  }

  private def check(input: List[(Int, Int)], hasResult: Boolean): Unit = {
    val result = Dominoes.chain(input)
    if (hasResult) {
      checkChain(result getOrElse fail("should have had a chain, but didn't"), input)
    }
    else assert(result == None)
  }

  private def checkChain(result: List[(Int, Int)], input: List[(Int, Int)]): Unit = {
    def sortDomino(ab: (Int, Int)): (Int, Int) =
      if (x._1 > x._2) x.swap else x
    def consecutivesShouldMatch(dominoes: List[((Int, Int), Int)]): Unit =
      dominoes.tails foreach {
        case (a@(_,x), i1)::(b@(y,_), i2)::_ =>
          assert(x == y, s"dominoes $i1 and $i2 don't match: $a $b")
        case _ =>
      }
    def endsShouldMatch: Unit =
      if (!result.isEmpty)
        consecutivesShouldMatch(List((result.last, result.length - 1),
                                     (result.head, 0)))

    assert(result.map(sortDomino).sorted == input.map(sortDomino).sorted,
        "doesn't consist of input dominoes")
    consecutivesShouldMatch(result.zipWithIndex)
    endsShouldMatch
  }
}
