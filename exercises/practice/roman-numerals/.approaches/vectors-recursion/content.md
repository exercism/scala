# Recursion with `Vector`s

```scala
import scala.annotation.tailrec

object RomanNumerals:

  private val ArabicNum = Vector(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
  private val RomanNum  = Vector("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")

  def roman(num: Int): String = romanRecur(num, 0, List.empty)

  @tailrec
  private def romanRecur(num: Int, idx: Int, digits: List[String]): String =
    (num, idx, digits) match
      case (_, 13, digits)                             => digits.reverse.mkString
      case (num, idx, digits) if num >= ArabicNum(idx) => romanRecur(num - ArabicNum(idx), idx, RomanNum(idx) :: digits)
      case (num, idx, digits)                          => romanRecur(num, idx + 1, digits)
```

This approach starts by defining two private [Vector][vector]s, one which contains the Arabic numbers and the other their corresponding
Roman numerals.

The `roman()` method returns the result of calling the recursive method, to which it passes the input Arabic number,
the value `0` for the index, and a new empty `List`.

The recursive method is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being performed on it.

The [`match`][match] is used to check the index.

The [pattern matching][pattern-matching] checks if the index has calculated up to `13`.
If so, the `List` is [reverse][reverse]d and converted to a `String` with [`mkString`][mkstring], which is returned from the recursive method.

Otherwise, the number is checked if it is greater than or equal to the Arabic number in the `Vector` at the index.
If so, the recursive method calls itself, passing in the number subtracted by the Arabic number, the index as is, and the `List` prepended by the
Roman numeral in the `Vector` at that index.

If the number is less than the Arabic number at the index, then the recursive method calls itself with the number as is, the index added to by `1`,
and the `List` as is.

Note that the operations on the values create new values instead of mutating them, thus supporting [immutability][immutability].

[vector]: https://www.scala-lang.org/api/current/scala/collection/immutable/Vector.html
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[list]: https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[reverse]: https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[mkstring]: https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/current/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
