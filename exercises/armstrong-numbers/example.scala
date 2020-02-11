object ArmstrongNumbers {
  def isArmstrongNumber(i: Int): Boolean = {
    val s = i.toString
    val power = s.length
    i == s.toStream.foldLeft(0.0)((acc, c) => acc + math.pow(c.asDigit, power))
  }
}
