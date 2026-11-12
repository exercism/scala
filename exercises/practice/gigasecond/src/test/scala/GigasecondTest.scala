import java.time.LocalDate
import java.time.LocalDateTime

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class GigasecondTest extends AnyFunSuite with Matchers:

  test("date only specification of time"):
    Gigasecond.add(LocalDate.parse("2011-04-25")) should be(LocalDateTime.parse("2043-01-01T01:46:40"))

  test("second test for date only specification of time"):
    pending
    Gigasecond.add(LocalDate.parse("1977-06-13")) should be(LocalDateTime.parse("2009-02-19T01:46:40"))

  test("third test for date only specification of time"):
    pending
    Gigasecond.add(LocalDate.parse("1959-07-19")) should be(LocalDateTime.parse("1991-03-27T01:46:40"))

  test("full time specified"):
    pending
    Gigasecond.add(LocalDateTime.parse("2015-01-24T22:00:00")) should be(LocalDateTime.parse("2046-10-02T23:46:40"))

  test("full time with day roll-over"):
    pending
    Gigasecond.add(LocalDateTime.parse("2015-01-24T23:59:59")) should be(LocalDateTime.parse("2046-10-03T01:46:39"))
