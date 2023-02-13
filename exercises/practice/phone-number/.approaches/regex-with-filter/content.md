#  `Regex` with `filter()`

```scala
object PhoneNumber {
  private val VALID_PHONE_NUMBER = raw"^1?([2-9]\d{2}[2-9]\d{6})$$".r

  def clean(input: String): Option[String] = {
    val cleaned = input.filter(_.isDigit)
    Option(cleaned) collect { case VALID_PHONE_NUMBER(cleaned) => cleaned }
  }
}
```

This approach starts by defining the [regex pattern][regex-pattern] for a valid phone number in a [raw string][raw-string].
- `^1?` means that the `String` being checked starts with `1` one or zero times.
- `[2-9]` means the next character must be `2` through `9`.
- `\d{2}` means the next two characters must be digits (with no limit to their value, so `0` through `9`.)
- `[2-9]` means the next character must be `2` through `9`.
- `\d{6}` means the next six characters must be digits (with no limit to their value, so `0` through `9`.)
- `$$` is the only escape in the raw string.
`$` means the end of the string, so the `String` being matched must have the preceeding six digits as the last part of the `String`.

The input `String` calls the [`filter()`][filter] method.
Each character is passed to `filter()`, which in turn passes the character to the [`isDigit`][isdigit] method.
Only the characters that are digits survive to be passed as a `String` to [`Option()`][option].
If there are no digits, then `Option` returns `None`.

Otherwise, the cleaned `String` is passed to [`collect()`][collect].
The `case` returns the cleaned `String` if it matches the valid phone number pattern.
If the phone number does not match the valid pattern, then `None` is returned.

TODO: put in links after scala-org comes back up.

[regex-pattern]: 
[raw-string]: 
[filter]: 
[isdigit]: 
[option]: 
[collect]: 
