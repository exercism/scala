// Clock(8, 0) is eight o'clock; Clock(3) is three minutes, to add or subtract.
class Clock:
  def +(that: Clock): Clock = ???

  def -(that: Clock): Clock = ???

object Clock:
  def apply(hours: Int, minutes: Int): Clock = ???

  def apply(minutes: Int): Clock = ???
