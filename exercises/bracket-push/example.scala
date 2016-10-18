import scala.util.parsing.combinator.RegexParsers

object Brackets extends RegexParsers {
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

  def areBalanced(s: String) = this.parseAll(all, s) match {
    case NoSuccess(_, _) => false
    case Success(_, _) => true
  }
}