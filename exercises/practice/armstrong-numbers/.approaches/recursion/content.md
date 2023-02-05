# recursion

```scala
import scala.annotation.tailrec;

import math.{log10, pow}

object ArmstrongNumbers {

  def isArmstrongNumber(num: Int): Boolean =
    if (num < 10) true
    else {
      val len: Double = log10(num).toInt + 1

      @tailrec
      def recurMe(nbr: Int, total: Double): Boolean =
        nbr match {
          case 0 => total == num
          case n => recurMe(n / 10, total + pow(n % 10, len))
        }
        
      recurMe(num, 0.0)
    }

}
```

This approach starts by importing from packages for what is needed.

The `isArmstrongNumber()` method uses an `if` and `else` expression.
Given that the test input is never less than `1`, if the input `Int` is less than `10`, then `true` is returned.
Otherwise the result of the `else` expression is returned.

The `else` expression starts by using the [`log10()`][log10] method to calculate the number of digits in the input `Int`.

Then a [nested function][nested-function] (also called a [closure][closure]) is defined which takes two arguments.
The first argument is for the number that is being calculated.
The second number is for totaling the results of the calculations.

```exercism/note
Although the number of digits is not passed into the inner function, the inner function still has access to it.
It is said that the value for the number of digits is _captured_ by the inner function.
```

The [nested function][nested-function] is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the function can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same function _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the function is being called
with no other operation being peformed on it.

A [`match`][match] is used to do [pattern-matching] on the first argument number.
If the number has calculated down to `0`, then the nested function returns whether the total equals the original number.
Otherwise, the nested function calls itself with the recalculated number and the new total.
Note that no state is being modified, so the recursive approach supports [immutability][immutability].

Since `pow()` take two `Double` arguments, 
the length of the `String` was set to the `Double` type to keep from doing a widening conversion of `Int` to `Double` on every call
to `pow()`.

Finally, the inner function is called, passing in the original number and a `Double` value of `0.0` for the total.
The `isArmstrongNumber()` method returns the result of calling its recursive inner function.

[log10]: https://www.scala-lang.org/api/2.13.5/scala/math/index.html#log10(x:Double):Double
[nested-function]: https://www.geeksforgeeks.org/scala-nested-functions/
[closure]: https://alvinalexander.com/scala/how-to-use-closures-in-scala-fp-examples/
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/2.12.1/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
