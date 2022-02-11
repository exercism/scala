import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 2.1.0 */
class QueenAttackTest extends AnyFunSuite with Matchers {

  private def create(x: Int, y: Int): Queen = {
    Queen.create(x, y) match {
      case Some(q) => q
      case None => fail("Error creating queen")
    }
  }

  test("queen with a valid position") {
    Queen.create(2, 2) should be (Some(Queen(2, 2)))
  }

  test("queen must have positive row") {
    pending
    Queen.create(-2, 2) should be (None)
  }

  test("queen must have row on board") {
    pending
    Queen.create(8, 4) should be (None)
  }

  test("queen must have positive column") {
    pending
    Queen.create(2, -2) should be (None)
  }

  test("queen must have column on board") {
    pending
    Queen.create(4, 8) should be (None)
  }

  test("can not attack") {
    pending
    QueenAttack.canAttack(create(2, 4), create(6, 6)) should be (false)
  }

  test("can attack on same row") {
    pending
    QueenAttack.canAttack(create(2, 4), create(2, 6)) should be (true)
  }

  test("can attack on same column") {
    pending
    QueenAttack.canAttack(create(4, 5), create(2, 5)) should be (true)
  }

  test("can attack on first diagonal") {
    pending
    QueenAttack.canAttack(create(2, 2), create(0, 4)) should be (true)
  }

  test("can attack on second diagonal") {
    pending
    QueenAttack.canAttack(create(2, 2), create(3, 1)) should be (true)
  }

  test("can attack on third diagonal") {
    pending
    QueenAttack.canAttack(create(2, 2), create(1, 1)) should be (true)
  }

  test("can attack on fourth diagonal") {
    pending
    QueenAttack.canAttack(create(2, 2), create(5, 5)) should be (true)
  }
}
