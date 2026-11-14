# Instructions append

## Track specific instructions

For something a little different you might also try a solution with an `unfold` function.
You are probably already familiar with `foldLeft/Right`: "map" a whole collection into something else (usually a non-collection).
`unfold` is the "inverse" operation: "map" something (usually a non-collection) into a collection.
So `unfold`ing is a logical addition to and part of the FP standard repertoire.

This exercise can be seen as a case for `unfold`ing: "map" an `Int` into a `String` (which is of course implicitly a `Seq[Char]`).

Scala's collections have it since 2.13, for example [`List.unfold`][list] and `Iterator.unfold`.
It takes a start state and a function that returns the next element and the next state, or `None` to stop:

```scala
List.unfold(1234)(n => Option.when(n > 0)((n % 10, n / 10)))
// List(4, 3, 2, 1)
```

[list]: https://www.scala-lang.org/api/current/scala/collection/immutable/List$.html
