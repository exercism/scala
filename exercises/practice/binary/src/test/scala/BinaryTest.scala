import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class BinaryTest extends AnyFunSuite with Matchers {
  test("empty string") {
    Binary("").toDecimal should be (0)
  }

  test("invalid string - all chars invalid") {
    pending
    Binary("carrot").toDecimal should be (0)
  }

  test("invalid string - leading char invalid") {
    pending
    Binary("a1111").toDecimal should be (0)
  }

  test("invalid string - trailing char invalid") {
    pending
    Binary("1111a").toDecimal should be (0)
  }

  test("invalid string - middle char invalid") {
    pending
    Binary("0101F0").toDecimal should be (0)
  }

  test("invalid string - invalid digits") {
    pending
    Binary("22").toDecimal should be (0)
  }

  test("1") {
    pending
    Binary("1").toDecimal should be (1)
  }

  test("2") {
    pending
    Binary("10").toDecimal should be (2)
  }

  test("3") {
    pending
    Binary("11").toDecimal should be (3)
  }

  test("4") {
    pending
    Binary("100").toDecimal should be (4)
  }

  test("9") {
    pending
    Binary("1001").toDecimal should be (9)
  }

  test("26") {
    pending
    Binary("11010").toDecimal should be (26)
  }

  test("Ultimate answer to everything") {
    pending
    Binary("101010").toDecimal should be (42)
  }

  test("1128") {
    pending
    Binary("10001101000").toDecimal should be (1128)
  }
}
