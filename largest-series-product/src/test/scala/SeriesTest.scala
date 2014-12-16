import org.scalatest.{Matchers, FlatSpec}

class SeriesTest extends FlatSpec with Matchers {
  it should "map digits" in {
    Series.digits(('0' to '9').mkString) should equal (0 to 9)
    Series.digits(('0' to '9').reverse.mkString) should equal ((0 to 9).reverse)
    Series.digits(('4' to '8').reverse.mkString) should equal ((4 to 8).reverse)
    Series.digits("936923468") should equal (List(9, 3, 6, 9, 2, 3, 4, 6, 8))
  }

  it should "slice" in {
    Series.slices(2, "98273463") should equal(List(List(9, 8), List(8, 2),
      List(2, 7), List(7, 3), List(3, 4), List(4, 6), List(6, 3)))
    Series.slices(3, "982347") should equal(List(List(9, 8, 2), List(8, 2, 3),
      List(2, 3, 4), List(3, 4, 7)))
  }

  it should "slice boundary conditions" in {
    Series.slices(4, "982") should equal(List())
    Series.slices(3, "982") should equal(List(List(9, 8, 2)))
  }

  it should "find largestProduct" in {
    Series.largestProduct(2, "0123456789") should equal(72)
    Series.largestProduct(2, "19") should equal(9)
    Series.largestProduct(2, "576802143") should equal(48)
    Series.largestProduct(3, "0123456789") should equal(504)
    Series.largestProduct(3, "1027839564") should equal(270)
  }

  it should "find largestProduct in long strings" in {
    Series.largestProduct(6,
      "73167176531330624919225119674426574742355349194934") should equal(23520)
    Series.largestProduct(6,
      "52677741234314237566414902593461595376319419139427") should equal(28350)
  }

  it should "find largestProduct boundary conditions" in {
    //unlike the Ruby implementation, no error is expected for too small input
    Series.largestProduct(1, "") should equal(1)
    Series.largestProduct(4, "123") should equal(1)
    Series.largestProduct(2, "00") should equal(0)
  }
}

