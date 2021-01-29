case class Clock(minutes: Int) {
  private val normalized = {
    val tmp = minutes % (24 * 60)
    if (tmp >= 0) tmp else (24 * 60) + tmp
  }

  private val hour = normalized / 60
  private val min = normalized % 60

  def +(that: Clock) =
    Clock(normalized + that.normalized)

  def -(that: Clock) = {
    var mins = normalized - that.normalized
    while (mins < 0) mins += (24 * 60)
    Clock(mins)
  }

  override def toString: String = f"$hour%02d:$min%02d"

  override def equals(that: scala.Any): Boolean = {
    that match {
      case that: Clock => normalized == that.normalized
      case _ => false
    }
  }

  override def hashCode(): Int = 41 * minutes
}

object Clock {
  def apply(hour: Int, min: Int) = new Clock(hour * 60 + min)
}
