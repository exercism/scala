import scala.util.parsing.combinator.RegexParsers
import Alphametics.Solution

object Alphametics {
  type Puzzle = String
  type Solution = Map[Char, Int]

  def solve(puzzle: Puzzle): Option[Solution] = {
    val expression = parse(puzzle)
    val uniqueLetters = puzzle filter (_.isLetter) distinct

    expression flatMap solveExpression(uniqueLetters)
  }

  private def parse(str: String): Option[Expression[Boolean]] =
    ExpressionParser.parse(str)

  private def solveExpression(uniqueLetters: String)(boolExpr: Expression[Boolean]): Option[Solution] = {
    def isSolution(solution: Solution): Boolean =
      boolExpr.eval(solution) getOrElse false

    def toSolution(tenChars: String): Solution =
      tenChars.zipWithIndex.toMap filterKeys (_.isLetter)

    val tenChars = uniqueLetters ++ Seq.fill(10 - uniqueLetters.size)('.')
    tenChars.permutations map toSolution find isSolution
  }
}


object ExpressionParser extends RegexParsers {

  private implicit def parseResultToOption[T](parseResult: ParseResult[T]): Option[T] =
    parseResult map (Some(_)) getOrElse None

  def parse(str: String): Option[Expression[Boolean]] =
    parseAll(booleanExpression, str)

  private def booleanExpression: Parser[Expression[Boolean]] =
    intExpression ~ "==" ~ intExpression ^^ {
      case left ~ _ ~ right => Equals(left, right)
    }

  private def intExpression: Parser[Expression[Int]] =
    intOperation | nonOperator

  private def intOperation: Parser[Expression[Int]] =
    nonOperator ~ operator ~ intExpression ^^ {
      case left ~ op ~ right => op(left, right)
    }

  private def operator: Parser[(Expression[Int], Expression[Int]) => Expression[Int]] =
    plus | mult | power

  private def nonOperator: Parser[Expression[Int]] =
    word | number

  private val word: Parser[Word] =
    "[A-Z]+".r ^^ (Word(_))

  private val number: Parser[Number] =
    "[0-9]+".r ^^ (n =>(Number(n.toInt)))

  private val plus: Parser[(Expression[Int], Expression[Int]) => Expression[Int]] =
    "+"  ^^ const(Plus(_, _))

  private val mult: Parser[(Expression[Int], Expression[Int]) => Expression[Int]] =
    "*" ^^ const(Mult(_, _))

  private val power: Parser[(Expression[Int], Expression[Int]) => Expression[Int]] =
    "^" ^^ const(Power(_, _))

  private def const[A](a: A)(ignore: Any) = a
}


sealed trait Expression[T] {
  def eval(solution: Solution): Option[T]
}

case class Word(word: String) extends Expression[Int] {
  override def eval(solution: Solution) =
    if (solution(word(0)) == 0) None
    else Some((word map solution mkString) toInt)
}

case class Number(number: Int) extends Expression[Int] {
  override def eval(solution: Solution) = Some(number)
}

trait Operation[A,B] extends Expression[B] {
  val op: (A, A) => B
  val left: Expression[A]
  val right: Expression[A]
  override def eval(solution: Solution) =
    for {
      l <- left.eval(solution)
      r <- right.eval(solution)
    } yield op(l, r)
}

case class Equals[A](override val left: Expression[A],
    override val right: Expression[A]) extends Operation[A, Boolean]
{
  override val op: (A, A) => Boolean = (_ == _)
}

case class Plus(override val left: Expression[Int],
    override val right: Expression[Int]) extends Operation[Int, Int]
{
  override val op: (Int, Int) => Int = (_ + _)
}

case class Mult(override val left: Expression[Int],
    override val right: Expression[Int]) extends Operation[Int, Int]
{
  override val op: (Int, Int) => Int = (_ * _)
}

case class Power(override val left: Expression[Int],
    override val right: Expression[Int]) extends Operation[Int, Int]
{
  override val op: (Int, Int) => Int = math.pow(_, _).toInt
}

