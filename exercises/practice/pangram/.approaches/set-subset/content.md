# `Set` with `subsetOf()`

```scala
object Pangrams:
  private val alphabet = ('a' to 'z').toSet

  def isPangram(input: String): Boolean =
    alphabet.subsetOf(input.toLowerCase.toSet)
```

This approach starts by defining the alphabet as a private value of the `Pangrams` object, so it is built only once.
The [`to`][to] method creates the range of [`Char`][char]s from `a` through `z`, and [`toSet`][numeric-range] turns the range into a [`Set`][set].

The `isPangram()` method lowercases the sentence with `toLowerCase`, so that `A` and `a` count as the same letter.
Calling [`toSet`][stringops] on the result gives the set of all the different characters in the sentence.
A set holds each of its elements only once, so the repeated characters disappear.

The [`subsetOf()`][set] method then checks that every element of `alphabet` is also an element of that set.
The spaces, digits and punctuation marks in the sentence need no special handling:
they are extra elements of the sentence's set, and extra elements do not stop the alphabet from being a subset of it.

Since the call to `subsetOf()` is the last expression of the method, its value is returned without needing the `return` keyword.

[to]: https://www.scala-lang.org/api/current/scala/runtime/RichChar.html
[char]: https://www.scala-lang.org/api/current/scala/Char.html
[numeric-range]: https://www.scala-lang.org/api/current/scala/collection/immutable/NumericRange.html
[set]: https://www.scala-lang.org/api/current/scala/collection/immutable/Set.html
[stringops]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
