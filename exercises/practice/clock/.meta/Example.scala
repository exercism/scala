// A clock keeps its time as minutes since midnight, so clocks showing the same time are equal.
case class Clock private (minutes: Int):
  def +(that: Clock): Clock = Clock(minutes + that.minutes)

  def -(that: Clock): Clock = Clock(minutes - that.minutes)

  override def toString: String = f"${minutes / 60}%02d:${minutes % 60}%02d"

object Clock:
  private val MinutesPerDay = 24 * 60

  def apply(minutes: Int): Clock = new Clock(Math.floorMod(minutes, MinutesPerDay))

  def apply(hours: Int, minutes: Int): Clock = Clock(hours * 60 + minutes)
