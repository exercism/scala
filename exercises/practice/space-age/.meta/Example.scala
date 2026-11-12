object SpaceAge:
  private val EarthYearInSeconds = 31_557_600.0

  private def age(seconds: Double, orbitalPeriod: Double): Double =
    seconds / EarthYearInSeconds / orbitalPeriod

  def onEarth(seconds: Double): Double   = age(seconds, 1.0)
  def onMercury(seconds: Double): Double = age(seconds, 0.2408467)
  def onVenus(seconds: Double): Double   = age(seconds, 0.61519726)
  def onMars(seconds: Double): Double    = age(seconds, 1.8808158)
  def onJupiter(seconds: Double): Double = age(seconds, 11.862615)
  def onSaturn(seconds: Double): Double  = age(seconds, 29.447498)
  def onUranus(seconds: Double): Double  = age(seconds, 84.016846)
  def onNeptune(seconds: Double): Double = age(seconds, 164.79132)
