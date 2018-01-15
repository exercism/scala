import java.time.{LocalDate, LocalDateTime, LocalTime}

object Gigasecond {

  val gigasecond = 1000000000L

  def add(startDate: LocalDate): LocalDateTime = add(LocalDateTime.of(startDate, LocalTime.MIDNIGHT))

  def add(startDateTime: LocalDateTime): LocalDateTime = {
    startDateTime.plusSeconds(gigasecond)
  }
}
