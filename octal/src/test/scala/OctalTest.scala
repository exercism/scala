import org.scalatest.{Matchers, FlatSpec}

class OctalTest extends FlatSpec with Matchers {
  it should "empty string should error" in {
    intercept[IllegalArgumentException] {
      Octal.octalToInt("")
    }
  }

  it should "invalid octal should error" in {
    intercept[IllegalArgumentException] {
      Octal.octalToInt("1239")
    }

    intercept[IllegalArgumentException] {
      Octal.octalToInt("FF")
    }
  }

  it should "handle zeros" in {
    Octal.octalToInt("00000000") should equal(0)
  }

  it should "handle single digit" in {
    Octal.octalToInt("0") should equal(0)
    Octal.octalToInt("7") should equal(7)
  }

  it should "handle multiple digits" in {
    Octal.octalToInt("10") should equal(8)
    Octal.octalToInt("17") should equal(15)
    Octal.octalToInt("1234567") should equal(342391)
  }

  it should "handle Int 0" in {
    Octal.intToOctal(0) should equal("0")
  }

  it should "handle Int to multi digit octal" in {
    Octal.intToOctal(9) should equal("11")
    Octal.intToOctal(342391) should equal("1234567")
  }

  it should "handle Int to octal with trailing zeros" in {
    Octal.intToOctal(8) should equal("10")
  }
}
