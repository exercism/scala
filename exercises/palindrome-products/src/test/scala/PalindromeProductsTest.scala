import org.scalatest.{Matchers, FlatSpec}

class PalindromeProductsTest extends FlatSpec with Matchers {

  it should "find smallest palindrome from a single digit factors" in {
    val (value, factors) = PalindromeProducts(1, 9).smallest
    value should be (1)
    factors should be (Set((1, 1)))
  }

  it should "find largest palindrome from a single digit factors" in {
    val (value, factors) = PalindromeProducts(0, 9).largest
    value should be (9)
    factors should be (Set((1, 9), (3, 3)))
  }

  it should "find smallest palindrome from a double digit factors" in {
    val (value, factors) = PalindromeProducts(10, 99).smallest
    value should be (121)
    factors should be (Set((11, 11)))
  }

  it should "find largest palindrome from a double digit factors" in {
    val (value, factors) = PalindromeProducts(10, 99).largest
    value should be (9009)
    factors should be (Set((91, 99)))
  }

  it should "find smallest palindrome from a triple digit factors" in {
    val (value, factors) = PalindromeProducts(100, 999).smallest
    value should be (10201)
    factors should be (Set((101, 101)))
  }

  it should "find largest palindrome from a triple digit factors" in {
    val (value, factors) = PalindromeProducts(100, 999).largest
    value should be (906609)
    factors should be (Set((913, 993)))
  }

  it should "find smallest palindrome from a four digit factors" in {
    val (value, factors) = PalindromeProducts(1000, 9999).smallest
    value should be (1002001)
    factors should be (Set((1001, 1001)))
  }

  it should "find largest palindrome from a four digit factors" in {
    val (value, factors) = PalindromeProducts(1000, 9999).largest
    value should be (99000099)
    factors should be (Set((9901, 9999)))
  }
}
