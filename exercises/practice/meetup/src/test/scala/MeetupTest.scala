import java.time.LocalDate

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class MeetupTest extends AnyFunSuite with Matchers:

  test("when teenth Monday is the 13th, the first day of the teenth week"):
    Meetup(5, 2013).day(Meetup.Mon, Schedule.Teenth) should be(LocalDate.of(2013, 5, 13))

  test("when teenth Monday is the 19th, the last day of the teenth week"):
    pending
    Meetup(8, 2013).day(Meetup.Mon, Schedule.Teenth) should be(LocalDate.of(2013, 8, 19))

  test("when teenth Monday is some day in the middle of the teenth week"):
    pending
    Meetup(9, 2013).day(Meetup.Mon, Schedule.Teenth) should be(LocalDate.of(2013, 9, 16))

  test("when teenth Tuesday is the 19th, the last day of the teenth week"):
    pending
    Meetup(3, 2013).day(Meetup.Tue, Schedule.Teenth) should be(LocalDate.of(2013, 3, 19))

  test("when teenth Tuesday is some day in the middle of the teenth week"):
    pending
    Meetup(4, 2013).day(Meetup.Tue, Schedule.Teenth) should be(LocalDate.of(2013, 4, 16))

  test("when teenth Tuesday is the 13th, the first day of the teenth week"):
    pending
    Meetup(8, 2013).day(Meetup.Tue, Schedule.Teenth) should be(LocalDate.of(2013, 8, 13))

  test("when teenth Wednesday is some day in the middle of the teenth week"):
    pending
    Meetup(1, 2013).day(Meetup.Wed, Schedule.Teenth) should be(LocalDate.of(2013, 1, 16))

  test("when teenth Wednesday is the 13th, the first day of the teenth week"):
    pending
    Meetup(2, 2013).day(Meetup.Wed, Schedule.Teenth) should be(LocalDate.of(2013, 2, 13))

  test("when teenth Wednesday is the 19th, the last day of the teenth week"):
    pending
    Meetup(6, 2013).day(Meetup.Wed, Schedule.Teenth) should be(LocalDate.of(2013, 6, 19))

  test("when teenth Thursday is some day in the middle of the teenth week"):
    pending
    Meetup(5, 2013).day(Meetup.Thu, Schedule.Teenth) should be(LocalDate.of(2013, 5, 16))

  test("when teenth Thursday is the 13th, the first day of the teenth week"):
    pending
    Meetup(6, 2013).day(Meetup.Thu, Schedule.Teenth) should be(LocalDate.of(2013, 6, 13))

  test("when teenth Thursday is the 19th, the last day of the teenth week"):
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Teenth) should be(LocalDate.of(2013, 9, 19))

  test("when teenth Friday is the 19th, the last day of the teenth week"):
    pending
    Meetup(4, 2013).day(Meetup.Fri, Schedule.Teenth) should be(LocalDate.of(2013, 4, 19))

  test("when teenth Friday is some day in the middle of the teenth week"):
    pending
    Meetup(8, 2013).day(Meetup.Fri, Schedule.Teenth) should be(LocalDate.of(2013, 8, 16))

  test("when teenth Friday is the 13th, the first day of the teenth week"):
    pending
    Meetup(9, 2013).day(Meetup.Fri, Schedule.Teenth) should be(LocalDate.of(2013, 9, 13))

  test("when teenth Saturday is some day in the middle of the teenth week"):
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Teenth) should be(LocalDate.of(2013, 2, 16))

  test("when teenth Saturday is the 13th, the first day of the teenth week"):
    pending
    Meetup(4, 2013).day(Meetup.Sat, Schedule.Teenth) should be(LocalDate.of(2013, 4, 13))

  test("when teenth Saturday is the 19th, the last day of the teenth week"):
    pending
    Meetup(10, 2013).day(Meetup.Sat, Schedule.Teenth) should be(LocalDate.of(2013, 10, 19))

  test("when teenth Sunday is the 19th, the last day of the teenth week"):
    pending
    Meetup(5, 2013).day(Meetup.Sun, Schedule.Teenth) should be(LocalDate.of(2013, 5, 19))

  test("when teenth Sunday is some day in the middle of the teenth week"):
    pending
    Meetup(6, 2013).day(Meetup.Sun, Schedule.Teenth) should be(LocalDate.of(2013, 6, 16))

  test("when teenth Sunday is the 13th, the first day of the teenth week"):
    pending
    Meetup(10, 2013).day(Meetup.Sun, Schedule.Teenth) should be(LocalDate.of(2013, 10, 13))

  test("when first Monday is some day in the middle of the first week"):
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.First) should be(LocalDate.of(2013, 3, 4))

  test("when first Monday is the 1st, the first day of the first week"):
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.First) should be(LocalDate.of(2013, 4, 1))

  test("when first Tuesday is the 7th, the last day of the first week"):
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.First) should be(LocalDate.of(2013, 5, 7))

  test("when first Tuesday is some day in the middle of the first week"):
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.First) should be(LocalDate.of(2013, 6, 4))

  test("when first Wednesday is some day in the middle of the first week"):
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.First) should be(LocalDate.of(2013, 7, 3))

  test("when first Wednesday is the 7th, the last day of the first week"):
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.First) should be(LocalDate.of(2013, 8, 7))

  test("when first Thursday is some day in the middle of the first week"):
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.First) should be(LocalDate.of(2013, 9, 5))

  test("when first Thursday is another day in the middle of the first week"):
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.First) should be(LocalDate.of(2013, 10, 3))

  test("when first Friday is the 1st, the first day of the first week"):
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.First) should be(LocalDate.of(2013, 11, 1))

  test("when first Friday is some day in the middle of the first week"):
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.First) should be(LocalDate.of(2013, 12, 6))

  test("when first Saturday is some day in the middle of the first week"):
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.First) should be(LocalDate.of(2013, 1, 5))

  test("when first Saturday is another day in the middle of the first week"):
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.First) should be(LocalDate.of(2013, 2, 2))

  test("when first Sunday is some day in the middle of the first week"):
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.First) should be(LocalDate.of(2013, 3, 3))

  test("when first Sunday is the 7th, the last day of the first week"):
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.First) should be(LocalDate.of(2013, 4, 7))

  test("when second Monday is some day in the middle of the second week"):
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.Second) should be(LocalDate.of(2013, 3, 11))

  test("when second Monday is the 8th, the first day of the second week"):
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.Second) should be(LocalDate.of(2013, 4, 8))

  test("when second Tuesday is the 14th, the last day of the second week"):
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.Second) should be(LocalDate.of(2013, 5, 14))

  test("when second Tuesday is some day in the middle of the second week"):
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.Second) should be(LocalDate.of(2013, 6, 11))

  test("when second Wednesday is some day in the middle of the second week"):
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.Second) should be(LocalDate.of(2013, 7, 10))

  test("when second Wednesday is the 14th, the last day of the second week"):
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.Second) should be(LocalDate.of(2013, 8, 14))

  test("when second Thursday is some day in the middle of the second week"):
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Second) should be(LocalDate.of(2013, 9, 12))

  test("when second Thursday is another day in the middle of the second week"):
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.Second) should be(LocalDate.of(2013, 10, 10))

  test("when second Friday is the 8th, the first day of the second week"):
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.Second) should be(LocalDate.of(2013, 11, 8))

  test("when second Friday is some day in the middle of the second week"):
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.Second) should be(LocalDate.of(2013, 12, 13))

  test("when second Saturday is some day in the middle of the second week"):
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.Second) should be(LocalDate.of(2013, 1, 12))

  test("when second Saturday is another day in the middle of the second week"):
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Second) should be(LocalDate.of(2013, 2, 9))

  test("when second Sunday is some day in the middle of the second week"):
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.Second) should be(LocalDate.of(2013, 3, 10))

  test("when second Sunday is the 14th, the last day of the second week"):
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.Second) should be(LocalDate.of(2013, 4, 14))

  test("when third Monday is some day in the middle of the third week"):
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.Third) should be(LocalDate.of(2013, 3, 18))

  test("when third Monday is the 15th, the first day of the third week"):
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.Third) should be(LocalDate.of(2013, 4, 15))

  test("when third Tuesday is the 21st, the last day of the third week"):
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.Third) should be(LocalDate.of(2013, 5, 21))

  test("when third Tuesday is some day in the middle of the third week"):
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.Third) should be(LocalDate.of(2013, 6, 18))

  test("when third Wednesday is some day in the middle of the third week"):
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.Third) should be(LocalDate.of(2013, 7, 17))

  test("when third Wednesday is the 21st, the last day of the third week"):
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.Third) should be(LocalDate.of(2013, 8, 21))

  test("when third Thursday is some day in the middle of the third week"):
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Third) should be(LocalDate.of(2013, 9, 19))

  test("when third Thursday is another day in the middle of the third week"):
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.Third) should be(LocalDate.of(2013, 10, 17))

  test("when third Friday is the 15th, the first day of the third week"):
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.Third) should be(LocalDate.of(2013, 11, 15))

  test("when third Friday is some day in the middle of the third week"):
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.Third) should be(LocalDate.of(2013, 12, 20))

  test("when third Saturday is some day in the middle of the third week"):
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.Third) should be(LocalDate.of(2013, 1, 19))

  test("when third Saturday is another day in the middle of the third week"):
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Third) should be(LocalDate.of(2013, 2, 16))

  test("when third Sunday is some day in the middle of the third week"):
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.Third) should be(LocalDate.of(2013, 3, 17))

  test("when third Sunday is the 21st, the last day of the third week"):
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.Third) should be(LocalDate.of(2013, 4, 21))

  test("when fourth Monday is some day in the middle of the fourth week"):
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.Fourth) should be(LocalDate.of(2013, 3, 25))

  test("when fourth Monday is the 22nd, the first day of the fourth week"):
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.Fourth) should be(LocalDate.of(2013, 4, 22))

  test("when fourth Tuesday is the 28th, the last day of the fourth week"):
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.Fourth) should be(LocalDate.of(2013, 5, 28))

  test("when fourth Tuesday is some day in the middle of the fourth week"):
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.Fourth) should be(LocalDate.of(2013, 6, 25))

  test("when fourth Wednesday is some day in the middle of the fourth week"):
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.Fourth) should be(LocalDate.of(2013, 7, 24))

  test("when fourth Wednesday is the 28th, the last day of the fourth week"):
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.Fourth) should be(LocalDate.of(2013, 8, 28))

  test("when fourth Thursday is some day in the middle of the fourth week"):
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Fourth) should be(LocalDate.of(2013, 9, 26))

  test("when fourth Thursday is another day in the middle of the fourth week"):
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.Fourth) should be(LocalDate.of(2013, 10, 24))

  test("when fourth Friday is the 22nd, the first day of the fourth week"):
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.Fourth) should be(LocalDate.of(2013, 11, 22))

  test("when fourth Friday is some day in the middle of the fourth week"):
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.Fourth) should be(LocalDate.of(2013, 12, 27))

  test("when fourth Saturday is some day in the middle of the fourth week"):
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.Fourth) should be(LocalDate.of(2013, 1, 26))

  test("when fourth Saturday is another day in the middle of the fourth week"):
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Fourth) should be(LocalDate.of(2013, 2, 23))

  test("when fourth Sunday is some day in the middle of the fourth week"):
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.Fourth) should be(LocalDate.of(2013, 3, 24))

  test("when fourth Sunday is the 28th, the last day of the fourth week"):
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.Fourth) should be(LocalDate.of(2013, 4, 28))

  test("last Monday in a month with four Mondays"):
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.Last) should be(LocalDate.of(2013, 3, 25))

  test("last Monday in a month with five Mondays"):
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.Last) should be(LocalDate.of(2013, 4, 29))

  test("last Tuesday in a month with four Tuesdays"):
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.Last) should be(LocalDate.of(2013, 5, 28))

  test("last Tuesday in another month with four Tuesdays"):
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.Last) should be(LocalDate.of(2013, 6, 25))

  test("last Wednesday in a month with five Wednesdays"):
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.Last) should be(LocalDate.of(2013, 7, 31))

  test("last Wednesday in a month with four Wednesdays"):
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.Last) should be(LocalDate.of(2013, 8, 28))

  test("last Thursday in a month with four Thursdays"):
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Last) should be(LocalDate.of(2013, 9, 26))

  test("last Thursday in a month with five Thursdays"):
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.Last) should be(LocalDate.of(2013, 10, 31))

  test("last Friday in a month with five Fridays"):
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.Last) should be(LocalDate.of(2013, 11, 29))

  test("last Friday in a month with four Fridays"):
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.Last) should be(LocalDate.of(2013, 12, 27))

  test("last Saturday in a month with four Saturdays"):
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.Last) should be(LocalDate.of(2013, 1, 26))

  test("last Saturday in another month with four Saturdays"):
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Last) should be(LocalDate.of(2013, 2, 23))

  test("last Sunday in a month with five Sundays"):
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.Last) should be(LocalDate.of(2013, 3, 31))

  test("last Sunday in a month with four Sundays"):
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.Last) should be(LocalDate.of(2013, 4, 28))

  test("when last Wednesday in February in a leap year is the 29th"):
    pending
    Meetup(2, 2012).day(Meetup.Wed, Schedule.Last) should be(LocalDate.of(2012, 2, 29))

  test("last Wednesday in December that is also the last day of the year"):
    pending
    Meetup(12, 2014).day(Meetup.Wed, Schedule.Last) should be(LocalDate.of(2014, 12, 31))

  test("when last Sunday in February in a non-leap year is not the 29th"):
    pending
    Meetup(2, 2015).day(Meetup.Sun, Schedule.Last) should be(LocalDate.of(2015, 2, 22))

  test("when first Friday is the 7th, the last day of the first week"):
    pending
    Meetup(12, 2012).day(Meetup.Fri, Schedule.First) should be(LocalDate.of(2012, 12, 7))
