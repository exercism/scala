import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class LargestSeriesProductTest extends AnyFunSuite with Matchers {

  test("finds the largest product if span equals length") {
    LargestSeriesProduct.largestProduct(2, "29") should be(Some(18))
  }

  test("can find the largest product of 2 with numbers in order") {
    pending
    LargestSeriesProduct.largestProduct(2, "0123456789") should be(Some(72))
  }

  test("can find the largest product of 2") {
    pending
    LargestSeriesProduct.largestProduct(2, "576802143") should be(Some(48))
  }

  test("can find the largest product of 3 with numbers in order") {
    pending
    LargestSeriesProduct.largestProduct(3, "0123456789") should be(Some(504))
  }

  test("can find the largest product of 3") {
    pending
    LargestSeriesProduct.largestProduct(3, "1027839564") should be(Some(270))
  }

  test("can find the largest product of 5 with numbers in order") {
    pending
    LargestSeriesProduct.largestProduct(5, "0123456789") should be(Some(15120))
  }

  test("can get the largest product of a big number") {
    pending
    LargestSeriesProduct.largestProduct(
      6,
      "73167176531330624919225119674426574742355349194934") should be(
      Some(23520))
  }

  test("reports zero if the only digits are zero") {
    pending
    LargestSeriesProduct.largestProduct(2, "0000") should be(Some(0))
  }

  test("reports zero if all spans include zero") {
    pending
    LargestSeriesProduct.largestProduct(3, "99099") should be(Some(0))
  }

  test("rejects span longer than string length") {
    pending
    LargestSeriesProduct.largestProduct(4, "123") should be(None)
  }

  test("rejects empty string and nonzero span") {
    pending
    LargestSeriesProduct.largestProduct(1, "") should be(None)
  }

  test("rejects invalid character in digits") {
    pending
    LargestSeriesProduct.largestProduct(2, "1234a5") should be(None)
  }

  test("rejects negative span") {
    pending
    LargestSeriesProduct.largestProduct(-1, "12345") should be(None)
  }
}
