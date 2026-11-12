import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class QueenAttackTest extends AnyFunSuite with Matchers:

  private def create(row: Int, column: Int): Queen =
    Queen.create(row, column).getOrElse(fail(s"Error creating queen at ($row, $column)"))

  test("Test creation of Queens with valid and invalid positions - queen with a valid position"):
    Queen.create(2, 2) should be(Some(Queen(2, 2)))

  test("Test creation of Queens with valid and invalid positions - queen must have positive row"):
    pending
    Queen.create(-2, 2) should be(None)

  test("Test creation of Queens with valid and invalid positions - queen must have row on board"):
    pending
    Queen.create(8, 4) should be(None)

  test("Test creation of Queens with valid and invalid positions - queen must have positive column"):
    pending
    Queen.create(2, -2) should be(None)

  test("Test creation of Queens with valid and invalid positions - queen must have column on board"):
    pending
    Queen.create(4, 8) should be(None)

  test("Test the ability of one queen to attack another - cannot attack"):
    pending
    QueenAttack.canAttack(create(2, 4), create(6, 6)) should be(false)

  test("Test the ability of one queen to attack another - can attack on same row"):
    pending
    QueenAttack.canAttack(create(2, 4), create(2, 6)) should be(true)

  test("Test the ability of one queen to attack another - can attack on same column"):
    pending
    QueenAttack.canAttack(create(4, 5), create(2, 5)) should be(true)

  test("Test the ability of one queen to attack another - can attack on first diagonal"):
    pending
    QueenAttack.canAttack(create(2, 2), create(0, 4)) should be(true)

  test("Test the ability of one queen to attack another - can attack on second diagonal"):
    pending
    QueenAttack.canAttack(create(2, 2), create(3, 1)) should be(true)

  test("Test the ability of one queen to attack another - can attack on third diagonal"):
    pending
    QueenAttack.canAttack(create(2, 2), create(1, 1)) should be(true)

  test("Test the ability of one queen to attack another - can attack on fourth diagonal"):
    pending
    QueenAttack.canAttack(create(1, 7), create(0, 6)) should be(true)

  test(
    "Test the ability of one queen to attack another - cannot attack if falling diagonals are only the same when reflected across the longest falling diagonal",
  ):
    pending
    QueenAttack.canAttack(create(4, 1), create(2, 5)) should be(false)
