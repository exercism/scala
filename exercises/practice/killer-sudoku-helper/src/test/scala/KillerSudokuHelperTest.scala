import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class KillerSudokuHelperTest extends AnyFunSuite with Matchers:

  test("Trivial 1-digit cages - 1"):
    KillerSudokuHelper.combinations(1, 1, List()) should be(List(List(1)))

  test("Trivial 1-digit cages - 2"):
    pending
    KillerSudokuHelper.combinations(2, 1, List()) should be(List(List(2)))

  test("Trivial 1-digit cages - 3"):
    pending
    KillerSudokuHelper.combinations(3, 1, List()) should be(List(List(3)))

  test("Trivial 1-digit cages - 4"):
    pending
    KillerSudokuHelper.combinations(4, 1, List()) should be(List(List(4)))

  test("Trivial 1-digit cages - 5"):
    pending
    KillerSudokuHelper.combinations(5, 1, List()) should be(List(List(5)))

  test("Trivial 1-digit cages - 6"):
    pending
    KillerSudokuHelper.combinations(6, 1, List()) should be(List(List(6)))

  test("Trivial 1-digit cages - 7"):
    pending
    KillerSudokuHelper.combinations(7, 1, List()) should be(List(List(7)))

  test("Trivial 1-digit cages - 8"):
    pending
    KillerSudokuHelper.combinations(8, 1, List()) should be(List(List(8)))

  test("Trivial 1-digit cages - 9"):
    pending
    KillerSudokuHelper.combinations(9, 1, List()) should be(List(List(9)))

  test("Cage with sum 45 contains all digits 1:9"):
    pending
    KillerSudokuHelper.combinations(45, 9, List()) should be(List(List(1, 2, 3, 4, 5, 6, 7, 8, 9)))

  test("Cage with only 1 possible combination"):
    pending
    KillerSudokuHelper.combinations(7, 3, List()) should be(List(List(1, 2, 4)))

  test("Cage with several combinations"):
    pending
    KillerSudokuHelper.combinations(10, 2, List()) should be(List(List(1, 9), List(2, 8), List(3, 7), List(4, 6)))

  test("Cage with several combinations that is restricted"):
    pending
    KillerSudokuHelper.combinations(10, 2, List(1, 4)) should be(List(List(2, 8), List(3, 7)))
