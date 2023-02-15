# Partial application

```scala
object SpaceAge {
  private val EARTH_SECONDS = 31_557_600.0
  private def calculate(orbitPeriod: Double, seconds: Double): Double =
    seconds / EARTH_SECONDS / orbitPeriod

  val onEarth = calculate(_, 1)
  val onMercury = calculate(_, 0.2408467)
  val onVenus = calculate(_, 0.61519726)
  val onMars = calculate(_, 1.8808158)
  val onJupiter = calculate(_, 11.862615)
  val onSaturn = calculate(_, 29.447498)
  val onUranus = calculate(_, 84.016846)
  val onNeptune = calculate(_, 164.79132)}
```


```scala
private def calculate(seconds: Double, orbitPeriod: Double): Double =
    seconds / EARTH_SECONDS / orbitPeriod

  val onEarth: Double => Double = calculate(_, 1)
  val onMercury: Double => Double = calculate(_, 0.2408467)
```
