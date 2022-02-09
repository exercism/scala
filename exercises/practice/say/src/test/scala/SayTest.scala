import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class SayTest extends AnyFunSuite with Matchers {

  test("zero") {
    Say.inEnglish(0) should be(Some("zero"))
  }

  test("one") {
    pending
    Say.inEnglish(1) should be(Some("one"))
  }

  test("fourteen") {
    pending
    Say.inEnglish(14) should be(Some("fourteen"))
  }

  test("twenty") {
    pending
    Say.inEnglish(20) should be(Some("twenty"))
  }

  test("twenty-two") {
    pending
    Say.inEnglish(22) should be(Some("twenty-two"))
  }

  test("one hundred") {
    pending
    Say.inEnglish(100) should be(Some("one hundred"))
  }

  test("one hundred twenty-three") {
    pending
    Say.inEnglish(123) should be(Some("one hundred twenty-three"))
  }

  test("one thousand") {
    pending
    Say.inEnglish(1000) should be(Some("one thousand"))
  }

  test("one thousand two hundred thirty-four") {
    pending
    Say.inEnglish(1234) should be(Some("one thousand two hundred thirty-four"))
  }

  test("one million") {
    pending
    Say.inEnglish(1000000) should be(Some("one million"))
  }

  test("one million two thousand three hundred forty-five") {
    pending
    Say.inEnglish(1002345) should be(Some("one million two thousand three hundred forty-five"))
  }

  test("one billion") {
    pending
    Say.inEnglish(1000000000) should be(Some("one billion"))
  }

  test("a big number") {
    pending
    Say.inEnglish(987654321123l) should be(Some("nine hundred eighty-seven billion six hundred fifty-four million three hundred twenty-one thousand one hundred twenty-three"))
  }

  test("numbers below zero are out of range") {
    pending
    Say.inEnglish(-1) should be(None)
  }

  test("numbers above 999,999,999,999 are out of range") {
    pending
    Say.inEnglish(1000000000000l) should be(None)
  }
}
