# Recursion

```scala
object PhoneNumber {

  def clean(input: String): Option[String] = {
    cleanRecur(input.to(List), Nil)
  }

  @scala.annotation.tailrec
  private def cleanRecur(
      raw: List[Char],
      cleaned: List[Char]
  ): Option[String] = {
    (raw, cleaned.length) match {
      case (Nil, index) => {
        if (index == 10) return Some(cleaned.reverse.mkString) else None
      }
      case (head :: tail, index) if List(0, 3).contains(index) => {
        head.isDigit match {
          case true if (head > '1') => cleanRecur(tail, head :: cleaned)
          case true if (index == 0 && head == '1') => cleanRecur(tail, cleaned)
          case false if (index == 0 && List('+', '(', ' ').contains(head)) =>
            cleanRecur(tail, cleaned)
          case false if (index == 3 && List(')', ' ', '.').contains(head)) =>
            cleanRecur(tail, cleaned)
          case _ => None
        }
      }
      case (head :: tail, index) => {
        head.isDigit match {
          case true => cleanRecur(tail, head :: cleaned)
          case false if (index == 6 && List(' ', '-', '.').contains(head)) =>
            cleanRecur(tail, cleaned)
          case false if (head == ' ') => cleanRecur(tail, cleaned)
          case _                      => None
        }
      }
    }
  }
}
```

The `clean()` method returns the result of calling the recursive method, passing in the input `String` converted to a `List` and
an empty List for building the output `String`.

The recursive method is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being peformed on it.

In the recursive method, the input `List` and the length of the output `List` are matched.
The length of the output `List` is used for the `index` value.

If the input `List` is `Nil` (meaning it is empty), then the index is checked.
If the index is `10`, then the [`reverse`][reverse] and [`mkString`][mkstring] methods are used on the output `List` to return a `Some` value
from the recursive method.
If the index is not `10`, then `None` is returned from the recursive method.

Otherwise, the cons operator (`::`) is used to destructure the [`head`][head] and [`tail`][tail] from the input `List`, and
the index is checked for being `1` or `3`.
If so, and the `head` is a digit greater than `1`, then the method calls itself, passing in the `tail` of the input `List`
and the `head` prepended to the output `List` with the cons operator (`::`).
If the `head` is some other legal character for its index, then the method calls itself with the `tail` of the input `List`
and the output `Lists` as is.
If the `head` is not a legal character for its index, then the method returns `None`.

If the `index` is neither `0` or `3`, then  the cons operator (`::`) is used to destructure the [`head`][head] and [`tail`][tail] from the input `List`,
and the `head` is checked for being a digit.
If it is a digit, then the method calls itself, passing in the `tail` of the input `List`
and the `head` prepended to the output `List` with the cons operator (`::`).
If the `head` is not a digit but is some other legal character, then the method calls itself with the `tail` of the input `List`
and the output `Lists` as is.
If the `head` is not a legal character, then the method returns `None`.

[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[immutability]: https://alvinalexander.com/scala/scala-idiom-immutable-code-functional-programming-immutability/
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/2.12.1/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[last]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#last:A
[init]: https://www.scala-lang.org/api/2.12.7/scala/collection/immutable/StringOps.html#init:Repr
[reverse]: https://www.scala-lang.org/api/2.13.4/scala/collection/immutable/List.html#reverse:List[A]
[mkstring]: https://www.scala-lang.org/api/2.13.4/scala/collection/immutable/List.html#mkString:String
[head]: https://www.scala-lang.org/api/2.13.4/scala/collection/immutable/List.html#head:A
[tail]: https://www.scala-lang.org/api/2.13.4/scala/collection/immutable/List.html#tail:C
