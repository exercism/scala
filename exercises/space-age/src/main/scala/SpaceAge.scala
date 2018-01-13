object SpaceAge {

  private val EarthYearInSeconds = 31557600.0
  private val MercuryYearInSeconds = EarthYearInSeconds * 0.2408467
  private val VenusYearInSeconds = EarthYearInSeconds * 0.61519726
  private val MarsYearInSeconds = EarthYearInSeconds * 1.8808158
  private val JupiterYearInSeconds = EarthYearInSeconds * 11.862615
  private val SaturnYearInSeconds = EarthYearInSeconds * 29.447498
  private val UranusYearInSeconds = EarthYearInSeconds * 84.016846
  private val NeptuneYearInSeconds = EarthYearInSeconds * 164.79132

  private def round(number: Double): Double = (number * 100).round / 100.0

  def onEarth(seconds: Double): Double = round(seconds / EarthYearInSeconds)
  def onMercury(seconds: Double): Double = round(seconds / MercuryYearInSeconds)
  def onVenus(seconds: Double): Double = round(seconds / VenusYearInSeconds)
  def onMars(seconds: Double): Double = round(seconds / MarsYearInSeconds)
  def onJupiter(seconds: Double): Double = round(seconds / JupiterYearInSeconds)
  def onSaturn(seconds: Double): Double = round(seconds / SaturnYearInSeconds)
  def onUranus(seconds: Double): Double = round(seconds / UranusYearInSeconds)
  def onNeptune(seconds: Double): Double = round(seconds / NeptuneYearInSeconds)
}
