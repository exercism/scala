# Partial application

```scala
object SpaceAge {
  private val EARTH_SECONDS = 31_557_600.0
  private def calculate(orbitPeriod: Double, seconds: Double): Double =
    seconds / EARTH_SECONDS / orbitPeriod

  val onEarth = calculate(1, _)
  val onMercury = calculate(0.2408467, _)
  val onVenus = calculate(0.61519726, _)
  val onMars = calculate(1.8808158, _)
  val onJupiter = calculate(11.862615, _)
  val onSaturn = calculate(29.447498, _)
  val onUranus = calculate(84.016846, _)
  val onNeptune = calculate(164.79132, _)
}
```

This approach starts be defining the number of earth seconds in a year.
Note the use of digit separators (`_`) makes long numbers more readable.

The `calculate()` method is defined as a regular function taking two parameters.

Following the `calculate()` method are bindings with the same names as the methods expected by the tests.
The bindings are set to the `calculate()` method [partially applied][partial-application], with the second argument omitted. 
The `calculate()` method called with its first argument returns a function which takes the argument for `seconds`.
So each binding is the `calculate()` method ready to accept its second argument.

The order of the parameters does not matter.
The following definitions, with the parameters reversed, would also work

```scala
private def calculate(seconds: Double, orbitPeriod: Double): Double =
    seconds / EARTH_SECONDS / orbitPeriod

  val onEarth: Double => Double = calculate(_, 1)
  val onMercury: Double => Double = calculate(_, 0.2408467)
```

When a test calls one of the bindings with the `seconds` value, it is calling the partially applied `calculate()` function that is expecting its second
argument.
The  partially applied function takes the `seconds` value and returns the result of the expression in the body of the  partially applied function.

[partial-application]: https://www.geeksforgeeks.org/scala-partially-applied-functions/
