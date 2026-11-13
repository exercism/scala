import scala.annotation.tailrec

import ForthError.ForthError

case class ForthState(stack: List[Int], words: Map[String, List[String]]) extends ForthEvaluatorState:
  override def toString: String = stack.reverse.mkString(" ")

class Forth extends ForthEvaluator:

  private val BuiltIns = Set("+", "-", "*", "/", "dup", "drop", "swap", "over")

  def eval(text: String): Either[ForthError, ForthEvaluatorState] =
    run(text.toLowerCase.split("\\s+").toList.filter(_.nonEmpty), ForthState(Nil, Map.empty))

  @tailrec
  private def run(tokens: List[String], state: ForthState): Either[ForthError, ForthState] = tokens match
    case Nil => Right(state)

    case ":" :: rest =>
      val (definition, end) = rest.span(_ != ";")
      define(definition, state) match
        case Some(defined) if end.nonEmpty => run(end.tail, defined)
        case _                             => Left(ForthError.InvalidWord)

    case word :: rest if state.words.contains(word) => run(state.words(word) ++ rest, state)

    case word :: rest =>
      builtIn(word, state.stack) match
        case Right(stack) => run(rest, state.copy(stack = stack))
        case Left(error)  => Left(error)

  /** A new word, with its body expanded now so that redefining a word later doesn't change it. */
  private def define(definition: List[String], state: ForthState): Option[ForthState] = definition match
    case name :: body if name.toIntOption.isEmpty => Some(state.copy(words = state.words + (name -> body.flatMap(expand(state)))))
    case _                                        => None

  private def expand(state: ForthState)(word: String): List[String] = state.words.getOrElse(word, List(word))

  private def builtIn(word: String, stack: List[Int]): Either[ForthError, List[Int]] = (word, stack) match
    case (number, _) if number.toIntOption.isDefined => Right(number.toInt :: stack)
    case ("+", b :: a :: rest)                       => Right(a + b :: rest)
    case ("-", b :: a :: rest)                       => Right(a - b :: rest)
    case ("*", b :: a :: rest)                       => Right(a * b :: rest)
    case ("/", 0 :: _ :: _)                          => Left(ForthError.DivisionByZero)
    case ("/", b :: a :: rest)                       => Right(a / b :: rest)
    case ("dup", a :: rest)                          => Right(a :: a :: rest)
    case ("drop", _ :: rest)                         => Right(rest)
    case ("swap", b :: a :: rest)                    => Right(a :: b :: rest)
    case ("over", b :: a :: rest)                    => Right(a :: b :: a :: rest)
    case (op, _) if BuiltIns.contains(op)            => Left(ForthError.StackUnderflow)
    case _                                           => Left(ForthError.UnknownWord)
