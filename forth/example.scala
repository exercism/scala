import ForthError.ForthError
import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

object ForthError extends Enumeration {
  type ForthError = Value
  val DivisionByZero, StackUnderflow, InvalidWord, UnknownWord = Value
}

case class ForthState(stack: List[Int], definitions: Map[String, List[Definition]]) {
  override def toString: String = stack.reverse.mkString(" ")
}

object EmptyState extends ForthState(List.empty, Map.empty)

object Definition {
  val add = "+"
  val subtract = "-"
  val multiply = "*"
  val divide = "/"
  val dup = "dup"
  val drop = "drop"
  val swap = "swap"
  val over = "over"
}

sealed abstract class Definition {
  def evaluate(state: Either[ForthError, ForthState]): Either[ForthError, ForthState]
}

sealed abstract class TermDefinition extends Definition {
  protected def word: String

  def evaluate(state: Either[ForthError, ForthState]): Either[ForthError, ForthState] =
    if (shouldEvaluateUserDef(state)) {
      evaluateUserDef(state)
    } else {
      evaluateDefinition(state)
    }

  def evaluateDefinition(state: Either[ForthError, ForthState]): Either[ForthError, ForthState]

  protected def shouldEvaluateUserDef(state: Either[ForthError, ForthState]) =
    state match {
      case Left(_) => false
      case Right(fs) => fs.definitions contains word
    }

  protected def evaluateUserDef(state: Either[ForthError, ForthState]): Either[ForthError, ForthState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val fsMap = fs.definitions
        fsMap get word match {
          case Some(definitions) => definitions.foldLeft(state)((acc, definition) => definition.evaluate(acc))
          case None => Left(ForthError.InvalidWord)
        }
    }
}

case class Add() extends TermDefinition {
  override protected def word = Definition.add

  override def evaluateDefinition(state: Either[ForthError, ForthState]): Either[ForthError, ForthState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.stack
        if (stack.size < 2) {
          Left(ForthError.StackUnderflow)
        } else {
          val (terms, rest) = stack.splitAt(2)
          val updatedStack = (terms.tail.head + terms.head) :: rest
          Right(ForthState(updatedStack, fs.definitions))
        }
    }
}

case class Subtract() extends TermDefinition {
  override protected def word = Definition.subtract

  override def evaluateDefinition(state: Either[ForthError, ForthState]): Either[ForthError, ForthState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.stack
        if (stack.size < 2) {
          Left(ForthError.StackUnderflow)
        } else {
          val (terms, rest) = stack.splitAt(2)
          val updatedStack = (terms.tail.head - terms.head) :: rest
          Right(ForthState(updatedStack, fs.definitions))
        }
    }
}

case class Multiply() extends TermDefinition {
  override protected def word = Definition.multiply

  override def evaluateDefinition(state: Either[ForthError, ForthState]): Either[ForthError, ForthState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.stack
        if (stack.size < 2) {
          Left(ForthError.StackUnderflow)
        } else {
          val (terms, rest) = stack.splitAt(2)
          val updatedStack = (terms.tail.head * terms.head) :: rest
          Right(ForthState(updatedStack, fs.definitions))
        }
    }
}

case class Divide() extends TermDefinition {
  override protected def word = Definition.divide

  override def evaluateDefinition(state: Either[ForthError, ForthState]): Either[ForthError, ForthState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.stack
        if (stack.size < 2) {
          Left(ForthError.StackUnderflow)
        } else {
          val (terms, rest) = stack.splitAt(2)
          if (terms.head == 0) {
            Left(ForthError.DivisionByZero)
          } else {
            val updatedStack = (terms.tail.head / terms.head) :: rest
            Right(ForthState(updatedStack, fs.definitions))
          }
        }
    }
}

case class Dup() extends TermDefinition {
  override protected def word = Definition.dup

  override def evaluateDefinition(state: Either[ForthError, ForthState]): Either[ForthError, ForthState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.stack
        if (stack.isEmpty) {
          Left(ForthError.StackUnderflow)
        } else {
          Right(ForthState(stack.head :: stack, fs.definitions))
        }
    }
}

case class Drop() extends TermDefinition {
  override protected def word = Definition.drop

  override def evaluateDefinition(state: Either[ForthError, ForthState]): Either[ForthError, ForthState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.stack
        if (stack.isEmpty) {
          Left(ForthError.StackUnderflow)
        } else {
          Right(ForthState(stack.tail, fs.definitions))
        }
    }
}

case class Swap() extends TermDefinition {
  override protected def word = Definition.swap

  override def evaluateDefinition(state: Either[ForthError, ForthState]): Either[ForthError, ForthState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.stack
        if (stack.size < 2) {
          Left(ForthError.StackUnderflow)
        } else {
          val (terms, rest) = stack.splitAt(2)
          val updatedStack = terms.reverse ::: rest
          Right(ForthState(updatedStack, fs.definitions))
        }
    }
}

case class Over() extends TermDefinition {
  override protected def word = Definition.over

  override def evaluateDefinition(state: Either[ForthError, ForthState]): Either[ForthError, ForthState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.stack
        if (stack.size < 2) {
          Left(ForthError.StackUnderflow)
        } else {
          val v = stack(1)
          val updatedStack = v :: stack
          Right(ForthState(updatedStack, fs.definitions))
        }
    }
}

case class UserDef(userTerm: String, definitions: List[TermDefinition]) extends TermDefinition {
  override protected def word = ""

  override def evaluateDefinition(state: Either[ForthError, ForthState]): Either[ForthError, ForthState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val fsMap = fs.definitions
        val updatedMap = fsMap + (userTerm -> definitions)
        val stack = fs.stack
        Right(ForthState(stack, updatedMap))
    }
}

case class PossibleUser(word: String) extends TermDefinition  {
  override protected def shouldEvaluateUserDef(state: Either[ForthError, ForthState]): Boolean = true

  override def evaluateDefinition(state: Either[ForthError, ForthState]): Either[ForthError, ForthState] = state
}

case class Term(value: Int) extends TermDefinition {
  override protected def word = ""

  override def evaluateDefinition(state: Either[ForthError, ForthState]): Either[ForthError, ForthState] =
    state match {
      case Left(_) => state
      case Right(fs) => Right(ForthState(value :: fs.stack, fs.definitions))
    }
}

class Forth extends RegexParsers {

  override protected val whiteSpace: Regex = "(?U)(\\s|[\\u0000-\\u001f])+".r

    private def word: Parser[TermDefinition] = "(?U)[\\S&&[^;]&&[^0-9]]+".r ^^ { s => {
      s.toLowerCase match {
        case Definition.add => Add()
        case Definition.subtract => Subtract()
        case Definition.multiply => Multiply()
        case Definition.divide => Divide()
        case Definition.dup => Dup()
        case Definition.drop => Drop()
        case Definition.swap => Swap()
        case Definition.over => Over()
        case possibleUserWord: String => PossibleUser(possibleUserWord)
      }
    }
  }

  private def userDefinition: Parser[TermDefinition] = (":" ~> "(?U)[\\S&&[^0-9]]+".r ~ rep(word | number) <~ ";") ^^ {
    case userDef: Forth.this.~[String, List[TermDefinition]] => UserDef(userDef._1.toLowerCase, userDef._2)
  }

  private def number: Parser[TermDefinition] = "(?U)([0-9]+)".r ^^ { s => Term(s.toInt) }
  private def term: Parser[TermDefinition] = userDefinition | word | number
  private def terms: Parser[List[TermDefinition]] = rep(term)

  def eval(text: String): Either[ForthError, ForthState] = parseAll(terms, text) match {
    case Success(defs: List[TermDefinition], _) =>
      defs.foldLeft(Right(EmptyState): Either[ForthError, ForthState])((acc, definition: TermDefinition) => definition.evaluate(acc))
    case NoSuccess(_, _) => Left(ForthError.InvalidWord)
  }
}