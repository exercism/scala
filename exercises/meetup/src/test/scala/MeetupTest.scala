import java.time.LocalDate
import org.scalatest._

class MeetupTest extends FunSuite with Matchers {
  //Note: Java uses 0-indexed months for GregorianCalendar
  test("monteenth of may 2013") {
    Meetup(5, 2013).day(Meetup.Mon, Schedule.Teenth) should be(
      LocalDate.of(2013, 5, 13))
  }

  test("monteenth of august 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Mon, Schedule.Teenth) should be(
      LocalDate.of(2013, 8, 19))
  }

  test("monteenth of september 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Mon, Schedule.Teenth) should be(
      LocalDate.of(2013, 9, 16))
  }

  test("tuesteenth of march 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Tue, Schedule.Teenth) should be(
      LocalDate.of(2013, 3, 19))
  }

  test("tuesteenth of april 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Tue, Schedule.Teenth) should be(
      LocalDate.of(2013, 4, 16))
  }

  test("tuesteenth of august 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Tue, Schedule.Teenth) should be(
      LocalDate.of(2013, 8, 13))
  }

  test("wednesteenth of january 2013") {
    pending
    Meetup(1, 2013).day(Meetup.Wed, Schedule.Teenth) should be(
      LocalDate.of(2013, 1, 16))
  }

  test("wednesteenth of february 2013") {
    pending
    Meetup(2, 2013).day(Meetup.Wed, Schedule.Teenth) should be(
      LocalDate.of(2013, 2, 13))
  }

  test("wednesteenth of june 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Wed, Schedule.Teenth) should be(
      LocalDate.of(2013, 6, 19))
  }

  test("thursteenth of may 2013") {
    pending
    Meetup(5, 2013).day(Meetup.Thu, Schedule.Teenth) should be(
      LocalDate.of(2013, 5, 16))
  }

  test("thursteenth of june 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Thu, Schedule.Teenth) should be(
      LocalDate.of(2013, 6, 13))
  }

  test("thursteenth of september 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Teenth) should be(
      LocalDate.of(2013, 9, 19))
  }

  test("friteenth of april 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Fri, Schedule.Teenth) should be(
      LocalDate.of(2013, 4, 19))
  }

  test("friteenth of august 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Fri, Schedule.Teenth) should be(
      LocalDate.of(2013, 8, 16))
  }

  test("friteenth of september 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Fri, Schedule.Teenth) should be(
      LocalDate.of(2013, 9, 13))
  }

  test("saturteenth of february 2013") {
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Teenth) should be(
      LocalDate.of(2013, 2, 16))
  }

  test("saturteenth of april 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Sat, Schedule.Teenth) should be(
      LocalDate.of(2013, 4, 13))
  }

  test("saturteenth of october 2013") {
    pending
    Meetup(10, 2013).day(Meetup.Sat, Schedule.Teenth) should be(
      LocalDate.of(2013, 10, 19))
  }

  test("sunteenth of may 2013") {
    pending
    Meetup(5, 2013).day(Meetup.Sun, Schedule.Teenth) should be(
      LocalDate.of(2013, 5, 19))
  }

  test("sunteenth of june 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Sun, Schedule.Teenth) should be(
      LocalDate.of(2013, 6, 16))
  }

  test("sunteenth of october 2013") {
    pending
    Meetup(10, 2013).day(Meetup.Sun, Schedule.Teenth) should be(
      LocalDate.of(2013, 10, 13))
  }

  test("first monday of march 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.First) should be(
      LocalDate.of(2013, 3, 4))
  }

  test("first monday of april 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.First) should be(
      LocalDate.of(2013, 4, 1))
  }

  test("first tuesday of may 2013") {
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.First) should be(
      LocalDate.of(2013, 5, 7))
  }

  test("first tuesday of june 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.First) should be(
      LocalDate.of(2013, 6, 4))
  }

  test("first wednesday of july 2013") {
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.First) should be(
      LocalDate.of(2013, 7, 3))
  }

  test("first wednesday of august 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.First) should be(
      LocalDate.of(2013, 8, 7))
  }

  test("first thursday of september 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.First) should be(
      LocalDate.of(2013, 9, 5))
  }

  test("first thursday of october 2013") {
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.First) should be(
      LocalDate.of(2013, 10, 3))
  }

  test("first friday of november 2013") {
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.First) should be(
      LocalDate.of(2013, 11, 1))
  }

  test("first friday of december 2013") {
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.First) should be(
      LocalDate.of(2013, 12, 6))
  }

  test("first saturday of january 2013") {
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.First) should be(
      LocalDate.of(2013, 1, 5))
  }

  test("first saturday of february 2013") {
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.First) should be(
      LocalDate.of(2013, 2, 2))
  }

  test("first sunday of march 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.First) should be(
      LocalDate.of(2013, 3, 3))
  }

  test("first sunday of april 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.First) should be(
      LocalDate.of(2013, 4, 7))
  }

  test("second monday of march 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.Second) should be(
      LocalDate.of(2013, 3, 11))
  }

  test("second monday of april 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.Second) should be(
      LocalDate.of(2013, 4, 8))
  }

  test("second tuesday of may 2013") {
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.Second) should be(
      LocalDate.of(2013, 5, 14))
  }

  test("second tuesday of june 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.Second) should be(
      LocalDate.of(2013, 6, 11))
  }

  test("second wednesday of july 2013") {
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.Second) should be(
      LocalDate.of(2013, 7, 10))
  }

  test("second wednesday of august 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.Second) should be(
      LocalDate.of(2013, 8, 14))
  }

  test("second thursday of september 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Second) should be(
      LocalDate.of(2013, 9, 12))
  }

  test("second thursday of october 2013") {
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.Second) should be(
      LocalDate.of(2013, 10, 10))
  }

  test("second friday of november 2013") {
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.Second) should be(
      LocalDate.of(2013, 11, 8))
  }

  test("second friday of december 2013") {
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.Second) should be(
      LocalDate.of(2013, 12, 13))
  }

  test("second saturday of january 2013") {
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.Second) should be(
      LocalDate.of(2013, 1, 12))
  }

  test("second saturday of february 2013") {
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Second) should be(
      LocalDate.of(2013, 2, 9))
  }

  test("second sunday of march 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.Second) should be(
      LocalDate.of(2013, 3, 10))
  }

  test("second sunday of april 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.Second) should be(
      LocalDate.of(2013, 4, 14))
  }

  test("third monday of march 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.Third) should be(
      LocalDate.of(2013, 3, 18))
  }

  test("third monday of april 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.Third) should be(
      LocalDate.of(2013, 4, 15))
  }

  test("third tuesday of may 2013") {
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.Third) should be(
      LocalDate.of(2013, 5, 21))
  }

  test("third tuesday of june 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.Third) should be(
      LocalDate.of(2013, 6, 18))
  }

  test("third wednesday of july 2013") {
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.Third) should be(
      LocalDate.of(2013, 7, 17))
  }

  test("third wednesday of august 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.Third) should be(
      LocalDate.of(2013, 8, 21))
  }

  test("third thursday of september 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Third) should be(
      LocalDate.of(2013, 9, 19))
  }

  test("third thursday of october 2013") {
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.Third) should be(
      LocalDate.of(2013, 10, 17))
  }

  test("third friday of november 2013") {
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.Third) should be(
      LocalDate.of(2013, 11, 15))
  }

  test("third friday of december 2013") {
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.Third) should be(
      LocalDate.of(2013, 12, 20))
  }

  test("third saturday of january 2013") {
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.Third) should be(
      LocalDate.of(2013, 1, 19))
  }

  test("third saturday of february 2013") {
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Third) should be(
      LocalDate.of(2013, 2, 16))
  }

  test("third sunday of march 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.Third) should be(
      LocalDate.of(2013, 3, 17))
  }

  test("third sunday of april 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.Third) should be(
      LocalDate.of(2013, 4, 21))
  }

  test("fourth monday of march 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.Fourth) should be(
      LocalDate.of(2013, 3, 25))
  }

  test("fourth monday of april 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.Fourth) should be(
      LocalDate.of(2013, 4, 22))
  }

  test("fourth tuesday of may 2013") {
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.Fourth) should be(
      LocalDate.of(2013, 5, 28))
  }

  test("fourth tuesday of june 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.Fourth) should be(
      LocalDate.of(2013, 6, 25))
  }

  test("fourth wednesday of july 2013") {
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.Fourth) should be(
      LocalDate.of(2013, 7, 24))
  }

  test("fourth wednesday of august 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.Fourth) should be(
      LocalDate.of(2013, 8, 28))
  }

  test("fourth thursday of september 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Fourth) should be(
      LocalDate.of(2013, 9, 26))
  }

  test("fourth thursday of october 2013") {
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.Fourth) should be(
      LocalDate.of(2013, 10, 24))
  }

  test("fourth friday of november 2013") {
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.Fourth) should be(
      LocalDate.of(2013, 11, 22))
  }

  test("fourth friday of december 2013") {
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.Fourth) should be(
      LocalDate.of(2013, 12, 27))
  }

  test("fourth saturday of january 2013") {
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.Fourth) should be(
      LocalDate.of(2013, 1, 26))
  }

  test("fourth saturday of february 2013") {
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Fourth) should be(
      LocalDate.of(2013, 2, 23))
  }

  test("fourth sunday of march 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.Fourth) should be(
      LocalDate.of(2013, 3, 24))
  }

  test("fourth sunday of april 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.Fourth) should be(
      LocalDate.of(2013, 4, 28))
  }

  test("last monday of march 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Mon, Schedule.Last) should be(
      LocalDate.of(2013, 3, 25))
  }

  test("last monday of april 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Mon, Schedule.Last) should be(
      LocalDate.of(2013, 4, 29))
  }

  test("last tuesday of may 2013") {
    pending
    Meetup(5, 2013).day(Meetup.Tue, Schedule.Last) should be(
      LocalDate.of(2013, 5, 28))
  }

  test("last tuesday of june 2013") {
    pending
    Meetup(6, 2013).day(Meetup.Tue, Schedule.Last) should be(
      LocalDate.of(2013, 6, 25))
  }

  test("last wednesday of july 2013") {
    pending
    Meetup(7, 2013).day(Meetup.Wed, Schedule.Last) should be(
      LocalDate.of(2013, 7, 31))
  }

  test("last wednesday of august 2013") {
    pending
    Meetup(8, 2013).day(Meetup.Wed, Schedule.Last) should be(
      LocalDate.of(2013, 8, 28))
  }

  test("last thursday of september 2013") {
    pending
    Meetup(9, 2013).day(Meetup.Thu, Schedule.Last) should be(
      LocalDate.of(2013, 9, 26))
  }

  test("last thursday of october 2013") {
    pending
    Meetup(10, 2013).day(Meetup.Thu, Schedule.Last) should be(
      LocalDate.of(2013, 10, 31))
  }

  test("last friday of november 2013") {
    pending
    Meetup(11, 2013).day(Meetup.Fri, Schedule.Last) should be(
      LocalDate.of(2013, 11, 29))
  }

  test("last friday of december 2013") {
    pending
    Meetup(12, 2013).day(Meetup.Fri, Schedule.Last) should be(
      LocalDate.of(2013, 12, 27))
  }

  test("last saturday of january 2013") {
    pending
    Meetup(1, 2013).day(Meetup.Sat, Schedule.Last) should be(
      LocalDate.of(2013, 1, 26))
  }

  test("last saturday of february 2013") {
    pending
    Meetup(2, 2013).day(Meetup.Sat, Schedule.Last) should be(
      LocalDate.of(2013, 2, 23))
  }

  test("last sunday of march 2013") {
    pending
    Meetup(3, 2013).day(Meetup.Sun, Schedule.Last) should be(
      LocalDate.of(2013, 3, 31))
  }

  test("last sunday of april 2013") {
    pending
    Meetup(4, 2013).day(Meetup.Sun, Schedule.Last) should be(
      LocalDate.of(2013, 4, 28))
  }
}
