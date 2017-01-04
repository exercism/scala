import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import org.scalatest.FunSuite
import org.scalatest.Matchers

class GigasecondTests extends FunSuite with Matchers {

  private def dateTime(str: String): LocalDateTime =
    LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(str))

  private def date(str: String): LocalDate =
    LocalDate.from(DateTimeFormatter.ISO_DATE.parse(str))


  test("modern time") {
    val input = date("2011-04-25")
    val expected = dateTime("2043-01-01T01:46:40")
    Gigasecond.addGigaseconds(input) should be (expected)
  }

  test("after epoch time") {
    pending
    val input = date("1977-06-13")
    val expected = dateTime("2009-02-19T01:46:40")
    Gigasecond.addGigaseconds(input) should be (expected)
  }

  test("before epoch time") {
    pending
    val input = date("1959-07-19")
    val expected = dateTime("1991-03-27T01:46:40")
    Gigasecond.addGigaseconds(input) should be (expected)
  }

  test("full time specified") {
    pending
    val input = dateTime("2015-01-24T22:00:00")
    val expected = dateTime("2046-10-02T23:46:40")
    Gigasecond.addGigaseconds(input) should be (expected)
  }

  test("full time with day roll-over") {
    pending
    val input = dateTime("2015-01-24T23:59:59")
    val expected = dateTime("2046-10-03T01:46:39")
    Gigasecond.addGigaseconds(input) should be (expected)
  }

  test("your birthday") {
    pending
    val yourBirthday = date(???)
    val expected = dateTime(???)
    Gigasecond.addGigaseconds(yourBirthday) should be (expected)
  }
}
