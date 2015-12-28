import ForthError.ForthError

object ForthError extends Enumeration {
  type ForthError = Value
  val DivisionByZero, StackUnderflow, InvalidWord, UnknownWord = Value
}

case class ForthState() {
  // TODO: Implement. return the current stack as Text with the element
  // on top of the stack being the rightmost element in the output."
  override def toString: String = ???
}


sealed abstract class Definition {
  def evaluate(state: Either[ForthError, ForthState]): Either[ForthError, ForthState]
}


class Forth  {
  // TODO: Implement evaluation
  def eval(text: String): Either[ForthError, ForthState] = ???
}