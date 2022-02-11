import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class PalindromeProductsTest extends AnyFunSuite with Matchers {

  // PalindromeProducts largest call is expecting a return type of
  // Option[(Int, Set[(Int, Int)])] - None is expected for error cases.
  // Some is expected for valid cases. The first element of the tuple 
  // is the largest palindrome product value. The second element of the
  // tuple is the list of factors.
  // PalindromeProducts smallest call is expecting a return type of
  // Option[(Int, Set[(Int, Int)])] - None is expected for error cases.
  // Some is expected for valid cases. The first element of the tuple 
  // is the smallest palindrome product value. The second element of the
  // tuple is the list of factors.

  test("finds the smallest palindrome from single digit factors") {
    PalindromeProducts(1, 9).smallest should be (Some((1, Set((1, 1)))))
  }

  test("finds the largest palindrome from single digit factors") {
    pending
    PalindromeProducts(1, 9).largest should be (Some((9, Set((1, 9), (3, 3)))))
  }

  test("find the smallest palindrome from double digit factors") {
    pending
    PalindromeProducts(10, 99).smallest should be (Some((121, Set((11, 11)))))
  }

  test("find the largest palindrome from double digit factors") {
    pending
    PalindromeProducts(10, 99).largest should be (Some((9009, Set((91, 99)))))
  }

  test("find smallest palindrome from triple digit factors") {
    pending
    PalindromeProducts(100, 999).smallest should be (Some((10201, Set((101, 101)))))
  }

  test("find the largest palindrome from triple digit factors") {
    pending
    PalindromeProducts(100, 999).largest should be (Some((906609, Set((913, 993)))))
  }

  test("find smallest palindrome from four digit factors") {
    pending
    PalindromeProducts(1000, 9999).smallest should be (Some((1002001, Set((1001, 1001)))))
  }

  test("find the largest palindrome from four digit factors") {
    pending
    PalindromeProducts(1000, 9999).largest should be (Some((99000099, Set((9901, 9999)))))
  }

  test("empty result for smallest if no palindrome in the range") {
    pending
    PalindromeProducts(1002, 1003).smallest should be (None)
  }

  test("empty result for largest if no palindrome in the range") {
    pending
    PalindromeProducts(15, 15).largest should be (None)
  }

  test("error result for smallest if min is more than max") {
    pending
    PalindromeProducts(10000, 1).smallest should be (None)
  }

  test("error result for largest if min is more than max") {
    pending
    PalindromeProducts(2, 1).largest should be (None)
  }
}
