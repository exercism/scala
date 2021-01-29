import scala.math.pow
import scala.util.parsing.combinator.RegexParsers

sealed abstract class Expression {
  def evaluate(): Integer
}

case class Const(value: Int) extends Expression {
  def evaluate() = value
}

case class Sum(e1: Expression, e2: Expression) extends Expression {
  def evaluate(): Integer = e1.evaluate() + e2.evaluate()
}

case class Diff(e1: Expression, e2: Expression) extends Expression {
  def evaluate(): Integer = e1.evaluate() - e2.evaluate()
}

case class Mult(e1: Expression, e2: Expression) extends Expression {
  def evaluate(): Integer = e1.evaluate() * e2.evaluate()
}

case class Div(e1: Expression, e2: Expression) extends Expression {
  def evaluate(): Integer = e1.evaluate() / e2.evaluate()
}

case class Pow(e1: Expression, e2: Expression) extends Expression {
  def evaluate(): Integer = pow(e1.evaluate().toDouble, e2.evaluate().toDouble).toInt
}

object Wordy extends RegexParsers {
  private lazy val prefix = "What is "
  private lazy val questionMark = "?"
  private lazy val operators = "plus" | "minus" | "multiplied by" |
    "divided by" | "raised to the"

  private def operation = expr ~ rep(operators ~ expr) ^^ {
    case op ~ list => list.foldLeft(op) {
      case (x, "plus" ~ y) => Sum(x, y)
      case (x, "minus" ~ y) => Diff(x, y)
      case (x, "multiplied by" ~ y) => Mult(x, y)
      case (x, "divided by" ~ y) => Div(x, y)
      case (x, "raised to the" ~ y) => Pow(x, y)
    }
  }

  private def operand: Parser[Const] = """-?\d+""".r ^^ {s => Const(s.toInt)}
  private def expr: Parser[Expression] = operand | operation

  // Top level parser
  private def questionParser = prefix ~> operation <~ questionMark

  def parse(str: String): ParseResult[Expression] = parseAll(questionParser, str)

  def answer(question: String): Option[Int] = parse(question) match {
    case Success(result: Expression, _) => Some(result.evaluate())
    case _ => None
  }
}
