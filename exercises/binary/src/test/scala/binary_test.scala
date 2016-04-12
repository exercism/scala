import org.scalatest.{Matchers, FunSuite}

class BinaryTest extends FunSuite with Matchers {
  test("empty string") {
    Binary("").toDecimal should be (0)
  }

  test("invalid string - all chars invalid") {
    Binary("carrot").toDecimal should be (0)
  }

  test("invalid string - leading char invalid") {
    Binary("a1111").toDecimal should be (0)
  }

  test("invalid string - trailing char invalid") {
    Binary("1111a").toDecimal should be (0)
  }

  test("invalid string - middle char invalid") {
    Binary("0101F0").toDecimal should be (0)
  }

  test("invalid string - invalid digits") {
    Binary("22").toDecimal should be (0)
  }

  test("1") {
    Binary("1").toDecimal should be (1)
  }

  test("2") {
    Binary("10").toDecimal should be (2)
  }

  test("3") {
    Binary("11").toDecimal should be (3)
  }

  test("4") {
    Binary("100").toDecimal should be (4)
  }

  test("9") {
    Binary("1001").toDecimal should be (9)
  }

  test("26") {
    Binary("11010").toDecimal should be (26)
  }

  test("Ultimate answer to everything") {
    Binary("101010").toDecimal should be (42)
  }

  test("1128") {
    Binary("10001101000").toDecimal should be (1128)
  }
}
