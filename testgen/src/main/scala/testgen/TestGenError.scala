package testgen

import scala.util.control.NoStackTrace

enum TestGenError extends NoStackTrace:
  case ParserError(value: String)

  override def getMessage(): String = productPrefix

type Result[A] = Either[TestGenError, A]
