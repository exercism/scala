import java.time.{DayOfWeek, LocalDate}

case class Meetup(month: Int, year: Int):
  def day(dayOfWeek: Int, schedule: Schedule): LocalDate = ???

enum Schedule:
  case Teenth, First, Second, Third, Fourth, Last

object Meetup:
  val Mon = DayOfWeek.MONDAY.getValue
  val Tue = DayOfWeek.TUESDAY.getValue
  val Wed = DayOfWeek.WEDNESDAY.getValue
  val Thu = DayOfWeek.THURSDAY.getValue
  val Fri = DayOfWeek.FRIDAY.getValue
  val Sat = DayOfWeek.SATURDAY.getValue
  val Sun = DayOfWeek.SUNDAY.getValue
