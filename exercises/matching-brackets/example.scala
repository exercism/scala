import scala.util.parsing.combinator.RegexParsers

object MatchingBrackets extends RegexParsers {
  lazy val t = "[^\\[\\]\\(\\)\\{\\}]+".r

  private def paren: Parser[String] =
    ("(" ~ rep1(t | paren) ~ ")" |
      "[" ~ rep1(t | paren) ~ "]" |
      "{" ~ rep1(t | paren) ~ "}" |
      "(" ~ ")" |
      "[" ~ "]" |
      "{" ~ "}" |
      t) ^^ {
      case _ => ""
    }

  private def all = rep(paren)

  def isPaired(s: String) = this.parseAll(all, s) match {
    case NoSuccess(_, _) => false
    case Success(_, _) => true
  }
}