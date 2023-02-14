# Currying

```scala
object SpaceAge {
  private val EARTH_SECONDS = 31_557_600.0
  private def calculate(orbitPeriod: Double) =
    (seconds: Double) => seconds / EARTH_SECONDS / orbitPeriod

  val onEarth: Double => Double = calculate(1.0)
  val onMercury: Double => Double = calculate(0.2408467)
  val onVenus: Double => Double = calculate(0.61519726)
  val onMars: Double => Double = calculate(1.8808158)
  val onJupiter: Double => Double = calculate(11.862615)
  val onSaturn: Double => Double = calculate(29.447498)
  val onUranus: Double => Double = calculate(84.016846)
  val onNeptune: Double => Double = calculate(164.79132)
}
```

```scala
private val calculate = (orbitPeriod: Double) =>
  (seconds: Double) => seconds / EARTH_SECONDS / orbitPeriod
```

