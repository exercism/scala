import java.time.DayOfWeek
import java.time.LocalDate

case class Meetup(month: Int, year: Int) {
  private val thirteenth = LocalDate.of(year, month, 13)
  private val first = LocalDate.of(year, month, 1)
  private val nextMonth = first.plusMonths(1)

  def teenth(day: Int): LocalDate = thirteenth.next(day)
  def first(day: Int): LocalDate = first.next(day)
  def second(day: Int): LocalDate = first(day).plusDays(7)
  def third(day: Int): LocalDate = second(day).plusDays(7)
  def fourth(day: Int): LocalDate = third(day).plusDays(7)
  def last(day: Int): LocalDate = nextMonth.next(day).minusDays(7)

  implicit class LocalDateOps(self: LocalDate) {
    def next(dayOfWeek: Int): LocalDate = self.plusDays(daysUntil(dayOfWeek))

    def daysUntil(dayOfWeek: Int): Int = (Meetup.Sun - this.dayOfWeek + dayOfWeek) % 7

    def dayOfWeek: Int = self.getDayOfWeek.getValue
  }
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
