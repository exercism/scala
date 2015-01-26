import java.util.{Calendar, GregorianCalendar}

case class Gigasecond(initial: Calendar) {
  val date = {
    val copy = initial.clone.asInstanceOf[Calendar]
    copy.add(Calendar.SECOND, 1000000000)
    copy
  }
}
