import java.time.{DayOfWeek, LocalDate}

case class Meetup(month: Int, year: Int):
  private val firstDay = LocalDate.of(year, month, 1)

  def day(dayOfWeek: Int, schedule: Schedule): LocalDate =
    val first = firstDay.onOrAfter(dayOfWeek)
    schedule match
      case Schedule.First  => first
      case Schedule.Second => first.plusWeeks(1)
      case Schedule.Third  => first.plusWeeks(2)
      case Schedule.Fourth => first.plusWeeks(3)
      case Schedule.Teenth => firstDay.withDayOfMonth(13).onOrAfter(dayOfWeek)
      case Schedule.Last   => firstDay.plusMonths(1).minusWeeks(1).onOrAfter(dayOfWeek)

  extension (date: LocalDate)
    // The first date on or after this one that falls on the given day of the week.
    private def onOrAfter(dayOfWeek: Int): LocalDate =
      date.plusDays(Math.floorMod(dayOfWeek - date.getDayOfWeek.getValue, 7))

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
