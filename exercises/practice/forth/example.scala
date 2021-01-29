import ForthError.ForthError
import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

case class ForthState(stack: List[Int], definitions: Map[String, List[Definition]])
  extends ForthEvaluatorState {

  override def toString: String = stack.reverse.mkString(" ")
}

object EmptyState extends ForthState(List.empty, Map.empty)

object Definitions {
  val add = "+"
  val subtract = "-"
  val multiply = "*"
  val divide = "/"
  val dup = "dup"
  val drop = "drop"
  val swap = "swap"
  val over = "over"
}

sealed abstract class TermDefinition extends Definition {
  protected def word: String

  def evaluate(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    if (shouldEvaluateUserDef(state)) {
      evaluateUserDef(state)
    } else {
      evaluateDefinition(state)
    }

  def evaluateDefinition(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState]

  protected def shouldEvaluateUserDef(state: Either[ForthError, ForthEvaluatorState]) =
    state match {
      case Left(_) => false
      case Right(fs) => fs.asInstanceOf[ForthState].definitions contains word
    }

  protected def evaluateUserDef(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val fsMap = fs.asInstanceOf[ForthState].definitions
        fsMap get word match {
          case Some(definitions) => definitions.foldLeft(state)((acc, definition) => definition.evaluate(acc))
          case None => Left(ForthError.InvalidWord)
        }
    }
}

case class Add() extends TermDefinition {
  override protected def word = Definitions.add

  override def evaluateDefinition(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.asInstanceOf[ForthState].stack
        if (stack.size < 2) {
          Left(ForthError.StackUnderflow)
        } else {
          val (terms, rest) = stack.splitAt(2)
          val updatedStack = (terms.tail.head + terms.head) :: rest
          Right(ForthState(updatedStack, fs.asInstanceOf[ForthState].definitions))
        }
    }
}

case class Subtract() extends TermDefinition {
  override protected def word = Definitions.subtract

  override def evaluateDefinition(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.asInstanceOf[ForthState].stack
        if (stack.size < 2) {
          Left(ForthError.StackUnderflow)
        } else {
          val (terms, rest) = stack.splitAt(2)
          val updatedStack = (terms.tail.head - terms.head) :: rest
          Right(ForthState(updatedStack, fs.asInstanceOf[ForthState].definitions))
        }
    }
}

case class Multiply() extends TermDefinition {
  override protected def word = Definitions.multiply

  override def evaluateDefinition(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.asInstanceOf[ForthState].stack
        if (stack.size < 2) {
          Left(ForthError.StackUnderflow)
        } else {
          val (terms, rest) = stack.splitAt(2)
          val updatedStack = (terms.tail.head * terms.head) :: rest
          Right(ForthState(updatedStack, fs.asInstanceOf[ForthState].definitions))
        }
    }
}

case class Divide() extends TermDefinition {
  override protected def word = Definitions.divide

  override def evaluateDefinition(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.asInstanceOf[ForthState].stack
        if (stack.size < 2) {
          Left(ForthError.StackUnderflow)
        } else {
          val (terms, rest) = stack.splitAt(2)
          if (terms.head == 0) {
            Left(ForthError.DivisionByZero)
          } else {
            val updatedStack = (terms.tail.head / terms.head) :: rest
            Right(ForthState(updatedStack, fs.asInstanceOf[ForthState].definitions))
          }
        }
    }
}

case class Dup() extends TermDefinition {
  override protected def word = Definitions.dup

  override def evaluateDefinition(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.asInstanceOf[ForthState].stack
        if (stack.isEmpty) {
          Left(ForthError.StackUnderflow)
        } else {
          Right(ForthState(stack.head :: stack, fs.asInstanceOf[ForthState].definitions))
        }
    }
}

case class Drop() extends TermDefinition {
  override protected def word = Definitions.drop

  override def evaluateDefinition(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.asInstanceOf[ForthState].stack
        if (stack.isEmpty) {
          Left(ForthError.StackUnderflow)
        } else {
          Right(ForthState(stack.tail, fs.asInstanceOf[ForthState].definitions))
        }
    }
}

case class Swap() extends TermDefinition {
  override protected def word = Definitions.swap

  override def evaluateDefinition(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.asInstanceOf[ForthState].stack
        if (stack.size < 2) {
          Left(ForthError.StackUnderflow)
        } else {
          val (terms, rest) = stack.splitAt(2)
          val updatedStack = terms.reverse ::: rest
          Right(ForthState(updatedStack, fs.asInstanceOf[ForthState].definitions))
        }
    }
}

case class Over() extends TermDefinition {
  override protected def word = Definitions.over

  override def evaluateDefinition(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val stack = fs.asInstanceOf[ForthState].stack
        if (stack.size < 2) {
          Left(ForthError.StackUnderflow)
        } else {
          val v = stack(1)
          val updatedStack = v :: stack
          Right(ForthState(updatedStack, fs.asInstanceOf[ForthState].definitions))
        }
    }
}

case class UserDef(userTerm: String, definitions: List[TermDefinition]) extends TermDefinition {
  override protected def word = ""

  override def evaluateDefinition(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state match {
      case Left(_) => state
      case Right(fs) =>
        val fsMap = fs.asInstanceOf[ForthState].definitions
        val substitutedDefs: List[Definition] = definitions.flatMap {
          case PossibleUser(s) => fsMap(s)
          case d@(_: Definition) => List(d)
        }
        val updatedMap = fsMap + (userTerm -> substitutedDefs)
        val stack = fs.asInstanceOf[ForthState].stack
        Right(ForthState(stack, updatedMap))
    }
}

case class PossibleUser(word: String) extends TermDefinition  {
  override protected def shouldEvaluateUserDef(state: Either[ForthError, ForthEvaluatorState]): Boolean = true

  override def evaluateDefinition(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] = state
}

case class Term(value: Int) extends TermDefinition {
  override protected def word = ""

  override def evaluateDefinition(state: Either[ForthError, ForthEvaluatorState]): Either[ForthError, ForthEvaluatorState] =
    state match {
      case Left(_) => state
      case Right(fs) => Right(ForthState(value :: fs.asInstanceOf[ForthState].stack,
        fs.asInstanceOf[ForthState].definitions))
    }
}

class Forth extends ForthEvaluator with RegexParsers {

  override protected val whiteSpace: Regex = "(?U)(\\s|[\\u0000-\\u001f])+".r

  private def word: Parser[TermDefinition] = "(?U)[\\S&&[^;]&&[^0-9]]+".r ^^ { s => {
    s.toLowerCase match {
      case Definitions.add => Add()
      case Definitions.subtract => Subtract()
      case Definitions.multiply => Multiply()
      case Definitions.divide => Divide()
      case Definitions.dup => Dup()
      case Definitions.drop => Drop()
      case Definitions.swap => Swap()
      case Definitions.over => Over()
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

  def eval(text: String): Either[ForthError, ForthEvaluatorState] = parseAll(terms, text) match {
    case Success(defs: List[TermDefinition], _) =>
      defs.foldLeft(Right(EmptyState): Either[ForthError, ForthEvaluatorState])((acc, definition: TermDefinition) => definition.evaluate(acc))
    case NoSuccess(_, _) => Left(ForthError.InvalidWord)
  }
}
