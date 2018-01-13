object SpaceAge2 {
  private val daysToEarthAge = 365.25
  private val daysToMercuryAge = 87.969
  private val daysToVenusAge = 224.701
  private val daysToMarsAge = 686.971
  private val daysToJupiterAge = 4332.59
  private val daysToSaturnAge = 10759.22
  private val daysToUranusAge = 30799.095
  private val daysToNeptuneAge = 60190.03

  private def toEarthDays(seconds: Double): Double = {
    val minutes = seconds.toDouble / 60
    val hours = minutes / 60
    hours / 24
  }

  private def toPlanetAge(daysToPlanetAge: Double, seconds: Double): Double =
    round(toEarthDays(seconds) / daysToPlanetAge)

  private def round(value: Double): Double = (value * 100).round / 100.0

  def onEarth(seconds: Double): Double = toPlanetAge(daysToEarthAge, seconds)
  def onMercury(seconds: Double): Double = toPlanetAge(daysToMercuryAge, seconds)
  def onVenus(seconds: Double): Double = toPlanetAge(daysToVenusAge, seconds)
  def onMars(seconds: Double): Double = toPlanetAge(daysToMarsAge, seconds)
  def onJupiter(seconds: Double): Double = toPlanetAge(daysToJupiterAge, seconds)
  def onSaturn(seconds: Double): Double = toPlanetAge(daysToSaturnAge, seconds)
  def onUranus(seconds: Double): Double = toPlanetAge(daysToUranusAge, seconds)
  def onNeptune(seconds: Double): Double = toPlanetAge(daysToNeptuneAge, seconds)
}
