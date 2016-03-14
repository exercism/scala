import org.scalatest.{Matchers, FlatSpec}

class SeriesTest extends FlatSpec with Matchers {
  it should "find largestProduct" in {
    Series.largestProduct(2, "0123456789") should equal(Some(72))
    Series.largestProduct(2, "19") should equal(Some(9))
    Series.largestProduct(2, "576802143") should equal(Some(48))
    Series.largestProduct(3, "0123456789") should equal(Some(504))
    Series.largestProduct(3, "1027839564") should equal(Some(270))
  }

  it should "find largestProduct in long strings" in {
    Series.largestProduct(6,
      "73167176531330624919225119674426574742355349194934") should equal(Some(23520))
    Series.largestProduct(6,
      "52677741234314237566414902593461595376319419139427") should equal(Some(28350))
  }

  it should "find largestProduct boundary conditions" in {
    Series.largestProduct(0, "") should equal(Some(1))
    Series.largestProduct(0, "123") should equal(Some(1))
    Series.largestProduct(1, "") should equal(None)
    Series.largestProduct(4, "123") should equal(None)
    Series.largestProduct(2, "00") should equal(Some(0))
    Series.largestProduct(3, "99099") should equal(Some(0))
  }
}

