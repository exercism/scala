import scala.util.parsing.combinator.RegexParsers

object Wordy extends RegexParsers:

  private val Operations = Map[String, (Int, Int) => Int](
    "plus"          -> (_ + _),
    "minus"         -> (_ - _),
    "multiplied by" -> (_ * _),
    "divided by"    -> (_ / _),
  )

  private def number: Parser[Int] = """-?\d+""".r ^^ (_.toInt)

  private def operation: Parser[(Int, Int) => Int] = Operations.keys.map(literal).reduce(_ | _) ^^ Operations

  private def expression: Parser[Int] = number ~ rep(operation ~ number) ^^ { case first ~ steps =>
    steps.foldLeft(first) { case (result, op ~ n) => op(result, n) }
  }

  private def problem: Parser[Int] = "What is" ~> expression <~ "?"

  def answer(question: String): Option[Int] = parseAll(problem, question) match
    case Success(result, _) => Some(result)
    case _                  => None
