import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class PalindromeProductsTest extends AnyFunSuite with Matchers:

  // smallest and largest return Option[(Int, Set[(Int, Int)])]: the palindrome and every pair of factors
  // that makes it, or None when the range has no palindrome product or min is more than max.

  test("find the smallest palindrome from single digit factors"):
    PalindromeProducts(1, 9).smallest should be(Some((1, Set((1, 1)))))

  test("find the largest palindrome from single digit factors"):
    pending
    PalindromeProducts(1, 9).largest should be(Some((9, Set((1, 9), (3, 3)))))

  test("find the smallest palindrome from double digit factors"):
    pending
    PalindromeProducts(10, 99).smallest should be(Some((121, Set((11, 11)))))

  test("find the largest palindrome from double digit factors"):
    pending
    PalindromeProducts(10, 99).largest should be(Some((9009, Set((91, 99)))))

  test("find the smallest palindrome from triple digit factors"):
    pending
    PalindromeProducts(100, 999).smallest should be(Some((10201, Set((101, 101)))))

  test("find the largest palindrome from triple digit factors"):
    pending
    PalindromeProducts(100, 999).largest should be(Some((906609, Set((913, 993)))))

  test("find the smallest palindrome from four digit factors"):
    pending
    PalindromeProducts(1000, 9999).smallest should be(Some((1002001, Set((1001, 1001)))))

  test("find the largest palindrome from four digit factors"):
    pending
    PalindromeProducts(1000, 9999).largest should be(Some((99000099, Set((9901, 9999)))))

  test("empty result for smallest if no palindrome in the range"):
    pending
    PalindromeProducts(1002, 1003).smallest should be(None)

  test("empty result for largest if no palindrome in the range"):
    pending
    PalindromeProducts(15, 15).largest should be(None)

  test("error result for smallest if min is more than max"):
    pending
    PalindromeProducts(10000, 1).smallest should be(None)

  test("error result for largest if min is more than max"):
    pending
    PalindromeProducts(2, 1).largest should be(None)

  test("smallest product does not use the smallest factor"):
    pending
    PalindromeProducts(3215, 4000).smallest should be(Some((10988901, Set((3297, 3333)))))
