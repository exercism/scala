import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.2.0 */
class TwoferTest extends AnyFunSuite with Matchers {

  test("no name given") {
    Twofer.twofer() should be ("One for you, one for me.")
  }

  test("a name given") {
    pending
    Twofer.twofer("Alice") should be ("One for Alice, one for me.")
  }

  test("another name given") {
    pending
    Twofer.twofer("Bob") should be ("One for Bob, one for me.")
  }
}