import org.scalatest._
import java.util.GregorianCalendar

class MeetupTest extends FunSuite with Matchers {
  //Note: Java uses 0-indexed months for GregorianCalendar
  test ("monteenth of may 2013") {
    Meetup(5, 2013).teenth(Meetup.Mon) should be (new GregorianCalendar(2013, 4, 13))
  }

  test ("teenth respects month") {
    pending
    Meetup(8, 2013).teenth(Meetup.Mon) should be (new GregorianCalendar(2013, 7, 19))
  }

  test ("teenth respects year") {
    pending
    Meetup(8, 2016).teenth(Meetup.Mon) should be (new GregorianCalendar(2016, 7, 15))
  }

  test ("teenth respects weekday") {
    pending
    Meetup(3, 2013).teenth(Meetup.Tue) should be (new GregorianCalendar(2013, 2, 19))
  }

  test ("teenth works on a variety of dates") {
    pending
    Meetup(4, 2013).teenth(Meetup.Tue) should be (new GregorianCalendar(2013, 3, 16))
    Meetup(8, 2013).teenth(Meetup.Tue) should be (new GregorianCalendar(2013, 7, 13))
    Meetup(1, 2013).teenth(Meetup.Wed) should be (new GregorianCalendar(2013, 0, 16))
    Meetup(2, 2013).teenth(Meetup.Wed) should be (new GregorianCalendar(2013, 1, 13))
    Meetup(6, 2013).teenth(Meetup.Wed) should be (new GregorianCalendar(2013, 5, 19))
    Meetup(5, 2013).teenth(Meetup.Thu) should be (new GregorianCalendar(2013, 4, 16))
    Meetup(6, 2013).teenth(Meetup.Thu) should be (new GregorianCalendar(2013, 5, 13))
    Meetup(9, 2013).teenth(Meetup.Thu) should be (new GregorianCalendar(2013, 8, 19))
    Meetup(4, 2013).teenth(Meetup.Fri) should be (new GregorianCalendar(2013, 3, 19))
    Meetup(8, 2013).teenth(Meetup.Fri) should be (new GregorianCalendar(2013, 7, 16))
    Meetup(9, 2013).teenth(Meetup.Fri) should be (new GregorianCalendar(2013, 8, 13))
    Meetup(2, 2013).teenth(Meetup.Sat) should be (new GregorianCalendar(2013, 1, 16))
    Meetup(4, 2013).teenth(Meetup.Sat) should be (new GregorianCalendar(2013, 3, 13))
    Meetup(10, 2013).teenth(Meetup.Sat) should be (new GregorianCalendar(2013, 9, 19))
    Meetup(5, 2013).teenth(Meetup.Sun) should be (new GregorianCalendar(2013, 4, 19))
    Meetup(6, 2013).teenth(Meetup.Sun) should be (new GregorianCalendar(2013, 5, 16))
    Meetup(10, 2013).teenth(Meetup.Sun) should be (new GregorianCalendar(2013, 9, 13))
  }

  test ("first monday of march 2013") {
    pending
    Meetup(3, 2013).first(Meetup.Mon) should be (new GregorianCalendar(2013, 2, 4))
  }

  test ("first respects month") {
    pending
    Meetup(4, 2013).first(Meetup.Mon) should be (new GregorianCalendar(2013, 3, 1))
  }

  test ("first respects year") {
    pending
    Meetup(5, 2016).first(Meetup.Mon) should be (new GregorianCalendar(2016, 4, 2))
  }

  test ("first respects weekday") {
    pending
    Meetup(6, 2013).first(Meetup.Tue) should be (new GregorianCalendar(2013, 5, 4))
  }

  test ("first works on a variety of dates") {
    pending
    Meetup(7, 2013).first(Meetup.Wed) should be (new GregorianCalendar(2013, 6, 3))
    Meetup(8, 2013).first(Meetup.Wed) should be (new GregorianCalendar(2013, 7, 7))
    Meetup(9, 2013).first(Meetup.Thu) should be (new GregorianCalendar(2013, 8, 5))
    Meetup(10, 2013).first(Meetup.Thu) should be (new GregorianCalendar(2013, 9, 3))
    Meetup(11, 2013).first(Meetup.Fri) should be (new GregorianCalendar(2013, 10, 1))
    Meetup(12, 2013).first(Meetup.Fri) should be (new GregorianCalendar(2013, 11, 6))
    Meetup(1, 2013).first(Meetup.Sat) should be (new GregorianCalendar(2013, 0, 5))
    Meetup(2, 2013).first(Meetup.Sat) should be (new GregorianCalendar(2013, 1, 2))
    Meetup(3, 2013).first(Meetup.Sun) should be (new GregorianCalendar(2013, 2, 3))
    Meetup(4, 2013).first(Meetup.Sun) should be (new GregorianCalendar(2013, 3, 7))
  }

  test ("second monday of march 2013") {
    pending
    Meetup(3, 2013).second(Meetup.Mon) should be (new GregorianCalendar(2013, 2, 11))
  }

  test ("second respects month") {
    pending
    Meetup(4, 2013).second(Meetup.Mon) should be (new GregorianCalendar(2013, 3, 8))
  }

  test ("second respects year") {
    pending
    Meetup(5, 2016).second(Meetup.Mon) should be (new GregorianCalendar(2016, 4, 9))
  }

  test ("second respects weekday") {
    pending
    Meetup(6, 2013).second(Meetup.Tue) should be (new GregorianCalendar(2013, 5, 11))
  }

  test ("second works on a variety of dates") {
    pending
    Meetup(7, 2013).second(Meetup.Wed) should be (new GregorianCalendar(2013, 6, 10))
    Meetup(8, 2013).second(Meetup.Wed) should be (new GregorianCalendar(2013, 7, 14))
    Meetup(9, 2013).second(Meetup.Thu) should be (new GregorianCalendar(2013, 8, 12))
    Meetup(10, 2013).second(Meetup.Thu) should be (new GregorianCalendar(2013, 9, 10))
    Meetup(11, 2013).second(Meetup.Fri) should be (new GregorianCalendar(2013, 10, 8))
    Meetup(12, 2013).second(Meetup.Fri) should be (new GregorianCalendar(2013, 11, 13))
    Meetup(1, 2013).second(Meetup.Sat) should be (new GregorianCalendar(2013, 0, 12))
    Meetup(2, 2013).second(Meetup.Sat) should be (new GregorianCalendar(2013, 1, 9))
    Meetup(3, 2013).second(Meetup.Sun) should be (new GregorianCalendar(2013, 2, 10))
    Meetup(4, 2013).second(Meetup.Sun) should be (new GregorianCalendar(2013, 3, 14))
  }

  test ("third monday of march 2013") {
    pending
    Meetup(3, 2013).third(Meetup.Mon) should be (new GregorianCalendar(2013, 2, 18))
  }

  test ("third respects month") {
    pending
    Meetup(4, 2013).third(Meetup.Mon) should be (new GregorianCalendar(2013, 3, 15))
  }

  test ("third respects year") {
    pending
    Meetup(5, 2016).third(Meetup.Mon) should be (new GregorianCalendar(2016, 4, 16))
  }

  test ("third respects weekday") {
    pending
    Meetup(6, 2013).third(Meetup.Tue) should be (new GregorianCalendar(2013, 5, 18))
  }

  test ("third works for a variety of dates") {
    pending
    Meetup(7, 2013).third(Meetup.Wed) should be (new GregorianCalendar(2013, 6, 17))
    Meetup(8, 2013).third(Meetup.Wed) should be (new GregorianCalendar(2013, 7, 21))
    Meetup(9, 2013).third(Meetup.Thu) should be (new GregorianCalendar(2013, 8, 19))
    Meetup(10, 2013).third(Meetup.Thu) should be (new GregorianCalendar(2013, 9, 17))
    Meetup(11, 2013).third(Meetup.Fri) should be (new GregorianCalendar(2013, 10, 15))
    Meetup(12, 2013).third(Meetup.Fri) should be (new GregorianCalendar(2013, 11, 20))
    Meetup(1, 2013).third(Meetup.Sat) should be (new GregorianCalendar(2013, 0, 19))
    Meetup(2, 2013).third(Meetup.Sat) should be (new GregorianCalendar(2013, 1, 16))
    Meetup(3, 2013).third(Meetup.Sun) should be (new GregorianCalendar(2013, 2, 17))
    Meetup(4, 2013).third(Meetup.Sun) should be (new GregorianCalendar(2013, 3, 21))
  }

  test ("fourth monday of march 2013") {
    pending
    Meetup(3, 2013).fourth(Meetup.Mon) should be (new GregorianCalendar(2013, 2, 25))
  }

  test ("fourth respects month") {
    pending
    Meetup(4, 2013).fourth(Meetup.Mon) should be (new GregorianCalendar(2013, 3, 22))
  }

  test ("fourth respects year") {
    pending
    Meetup(5, 2016).fourth(Meetup.Mon) should be (new GregorianCalendar(2016, 4, 23))
  }

  test ("fourth respects weekday") {
    pending
    Meetup(6, 2013).fourth(Meetup.Tue) should be (new GregorianCalendar(2013, 5, 25))
  }

  test ("fourth works on a variety of dates") {
    pending
    Meetup(7, 2013).fourth(Meetup.Wed) should be (new GregorianCalendar(2013, 6, 24))
    Meetup(8, 2013).fourth(Meetup.Wed) should be (new GregorianCalendar(2013, 7, 28))
    Meetup(9, 2013).fourth(Meetup.Thu) should be (new GregorianCalendar(2013, 8, 26))
    Meetup(10, 2013).fourth(Meetup.Thu) should be (new GregorianCalendar(2013, 9, 24))
    Meetup(11, 2013).fourth(Meetup.Fri) should be (new GregorianCalendar(2013, 10, 22))
    Meetup(12, 2013).fourth(Meetup.Fri) should be (new GregorianCalendar(2013, 11, 27))
    Meetup(1, 2013).fourth(Meetup.Sat) should be (new GregorianCalendar(2013, 0, 26))
    Meetup(2, 2013).fourth(Meetup.Sat) should be (new GregorianCalendar(2013, 1, 23))
    Meetup(3, 2013).fourth(Meetup.Sun) should be (new GregorianCalendar(2013, 2, 24))
    Meetup(4, 2013).fourth(Meetup.Sun) should be (new GregorianCalendar(2013, 3, 28))
  }

  test ("last monday of march 2013") {
    pending
    Meetup(3, 2013).last(Meetup.Mon) should be (new GregorianCalendar(2013, 2, 25))
  }

  test ("last respects month") {
    pending
    Meetup(4, 2013).last(Meetup.Mon) should be (new GregorianCalendar(2013, 3, 29))
  }

  test ("last respects year") {
    pending
    Meetup(6, 2016).last(Meetup.Mon) should be (new GregorianCalendar(2016, 5, 27))
  }

  test ("last respects weekday") {
    pending
    Meetup(5, 2013).last(Meetup.Tue) should be (new GregorianCalendar(2013, 4, 28))
  }

  test ("works on a leap year") {
    pending
    Meetup(2, 2016).last(Meetup.Sun) should be (new GregorianCalendar(2016, 1, 28))
    Meetup(2, 2016).last(Meetup.Mon) should be (new GregorianCalendar(2016, 1, 29))
  }

  test ("last works on a variety of dates") {
    pending
    Meetup(7, 2013).last(Meetup.Wed) should be (new GregorianCalendar(2013, 6, 31))
    Meetup(8, 2013).last(Meetup.Wed) should be (new GregorianCalendar(2013, 7, 28))
    Meetup(9, 2013).last(Meetup.Thu) should be (new GregorianCalendar(2013, 8, 26))
    Meetup(10, 2013).last(Meetup.Thu) should be (new GregorianCalendar(2013, 9, 31))
    Meetup(11, 2013).last(Meetup.Fri) should be (new GregorianCalendar(2013, 10, 29))
    Meetup(12, 2013).last(Meetup.Fri) should be (new GregorianCalendar(2013, 11, 27))
    Meetup(1, 2013).last(Meetup.Sat) should be (new GregorianCalendar(2013, 0, 26))
    Meetup(2, 2013).last(Meetup.Sat) should be (new GregorianCalendar(2013, 1, 23))
    Meetup(3, 2013).last(Meetup.Sun) should be (new GregorianCalendar(2013, 2, 31))
    Meetup(4, 2013).last(Meetup.Sun) should be (new GregorianCalendar(2013, 3, 28))
  }
}
