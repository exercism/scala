# Hints
For simplicity and readability: Consider using the Scala collection functions instead of Java's `String` methods. Remember that in Scala a `String` is implicitly also a `Seq[Char]`, so you can call them as easily as the `String` methods.

Some examples:
- `filter` instead of `replaceAll`
- `take`, `takeRight`, `drop`, `head`, `tail` instead of `substring`

Another idea worth exploring might be to change the `String` into a `List[Char]`
and then use [pattern matching](http://alvinalexander.com/scala/how-to-use-lists-nil-cons-scala-match-case-expressions) with the `::` operator.
