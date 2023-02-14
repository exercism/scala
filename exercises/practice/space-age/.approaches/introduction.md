# Introduction

There are at least two general ways to solve Space Age.
One approach is to use currying to reduce boilerplate.
Another approach is to use [`applyDynamic()`][applydynamic] to keep the code [DRY][dry].

## General guidance

One concern is to keep all of the calculation in one place.
Another concern is to reduce the amount of duplication.
As always, a concern is to try to maintain [immutability][immutability].


## Approach: Currying

```scala
object SpaceAge {
  private val EARTH_SECONDS = 31_557_600.0
  private val calculate = (orbitPeriod: Double) =>
    (seconds: Double) => seconds / EARTH_SECONDS / orbitPeriod
    
  val onEarth: Double => Double = calculate(1)
  val onMercury: Double => Double = calculate(0.2408467)
  val onVenus: Double => Double = calculate(0.61519726)
  val onMars: Double => Double = calculate(1.8808158)
  val onJupiter: Double => Double = calculate(11.862615)
  val onSaturn: Double => Double = calculate(29.447498)
  val onUranus: Double => Double = calculate(84.016846)
  val onNeptune: Double => Double = calculate(164.79132)
}
```

For more information, check the [Currying approach][approach-currying].

## Approach: `applyDynamic()`

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
    // changes "onEarth" to "Earth"
    calculate(methodCall.substring(2), seconds)
}
```

For more information, check the [`applyDynamic()` approach][approach-currying].

## Which approach to use?

Since benchmarking is currently outside the scope of this document, the choice between the approaches can be made by perceived readability.
The currying approach may be considered the most "functional".

The `applyDynamic()` approach avoids defining a lot of methods, but for the same reason is not as type-safe.
Calling a method by the wrong name would not be a compile error.
A typo in the method name would not even be a runtime error, but an unexpected value would likely be returned.

[currying]: https://www.baeldung.com/scala/currying
[applydynamic]: https://www.scala-lang.org/api/2.13.3/scala/Dynamic.html
[dry]: https://en.wikipedia.org/wiki/Don%27t_repeat_yourself
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[approach-currying]: https://exercism.org/tracks/scala/exercises/space-age/approaches/currying
[approach-applydynamic]: https://exercism.org/tracks/scala/exercises/space-age/approaches/applydynamic
