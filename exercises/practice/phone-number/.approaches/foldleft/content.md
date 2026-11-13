# `foldLeft()`

```scala
object PhoneNumber:

  def clean(input: String): Option[String] =
    val (digits, count) = input.foldLeft((List.empty[Char], 0)): (tup, head) =>
      tup._2 match
        case index @ (0 | 3) =>
          head.isDigit match
            case true if head > '1'                                        => (head :: tup._1, index + 1)
            case true if index == 0 && head == '1'                         => tup
            case false if index == 0 && List('+', '(', ' ').contains(head) => tup
            case false if index == 3 && List(')', ' ', '.').contains(head) => tup
            case _                                                         => (Nil, -100)
        case index           =>
          head.isDigit match
            case true                                                      => (head :: tup._1, index + 1)
            case false if index == 6 && List(' ', '-', '.').contains(head) => tup
            case false if head == ' '                                      => tup
            case _                                                         => (Nil, -100)
    if count == 10 then Some(digits.reverse.mkString) else None
```

This approach starts by calling the [`foldLeft()`][foldLeft] method on the input `String`.
It is initialized with an empty `List` for building the output `String` and `0` for the index, both wrapped in a [tuple][tuple].
The tuple and each character in the input `String` are passed to the [lambda][lambda].
The lambda is the last argument of `foldLeft()`, so it can follow a [colon instead of being wrapped in braces][fewer-braces].
In the lambda the index in the tuple is passed to the [match][match].
The [pattern matching][pattern-matching] checks if the index is `0` or `3`.
The pattern `index @ (0 | 3)` matches either value and [binds][binding] it to `index`.

If so, and the `head` character is a digit greater than `1`, then a new tuple is made from the `head` prepended to the output `List`
with the cons operator (`::`), and with the index added to by `1`.
If the `head` is some other legal character for its index, then the tuple is passed as is.
If the `head` is not a legal character for its index, then a new tuple is made from a `Nil` output `List`
and with the index given a value such that it will reasonably never be valid.

If the `index` is neither `0` nor `3`, then the `head` is checked for being a digit.
If it is a digit, then a new tuple is made from the `head` prepended to the output `List`
with the cons operator (`::`), and with the index added to by `1`.
If the `head` is not a digit but is some other legal character, then the tuple is passed as is.
If the `head` is not a legal character, then a new tuple is made from a `Nil` output `List`
and with the index given a value such that it will reasonably never be valid.

The result of `foldLeft()` is destructured into `digits` and `count`.
If the `count` is `10`, then the [`reverse`][reverse] and [`mkString`][mkstring] methods are used on the `digits` to return a `Some` value.
If the `count` is not `10`, then `None` is returned.

[foldLeft]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[tuple]: https://docs.scala-lang.org/tour/tuples.html
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[fewer-braces]: https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html#optional-braces-for-method-arguments
[match]: https://docs.scala-lang.org/scala3/book/control-structures.html#match-expressions
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[binding]: https://docs.scala-lang.org/scala3/book/control-structures.html#binding-matched-patterns-to-variables
[reverse]: https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
[mkstring]: https://www.scala-lang.org/api/current/scala/collection/immutable/List.html
