# Currying

```scala
object SpaceAge {
  private val EARTH_SECONDS = 31_557_600.0
  private def calculate(orbitPeriod: Double) =
    (seconds: Double) => seconds / EARTH_SECONDS / orbitPeriod

  val onEarth = calculate(1.0)
  val onMercury = calculate(0.2408467)
  val onVenus = calculate(0.61519726)
  val onMars = calculate(1.8808158)
  val onJupiter = calculate(11.862615)
  val onSaturn = calculate(29.447498)
  val onUranus = calculate(84.016846)
  val onNeptune = calculate(164.79132)
}
```

This approach starts by defining the number of earth seconds in a year.
Note the use of digit separators (`_`) makes long numbers more readable.

The `calculate()` method is defined as a [curried][currying] function.
In this situation, the curried function is a function of two parameters defined as a function that takes the first parameter
and returns another function which takes the second parameter.

The method could also be defined as a binding like so:

```scala
private val calculate = (orbitPeriod: Double) =>
  (seconds: Double) => seconds / EARTH_SECONDS / orbitPeriod
```

The slight change in syntax is exchanging the `=` operator for a `=>` operator after the first parameter.

The method syntax could be read "The function named 'calculate' that takes the parameter 'orbitPeriod' has a body which is a function
that takes the parameter 'seconds' and returns seconds / EARTH_SECONDS / orbitPeriod."

The function literal syntax could be read "The binding named 'calculate' is a function which takes the parameter 'orbitPeriod'
and returns a function which takes the parameter 'seconds' and returns seconds / EARTH_SECONDS / orbitPeriod."

Following the `calculate()` method are bindings with the same names as the methods expected by the tests.
The bindings are set to the `calculate()` method with the first argument passed for the orbit period. 
The `calculate()` method called with its first argument returns a function which takes the argument for `seconds`.
So each binding is the `calculate()` method ready to accept its second argument.

When a test calls one of the bindings with the `seconds` value, it is calling the curried `calculate()` function that is expecting its second
argument.
The curried function takes the `seconds` value and returns the result of the expression in the body of the curried function.


[currying]: https://www.geeksforgeeks.org/currying-functions-in-scala-with-examples/
