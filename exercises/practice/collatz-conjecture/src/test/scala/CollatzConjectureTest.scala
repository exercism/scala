import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.2.0 */
class CollatzConjectureTest extends AnyFunSuite with Matchers {

  test("zero steps for one") {
    CollatzConjecture.steps(1) should be (Some(0))
  }

  test("divide if even") {
    pending
    CollatzConjecture.steps(16) should be (Some(4))
  }

  test("even and odd steps") {
    pending
    CollatzConjecture.steps(12) should be (Some(9))
  }

  test("Large number of even and odd steps") {
    pending
    CollatzConjecture.steps(1000000) should be (Some(152))
  }

  test("zero is an error") {
    pending
    CollatzConjecture.steps(0) should be (None)
  }

  test("negative value is an error") {
    pending
    CollatzConjecture.steps(-15) should be (None)
  }
}