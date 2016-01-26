import org.scalatest._
import java.util.{TimeZone, GregorianCalendar}

class GigasecondTests extends FunSuite with Matchers {
  test ("1") {
    // Note: Months are 0-indexed. 3 = April
    val cal = new GregorianCalendar(2011, 3, 25)
    cal.setTimeZone(TimeZone.getTimeZone("GMT"))
    val gs = Gigasecond(cal)

    val expected = new GregorianCalendar(2043, 0, 1, 1, 46, 40)
    expected.setTimeZone(TimeZone.getTimeZone("GMT"))
    gs.date should be (expected)
  }

  test ("2") {
    pending
    val cal = new GregorianCalendar(1977, 5, 13)
    cal.setTimeZone(TimeZone.getTimeZone("GMT"))
    val gs = Gigasecond(cal)

    val expected = new GregorianCalendar(2009, 1, 19, 1, 46, 40)
    expected.setTimeZone(TimeZone.getTimeZone("GMT"))
    gs.date should be (expected)
  }

  test ("3") {
    pending
    val cal = new GregorianCalendar(1959, 6, 19)
    cal.setTimeZone(TimeZone.getTimeZone("GMT"))
    val gs = Gigasecond(cal)

    val expected = new GregorianCalendar(1991, 2, 27, 1, 46, 40)
    expected.setTimeZone(TimeZone.getTimeZone("GMT"))
    gs.date should be (expected)
  }

  test ("4") {
    pending
    val cal = new GregorianCalendar(2015, 0, 24, 23, 59, 59)
    cal.setTimeZone(TimeZone.getTimeZone("GMT"))
    val gs = Gigasecond(cal)

    val expected = new GregorianCalendar(2046, 9, 3, 1, 46, 39)
    expected.setTimeZone(TimeZone.getTimeZone("GMT"))
    gs.date should be (expected)
  }

  test ("yourself") {
    pending
    // val yourBirthday = new GregorianCalendar(year, month-1, day)
    // val gs = Gigasecond(yourBirthday)
    // gs.date should be (new GregorianCalendar(2009, 0, 31, 0, 46, 40))
  }
}
