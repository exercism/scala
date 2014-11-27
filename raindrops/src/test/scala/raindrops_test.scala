import org.scalatest.{Matchers, FlatSpec}

class RaindropsTest extends FlatSpec with Matchers {
  it should "convert 1" in {
    Raindrops().convert(1) should equal("1")
  }

  it should "convert 3" in {
    Raindrops().convert(3) should equal("Pling")
  }

  it should "convert 5" in {
    Raindrops().convert(5) should equal("Plang")
  }

  it should "convert 7" in {
    Raindrops().convert(7) should equal("Plong")
  }

  it should "convert 6" in {
    Raindrops().convert(6) should equal("Pling")
  }

  it should "convert 9" in {
    Raindrops().convert(9) should equal("Pling")
  }

  it should "convert 10" in {
    Raindrops().convert(10) should equal("Plang")
  }

  it should "convert 14" in {
    Raindrops().convert(14) should equal("Plong")
  }

  it should "convert 15" in {
    Raindrops().convert(15) should equal("PlingPlang")
  }

  it should "convert 21" in {
    Raindrops().convert(21) should equal("PlingPlong")
  }

  it should "convert 25" in {
    Raindrops().convert(25) should equal("Plang")
  }

  it should "convert 35" in {
    Raindrops().convert(35) should equal("PlangPlong")
  }

  it should "convert 49" in {
    Raindrops().convert(49) should equal("Plong")
  }

  it should "convert 52" in {
    Raindrops().convert(52) should equal("52")
  }

  it should "convert 105" in {
    Raindrops().convert(105) should equal("PlingPlangPlong")
  }

  it should "convert 12121" in {
    Raindrops().convert(12121) should equal("12121")
  }
}

