import scala.util.parsing.combinator.RegexParsers

object MatchingBrackets extends RegexParsers:

  private val text = """[^\[\](){}]+""".r

  private def group: Parser[Any] = "(" ~ rep(group) ~ ")" | "[" ~ rep(group) ~ "]" | "{" ~ rep(group) ~ "}" | text

  def isPaired(brackets: String): Boolean = parseAll(rep(group), brackets).successful
