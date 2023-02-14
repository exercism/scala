# Partial application

```scala
object SpaceAge {
  private val EARTH_SECONDS = 31_557_600.0
  private def calculate(orbitPeriod: Double, seconds: Double): Double =
    seconds / EARTH_SECONDS / orbitPeriod

  val onEarth: Double => Double = calculate(1.0, _)
  val onMercury: Double => Double = calculate(0.2408467, _)
  val onVenus: Double => Double = calculate(0.61519726, _)
  val onMars: Double => Double = calculate(1.8808158, _)
  val onJupiter: Double => Double = calculate(11.862615, _)
  val onSaturn: Double => Double = calculate(29.447498, _)
  val onUranus: Double => Double = calculate(84.016846, _)
  val onNeptune: Double => Double = calculate(164.79132, _)
}
```


```scala
private def calculate(seconds: Double, orbitPeriod: Double): Double =
    seconds / EARTH_SECONDS / orbitPeriod

  val onEarth: Double => Double = calculate(_, 1)
  val onMercury: Double => Double = calculate(_, 0.2408467)
```
