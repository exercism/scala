import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class ArmstrongNumbersTest extends AnyFunSuite with Matchers:

  test("Zero is an Armstrong number"):
    ArmstrongNumbers.isArmstrongNumber(0) should be(true)

  test("Single-digit numbers are Armstrong numbers"):
    pending
    ArmstrongNumbers.isArmstrongNumber(5) should be(true)

  test("There are no two-digit Armstrong numbers"):
    pending
    ArmstrongNumbers.isArmstrongNumber(10) should be(false)

  test("Three-digit number that is an Armstrong number"):
    pending
    ArmstrongNumbers.isArmstrongNumber(153) should be(true)

  test("Three-digit number that is not an Armstrong number"):
    pending
    ArmstrongNumbers.isArmstrongNumber(100) should be(false)

  test("Four-digit number that is an Armstrong number"):
    pending
    ArmstrongNumbers.isArmstrongNumber(9474) should be(true)

  test("Four-digit number that is not an Armstrong number"):
    pending
    ArmstrongNumbers.isArmstrongNumber(9475) should be(false)

  test("Seven-digit number that is an Armstrong number"):
    pending
    ArmstrongNumbers.isArmstrongNumber(9926315) should be(true)

  test("Seven-digit number that is not an Armstrong number"):
    pending
    ArmstrongNumbers.isArmstrongNumber(9926314) should be(false)
