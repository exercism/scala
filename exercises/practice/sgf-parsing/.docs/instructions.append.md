# Instructions append

## Track specific instructions

The stub extends `RegexParsers` from the `scala-parser-combinators` library, which is already listed in the `build.sbt` of this exercise.
With [parser combinators][parser-combinators] you write a small parser for each part of the format and combine them, so that the code reads much like the grammar of SGF.
You do not have to use the library: remove `extends RegexParsers` if you prefer to write the parser by hand.

As an illustration, this parser reads nested lists of numbers such as `(1 (2 3) ())`:

```scala
import scala.util.parsing.combinator.RegexParsers

enum Nested:
  case Leaf(value: Int)
  case Branch(children: List[Nested])

object NestedParser extends RegexParsers:
  import Nested.*

  private def leaf: Parser[Nested] = """\d+""".r ^^ (digits => Leaf(digits.toInt))

  private def branch: Parser[Nested] = "(" ~> rep(nested) <~ ")" ^^ (Branch(_))

  private def nested: Parser[Nested] = leaf | branch

  def parse(input: String): Option[Nested] = parseAll(nested, input) match
    case Success(result, _) => Some(result)
    case _                  => None
```

Two things are worth knowing before you start:

- A grammar for trees is recursive: here `branch` uses `nested`, and `nested` uses `branch`.
  Define the parsers with `def` (or `lazy val`), so that they can refer to each other in any order.
- `RegexParsers` skips whitespace before every string and regular expression.
  That is convenient for the lists above, but whitespace is significant inside an SGF property value.
  Add `override val skipWhitespace = false` to your object to turn it off.

The [Getting Started guide][getting-started] explains the library in more detail.

[parser-combinators]: https://github.com/scala/scala-parser-combinators
[getting-started]: https://github.com/scala/scala-parser-combinators/blob/main/docs/Getting_Started.md
