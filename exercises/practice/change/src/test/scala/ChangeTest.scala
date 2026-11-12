import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class ChangeTest extends AnyFunSuite with Matchers:

  test("change for 1 cent"):
    Change.findFewestCoins(1, List(1, 5, 10, 25)) should be(Some(List(1)))

  test("single coin change"):
    pending
    Change.findFewestCoins(25, List(1, 5, 10, 25, 100)) should be(Some(List(25)))

  test("multiple coin change"):
    pending
    Change.findFewestCoins(15, List(1, 5, 10, 25, 100)) should be(Some(List(5, 10)))

  test("change with Lilliputian Coins"):
    pending
    Change.findFewestCoins(23, List(1, 4, 15, 20, 50)) should be(Some(List(4, 4, 15)))

  test("change with Lower Elbonia Coins"):
    pending
    Change.findFewestCoins(63, List(1, 5, 10, 21, 25)) should be(Some(List(21, 21, 21)))

  test("large target values"):
    pending
    Change.findFewestCoins(999, List(1, 2, 5, 10, 20, 50, 100)) should be(Some(List(2, 2, 5, 20, 20, 50, 100, 100, 100, 100, 100, 100, 100, 100, 100)))

  test("possible change without unit coins available"):
    pending
    Change.findFewestCoins(21, List(2, 5, 10, 20, 50)) should be(Some(List(2, 2, 2, 5, 10)))

  test("another possible change without unit coins available"):
    pending
    Change.findFewestCoins(27, List(4, 5)) should be(Some(List(4, 4, 4, 5, 5, 5)))

  test("a greedy approach is not optimal"):
    pending
    Change.findFewestCoins(20, List(1, 10, 11)) should be(Some(List(10, 10)))

  test("no coins make 0 change"):
    pending
    Change.findFewestCoins(0, List(1, 5, 10, 21, 25)) should be(Some(List()))

  test("error testing for change smaller than the smallest of coins"):
    pending
    Change.findFewestCoins(3, List(5, 10)) should be(None)

  test("error if no combination can add up to target"):
    pending
    Change.findFewestCoins(94, List(5, 10)) should be(None)

  test("cannot find negative change values"):
    pending
    Change.findFewestCoins(-5, List(1, 2, 5)) should be(None)
