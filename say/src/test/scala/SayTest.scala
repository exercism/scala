import org.scalatest.{Matchers, FunSuite}

class SayTest extends FunSuite with Matchers {
  test("zero") {
    Say.inEnglish(0) should be (Some("zero"))
  }

  test("one") {
    Say.inEnglish(1) should be (Some("one"))
  }

  test("fourteen") {
    Say.inEnglish(14) should be (Some("fourteen"))
  }

  test("twenty") {
    Say.inEnglish(20) should be (Some("twenty"))
  }

  test("twenty-two") {
    Say.inEnglish(22) should be (Some("twenty-two"))
  }

  test("fifty") {
    Say.inEnglish(50) should be (Some("fifty"))
  }

  test("ninety-eight") {
    Say.inEnglish(98) should be (Some("ninety-eight"))
  }

  test("one hundred") {
    Say.inEnglish(100) should be (Some("one hundred"))
  }

  test("one hundred twenty-three") {
    Say.inEnglish(123) should be (Some("one hundred twenty-three"))
  }

  test("one thousand") {
    Say.inEnglish(1000) should be (Some("one thousand"))
  }

  test("one thousand two hundred thirty-four") {
    Say.inEnglish(1234) should be (Some("one thousand two hundred thirty-four"))
  }

  test("one million") {
    Say.inEnglish(1000000) should be (Some("one million"))
  }

  test("one million two") {
    Say.inEnglish(1000002) should be (Some("one million two"))
  }

  test("one million two thousand three hundred forty-five") {
    Say.inEnglish(1002345) should be (Some("one million two thousand three hundred forty-five"))
  }

  test("one billion") {
    Say.inEnglish(1000000000) should be (Some("one billion"))
  }

  test("A big number") {
    Say.inEnglish(987654321123L) should be (Some("nine hundred eighty-seven billion " +
      "six hundred fifty-four million three hundred twenty-one thousand one hundred twenty-three"))
  }

  test("Lower bound") {
    Say.inEnglish(-1) should be (None)
  }

  test("Upper bound") {
    Say.inEnglish(1000000000000L) should be (None)
  }
}
