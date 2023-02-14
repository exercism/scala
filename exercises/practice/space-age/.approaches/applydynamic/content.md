# `applyDynamic()`

```scala
import scala.language.dynamics

object SpaceAge extends Dynamic {
  private val EarthSeconds = 31_557_600.0

  private val PlanetRatio: Map[String, Double] = Map(
    "Mercury" -> 0.2408467,
    "Venus" -> 0.61519726,
    "Earth" -> 1.0,
    "Mars" -> 1.8808158,
    "Jupiter" -> 11.862615,
    "Saturn" -> 29.447498,
    "Uranus" -> 84.016846,
    "Neptune" -> 164.79132
  )

  private def calculate(planet: String, seconds: Double): Double =
    seconds / EarthSeconds / PlanetRatio.getOrElse(planet, 1.0)

  def applyDynamic(methodCall: String)(seconds: Double): Double =
    calculate(methodCall.substring(2), seconds)
}
```

