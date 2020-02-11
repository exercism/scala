import java.time.{DayOfWeek, LocalDate}

import Schedule.Schedule

case class Meetup(month: Int, year: Int) {
  private val thirteenth = LocalDate.of(year, month, 13)
  private val firstDay = LocalDate.of(year, month, 1)
  private val nextMonth = firstDay.plusMonths(1)

  private val teenth: Scheduler = (dayOfWeek: Int) => thirteenth.next(dayOfWeek)
  private val first: Scheduler = (dayOfWeek: Int) => firstDay.next(dayOfWeek)
  private val second: Scheduler = (dayOfWeek: Int) => first.day(dayOfWeek).plusDays(7)
  private val third: Scheduler = (dayOfWeek: Int) => second.day(dayOfWeek).plusDays(7)
  private val fourth: Scheduler = (dayOfWeek: Int) => third.day(dayOfWeek).plusDays(7)
  private val last: Scheduler = (dayOfWeek: Int) => nextMonth.next(dayOfWeek).minusDays(7)
  private def schedulers: Map[Schedule, Scheduler] = Map(Schedule.Teenth -> teenth,
    Schedule.First -> first,
    Schedule.Second -> second,
    Schedule.Third -> third,
    Schedule.Fourth -> fourth,
    Schedule.Last -> last)

  def day(dayOfWeek: Int, schedule: Schedule): LocalDate =
    schedulers(schedule).day(dayOfWeek)

  implicit class LocalDateOps(self: LocalDate) {
    def next(dayOfWeek: Int): LocalDate = self.plusDays(daysUntil(dayOfWeek))

    def daysUntil(dayOfWeek: Int): Int = (Meetup.Sun - this.dayOfWeek + dayOfWeek) % 7

    def dayOfWeek: Int = self.getDayOfWeek.getValue
  }

  trait Scheduler {
    def day(dayOfWeek: Int): LocalDate
  }
}

object Schedule extends Enumeration {
  type Schedule = Value
  val Teenth, First, Second, Third, Fourth, Last = Value
}

object Meetup {
  val Mon = DayOfWeek.MONDAY.getValue
  val Tue = DayOfWeek.TUESDAY.getValue
  val Wed = DayOfWeek.WEDNESDAY.getValue
  val Thu = DayOfWeek.THURSDAY.getValue
  val Fri = DayOfWeek.FRIDAY.getValue
  val Sat = DayOfWeek.SATURDAY.getValue
  val Sun = DayOfWeek.SUNDAY.getValue
}
