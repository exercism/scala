# `Regex` with `filter()`

```scala
object PhoneNumber:

  private val ValidPhoneNumber = raw"1?([2-9]\d{2}[2-9]\d{6})".r

  def clean(input: String): Option[String] =
    val cleaned = input.filter(_.isDigit)
    Some(cleaned).collect { case ValidPhoneNumber(number) => number }
```

This approach starts by defining the [regex pattern][regex-pattern] for a valid phone number in a [raw string][raw-string],
so that `\d` needs no extra backslash.
The [`r`][regex] method turns the `String` into a `Regex`.
- `1?` means that the `String` being checked starts with `1` one or zero times.
- `[2-9]` means the next character must be `2` through `9`.
- `\d{2}` means the next two characters must be digits (with no limit to their value, so `0` through `9`.)
- `[2-9]` means the next character must be `2` through `9`.
- `\d{6}` means the next six characters must be digits (with no limit to their value, so `0` through `9`.)
- The parentheses capture the ten digits after the optional `1`.

The input `String` calls the [`filter()`][filter] method.
Each character is passed to `filter()`, which in turn passes the character to the [`isDigit`][isdigit] method.
Only the characters that are digits survive to be wrapped in a `Some` and passed to [`collect()`][collect].

The `case` uses the `Regex` as a [pattern][regex-patterns].
A `Regex` pattern must match the whole `String`, so no `^` or `$` anchors are needed,
and it binds what the parentheses captured to `number`.
If the cleaned `String` matches the valid phone number pattern, `collect()` returns the ten digits in a `Some`.
If the phone number does not match the valid pattern, then `collect()` returns `None`.

[regex-pattern]: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/regex/Pattern.html
[raw-string]: https://docs.scala-lang.org/overviews/core/string-interpolation.html
[regex]: https://www.scala-lang.org/api/current/scala/util/matching/Regex.html
[regex-patterns]: https://docs.scala-lang.org/tour/regular-expression-patterns.html
[filter]: https://www.scala-lang.org/api/current/scala/collection/StringOps.html
[isdigit]: https://www.scala-lang.org/api/current/scala/runtime/RichChar.html
[collect]: https://www.scala-lang.org/api/current/scala/Option.html
