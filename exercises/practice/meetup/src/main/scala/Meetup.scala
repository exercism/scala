import java.time.{DayOfWeek, LocalDate}

import Schedule.Schedule

case class Meetup(month: Int, year: Int) {

  def day(dayOfWeek: DayOfWeek, schedule: Schedule): LocalDate = ???
}

enum Schedule {
  case First, Second, Third, Fourth, Last, Teenth
}