import org.scalatest._

class PhoneNumberTest extends FlatSpec with Matchers {
  behavior of "number"

  it should "clean the number" in {
    val number = new PhoneNumber("(123) 456-7890").number
    number should be (Some("1234567890"))
  }

  it should "clean numbers with dots" in {
    pending
    val number = new PhoneNumber("123.456.7890").number
    number should be (Some("1234567890"))
  }

  it should "be valid when 11 digits and first is 1" in {
    pending
    val number = new PhoneNumber("11234567890").number
    number should be (Some("1234567890"))
  }

  it should "be invalid when 11 digits" in {
    pending
    val number = new PhoneNumber("21234567890").number
    number should be (None)
  }

  it should "be invalid when 9 digits" in {
    pending
    val number = new PhoneNumber("123456789").number
    number should be (None)
  }

  it should "be invalid when 12 digits" in {
    pending
    val number = new PhoneNumber("123456789012").number
    number should be (None)
  }

  it should "be invalid when no digits present" in {
    pending
    val number = new PhoneNumber(" (-) ").number
    number should be (None)
  }

  it should "be valid with leading characters" in {
    pending
    val number = new PhoneNumber("my number is 235 813 2134").number
    number should be (Some("2358132134"))
  }

  it should "be valid with trailing characters" in {
    pending
    val number = new PhoneNumber("987 654 3210 - bob").number
    number should be (Some("9876543210"))
  }

  it should "be valid amidst text and punctuation" in {
    pending
    val number = new PhoneNumber("Here it is: 415-888-0000. Thanks!").number
    number should be (Some("4158880000"))
  }

  behavior of "areaCode"

  it should "give the area code" in {
    pending
    val number = new PhoneNumber("1234567890")
    number.areaCode should be(Some("123"))
  }

  it should "give the area code with parentheses" in {
    pending
    val number = new PhoneNumber("(612) 555-1212")
    number.areaCode should be(Some("612"))
  }

  it should "give the area code with leading characters" in {
    pending
    val number = new PhoneNumber("my number is 235 813 2134")
    number.areaCode should be(Some("235"))
  }

  it should "be invalid when no digits present" in {
    pending
    val number = new PhoneNumber(" (-) ")
    number.areaCode should be (None)
  }

  behavior of "prettyPrint"

  it should "format the number" in {
    pending
    val number = new PhoneNumber("1234567890")
    number.prettyPrint should be (Some("(123) 456-7890"))
  }

  it should "format full US phone numbers" in {
    pending
    val number = new PhoneNumber("11234567890")
    number.prettyPrint should be (Some("(123) 456-7890"))
  }

  it should "format the number amidst text and punctuation" in {
    pending
    val number = new PhoneNumber("Here it is: 415-888-0000. Thanks!")
    number.prettyPrint should be (Some("(415) 888-0000"))
  }

  it should "be invalid for invalid numbers" in {
    pending
    val number = new PhoneNumber("I am invalid!")
    number.prettyPrint should be (None)
  }
}

