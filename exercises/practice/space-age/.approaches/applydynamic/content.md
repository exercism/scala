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

This approach starts by importing from packages for what is needed.

It then defines the `SpaceAge` object as [extended][extends] from the [`Dynamic`][applydynamic] [trait][trait].

Then comes the definition of the number of earth seconds in a year.
Note the use of digit separators (`_`) makes long numbers more readable.

A [`Map`][map] is defined that associates the name of each planet with its orbit ratio.

The `calculate()` method is defined as a regular function taking two parameters.

The `applyDynamic()` method is used to intercept a method call, such as `SpaceAge.onMercury(2134835688)`.
It takes a [`substring()`][substring] of the method name (e.g., to remove the "on" from "onMercury")
and passes it to the `calculate()` method along with its `seconds` argument.

Note that the `applyDynamic()` method is defined as a [curried][currying] function.
The `applyDynamic()` method takes the method name as its first parameter, with any other parameters
(including no parameters) defined separately after that.
Otherwise, the `applyDynamic()` method would have to support all possible parameters in its initial signature, which would
not be practical.

[extends]: https://www.geeksforgeeks.org/extending-a-class-in-scala/
[applydynamic]: https://www.scala-lang.org/api/2.13.3/scala/Dynamic.html
[trait]: https://docs.scala-lang.org/tour/traits.html
[map]: https://www.scala-lang.org/api/2.13.10/scala/collection/immutable/Map.html
[substring]: https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#substring(int)
[currying]: https://www.geeksforgeeks.org/currying-functions-in-scala-with-examples/
