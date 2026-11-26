# Instructions append

## Track specific instructions

You can solve this exercise with the standard library alone, for example by splitting the question into words or by matching it with regular expressions.

It is also a good opportunity to try [parser combinators][parser-combinators]: small parsers that you combine into bigger ones, so that the code reads much like the grammar it accepts.
The `scala-parser-combinators` library is not part of the standard library, but it is already listed in the `build.sbt` of this exercise, so you can use it both locally and in the online editor.

As an illustration, this parser accepts a list of numbers such as `[1, 2, 3]`:

```scala
import scala.util.parsing.combinator.RegexParsers

object Numbers extends RegexParsers:

  private def number: Parser[Int] = """\d+""".r ^^ (_.toInt)

  private def numbers: Parser[List[Int]] = "[" ~> repsep(number, ",") <~ "]"

  def parse(input: String): Option[List[Int]] = parseAll(numbers, input) match
    case Success(result, _) => Some(result)
    case _                  => None
```

- A string or a regular expression is a parser that matches itself, and `RegexParsers` skips the whitespace between them.
- `^^` converts the result of a parser.
- `~` runs two parsers in sequence and keeps both results, while `~>` and `<~` keep only the result the arrow points at.
- `rep` and `repsep` repeat a parser, and `|` tries an alternative.
- `parseAll` succeeds only if the whole input is consumed.

The [Getting Started guide][getting-started] explains the library in more detail.

[parser-combinators]: https://github.com/scala/scala-parser-combinators
[getting-started]: https://github.com/scala/scala-parser-combinators/blob/main/docs/Getting_Started.md
