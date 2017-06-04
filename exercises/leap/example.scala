object Leap {
  def leapYear(year: Int): Boolean = {
    def divisibleBy(i: Int) = year % i == 0
    divisibleBy(4) && (divisibleBy(400) || !divisibleBy(100))
  }
}
