import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

object Gigasecond {
  private type Seconds = Long

  def addGigaseconds(startDate: LocalDate): LocalDateTime = {
    val startDateTime = LocalDateTime.of(startDate, LocalTime.of(0, 0))
    addGigaseconds(startDateTime)
  }

  def addGigaseconds(startDateTime: LocalDateTime): LocalDateTime =
    startDateTime.plusSeconds(OneGigasecond)

  private val OneGigasecond: Seconds = math.pow(10, 9) toLong
}
