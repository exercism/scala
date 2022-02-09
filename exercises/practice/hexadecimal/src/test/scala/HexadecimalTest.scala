import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class HexadecimalTest extends AnyFlatSpec with Matchers {
  it should "handle empty" in {
    Hexadecimal.hexToInt("") should equal(0)
  }

  it should "handle zeros" in {
    pending
    Hexadecimal.hexToInt("00000000") should equal(0)
  }

  it should "handle single digit" in {
    pending
    Hexadecimal.hexToInt("1") should equal(1)
  }

  it should "handle single hex digit" in {
    pending
    Hexadecimal.hexToInt("c") should equal(12)
  }

  it should "handle upper case" in {
    pending
    Hexadecimal.hexToInt("C") should equal(12)
  }

  it should "handle multiple digits" in {
    pending
    Hexadecimal.hexToInt("10") should equal(16)
  }

  it should "handle multiple hex digits" in {
    pending
    Hexadecimal.hexToInt("AF") should equal(175)
  }

  it should "handle complex strings" in {
    pending
    Hexadecimal.hexToInt("19ace") should equal(105166)
    Hexadecimal.hexToInt("ffffff") should equal(16777215)
    Hexadecimal.hexToInt("ffff00") should equal(16776960)
  }

  it should "handle invalid strings" in {
    pending
    Hexadecimal.hexToInt("carrot") should equal(0)
    Hexadecimal.hexToInt("abczcba") should equal(0)
  }
}
