import java.time.LocalDate
import java.time.LocalDateTime

object Gigasecond:
  private val OneGigasecond = 1_000_000_000L

  def add(startDate: LocalDate): LocalDateTime =
    add(startDate.atStartOfDay)

  def add(startDateTime: LocalDateTime): LocalDateTime =
    startDateTime.plusSeconds(OneGigasecond)
