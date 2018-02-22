import java.time.LocalDate

import org.scalatest.{Matchers, FunSuite}

/** @version 1.1.0 */
class MeetupTest extends FunSuite with Matchers {

  test("monteenth of May 2013") {
    Meetup(5, 2013).day(Meetup.Mon, Schedule.Teenth) should be(
      LocalDate.of(2013, 5, 13))
  }

  test("monteenth of August 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Mon, Schedule.Teenth) should be(
      LocalDate.of(2013, 8, 19))
  }

  test("monteenth of September 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Mon, Schedule.Teenth) should be(
      LocalDate.of(2013, 9, 16))
  }

  test("tuesteenth of March 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Tue, Schedule.Teenth) should be(
      LocalDate.of(2013, 3, 19))
  }

  test("tuesteenth of April 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Tue, Schedule.Teenth) should be(
      LocalDate.of(2013, 4, 16))
  }

  test("tuesteenth of August 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Tue, Schedule.Teenth) should be(
      LocalDate.of(2013, 8, 13))
  }

  test("wednesteenth of January 2013") {
    pending
    Meetup(1, 2013).day(Meetup.Wed, Schedule.Teenth) should be(
      LocalDate.of(2013, 1, 16))
  }

  test("wednesteenth of February 2013") {
    pending
    Meetup(2, 2013).day(Meetup.Wed, Schedule.Teenth) should be(
      LocalDate.of(2013, 2, 13))
  }

  test("wednesteenth of June 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Wed, Schedule.Teenth) should be(
      LocalDate.of(2013, 6, 19))
  }

  test("thursteenth of May 2013") {
    pending
    Meetup(5, 2013).day(Meetup.Thu, Schedule.Teenth) should be(
      LocalDate.of(2013, 5, 16))
  }

  test("thursteenth of June 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Thu, Schedule.Teenth) should be(
      LocalDate.of(2013, 6, 13))
  }

  test("thursteenth of September 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Teenth) should be(
      LocalDate.of(2013, 9, 19))
  }

  test("friteenth of April 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Fri, Schedule.Teenth) should be(
      LocalDate.of(2013, 4, 19))
  }

  test("friteenth of August 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Fri, Schedule.Teenth) should be(
      LocalDate.of(2013, 8, 16))
  }

  test("friteenth of September 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Fri, Schedule.Teenth) should be(
      LocalDate.of(2013, 9, 13))
  }

  test("saturteenth of February 2013") {
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Teenth) should be(
      LocalDate.of(2013, 2, 16))
  }

  test("saturteenth of April 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Sat, Schedule.Teenth) should be(
      LocalDate.of(2013, 4, 13))
  }

  test("saturteenth of October 2013") {
    pending
    Meetup(10, 2013).day(Meetup.Sat, Schedule.Teenth) should be(
      LocalDate.of(2013, 10, 19))
  }

  test("sunteenth of May 2013") {
    pending
    Meetup(5, 2013).day(Meetup.Sun, Schedule.Teenth) should be(
      LocalDate.of(2013, 5, 19))
  }

  test("sunteenth of June 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Sun, Schedule.Teenth) should be(
      LocalDate.of(2013, 6, 16))
  }

  test("sunteenth of October 2013") {
    pending
    Meetup(10, 2013).day(Meetup.Sun, Schedule.Teenth) should be(
      LocalDate.of(2013, 10, 13))
  }

  test("first Monday of March 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.First) should be(
      LocalDate.of(2013, 3, 4))
  }

  test("first Monday of April 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.First) should be(
      LocalDate.of(2013, 4, 1))
  }

  test("first Tuesday of May 2013") {
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.First) should be(
      LocalDate.of(2013, 5, 7))
  }

  test("first Tuesday of June 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.First) should be(
      LocalDate.of(2013, 6, 4))
  }

  test("first Wednesday of July 2013") {
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.First) should be(
      LocalDate.of(2013, 7, 3))
  }

  test("first Wednesday of August 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.First) should be(
      LocalDate.of(2013, 8, 7))
  }

  test("first Thursday of September 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.First) should be(
      LocalDate.of(2013, 9, 5))
  }

  test("first Thursday of October 2013") {
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.First) should be(
      LocalDate.of(2013, 10, 3))
  }

  test("first Friday of November 2013") {
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.First) should be(
      LocalDate.of(2013, 11, 1))
  }

  test("first Friday of December 2013") {
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.First) should be(
      LocalDate.of(2013, 12, 6))
  }

  test("first Saturday of January 2013") {
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.First) should be(
      LocalDate.of(2013, 1, 5))
  }

  test("first Saturday of February 2013") {
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.First) should be(
      LocalDate.of(2013, 2, 2))
  }

  test("first Sunday of March 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.First) should be(
      LocalDate.of(2013, 3, 3))
  }

  test("first Sunday of April 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.First) should be(
      LocalDate.of(2013, 4, 7))
  }

  test("second Monday of March 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.Second) should be(
      LocalDate.of(2013, 3, 11))
  }

  test("second Monday of April 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.Second) should be(
      LocalDate.of(2013, 4, 8))
  }

  test("second Tuesday of May 2013") {
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.Second) should be(
      LocalDate.of(2013, 5, 14))
  }

  test("second Tuesday of June 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.Second) should be(
      LocalDate.of(2013, 6, 11))
  }

  test("second Wednesday of July 2013") {
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.Second) should be(
      LocalDate.of(2013, 7, 10))
  }

  test("second Wednesday of August 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.Second) should be(
      LocalDate.of(2013, 8, 14))
  }

  test("second Thursday of September 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Second) should be(
      LocalDate.of(2013, 9, 12))
  }

  test("second Thursday of October 2013") {
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.Second) should be(
      LocalDate.of(2013, 10, 10))
  }

  test("second Friday of November 2013") {
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.Second) should be(
      LocalDate.of(2013, 11, 8))
  }

  test("second Friday of December 2013") {
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.Second) should be(
      LocalDate.of(2013, 12, 13))
  }

  test("second Saturday of January 2013") {
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.Second) should be(
      LocalDate.of(2013, 1, 12))
  }

  test("second Saturday of February 2013") {
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Second) should be(
      LocalDate.of(2013, 2, 9))
  }

  test("second Sunday of March 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.Second) should be(
      LocalDate.of(2013, 3, 10))
  }

  test("second Sunday of April 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.Second) should be(
      LocalDate.of(2013, 4, 14))
  }

  test("third Monday of March 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.Third) should be(
      LocalDate.of(2013, 3, 18))
  }

  test("third Monday of April 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.Third) should be(
      LocalDate.of(2013, 4, 15))
  }

  test("third Tuesday of May 2013") {
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.Third) should be(
      LocalDate.of(2013, 5, 21))
  }

  test("third Tuesday of June 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.Third) should be(
      LocalDate.of(2013, 6, 18))
  }

  test("third Wednesday of July 2013") {
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.Third) should be(
      LocalDate.of(2013, 7, 17))
  }

  test("third Wednesday of August 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.Third) should be(
      LocalDate.of(2013, 8, 21))
  }

  test("third Thursday of September 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Third) should be(
      LocalDate.of(2013, 9, 19))
  }

  test("third Thursday of October 2013") {
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.Third) should be(
      LocalDate.of(2013, 10, 17))
  }

  test("third Friday of November 2013") {
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.Third) should be(
      LocalDate.of(2013, 11, 15))
  }

  test("third Friday of December 2013") {
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.Third) should be(
      LocalDate.of(2013, 12, 20))
  }

  test("third Saturday of January 2013") {
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.Third) should be(
      LocalDate.of(2013, 1, 19))
  }

  test("third Saturday of February 2013") {
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Third) should be(
      LocalDate.of(2013, 2, 16))
  }

  test("third Sunday of March 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.Third) should be(
      LocalDate.of(2013, 3, 17))
  }

  test("third Sunday of April 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.Third) should be(
      LocalDate.of(2013, 4, 21))
  }

  test("fourth Monday of March 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.Fourth) should be(
      LocalDate.of(2013, 3, 25))
  }

  test("fourth Monday of April 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.Fourth) should be(
      LocalDate.of(2013, 4, 22))
  }

  test("fourth Tuesday of May 2013") {
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.Fourth) should be(
      LocalDate.of(2013, 5, 28))
  }

  test("fourth Tuesday of June 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.Fourth) should be(
      LocalDate.of(2013, 6, 25))
  }

  test("fourth Wednesday of July 2013") {
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.Fourth) should be(
      LocalDate.of(2013, 7, 24))
  }

  test("fourth Wednesday of August 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.Fourth) should be(
      LocalDate.of(2013, 8, 28))
  }

  test("fourth Thursday of September 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Fourth) should be(
      LocalDate.of(2013, 9, 26))
  }

  test("fourth Thursday of October 2013") {
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.Fourth) should be(
      LocalDate.of(2013, 10, 24))
  }

  test("fourth Friday of November 2013") {
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.Fourth) should be(
      LocalDate.of(2013, 11, 22))
  }

  test("fourth Friday of December 2013") {
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.Fourth) should be(
      LocalDate.of(2013, 12, 27))
  }

  test("fourth Saturday of January 2013") {
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.Fourth) should be(
      LocalDate.of(2013, 1, 26))
  }

  test("fourth Saturday of February 2013") {
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Fourth) should be(
      LocalDate.of(2013, 2, 23))
  }

  test("fourth Sunday of March 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.Fourth) should be(
      LocalDate.of(2013, 3, 24))
  }

  test("fourth Sunday of April 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.Fourth) should be(
      LocalDate.of(2013, 4, 28))
  }

  test("last Monday of March 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.Last) should be(
      LocalDate.of(2013, 3, 25))
  }

  test("last Monday of April 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.Last) should be(
      LocalDate.of(2013, 4, 29))
  }

  test("last Tuesday of May 2013") {
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.Last) should be(
      LocalDate.of(2013, 5, 28))
  }

  test("last Tuesday of June 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.Last) should be(
      LocalDate.of(2013, 6, 25))
  }

  test("last Wednesday of July 2013") {
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.Last) should be(
      LocalDate.of(2013, 7, 31))
  }

  test("last Wednesday of August 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.Last) should be(
      LocalDate.of(2013, 8, 28))
  }

  test("last Thursday of September 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Last) should be(
      LocalDate.of(2013, 9, 26))
  }

  test("last Thursday of October 2013") {
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.Last) should be(
      LocalDate.of(2013, 10, 31))
  }

  test("last Friday of November 2013") {
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.Last) should be(
      LocalDate.of(2013, 11, 29))
  }

  test("last Friday of December 2013") {
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.Last) should be(
      LocalDate.of(2013, 12, 27))
  }

  test("last Saturday of January 2013") {
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.Last) should be(
      LocalDate.of(2013, 1, 26))
  }

  test("last Saturday of February 2013") {
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Last) should be(
      LocalDate.of(2013, 2, 23))
  }

  test("last Sunday of March 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.Last) should be(
      LocalDate.of(2013, 3, 31))
  }

  test("last Sunday of April 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.Last) should be(
      LocalDate.of(2013, 4, 28))
  }

  test("last Wednesday of February 2012") {
    pending
    Meetup(2, 2012).day(Meetup.Wed, Schedule.Last) should be(
      LocalDate.of(2012, 2, 29))
  }

  test("last Wednesday of December 2014") {
    pending
    Meetup(12, 2014).day(Meetup.Wed, Schedule.Last) should be(
      LocalDate.of(2014, 12, 31))
  }

  test("last Sunday of February 2015") {
    pending
    Meetup(2, 2015).day(Meetup.Sun, Schedule.Last) should be(
      LocalDate.of(2015, 2, 22))
  }

  test("first Friday of December 2012") {
    pending
    Meetup(12, 2012).day(Meetup.Fri, Schedule.First) should be(
      LocalDate.of(2012, 12, 7))
  }
}
