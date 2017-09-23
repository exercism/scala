import java.io.File

import testgen.TestSuiteBuilder.{toString, _}
import testgen._

object ForthTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/forth.json")

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Right(n: Int) => s""""${n.toString}""""
        case Right(vals: List[Int]) => s""""${vals.map(_.toString).mkString(" ")}""""
        case _ => throw new IllegalArgumentException
      }
    }

    def isError(expected: CanonicalDataParser.Expected): Boolean = {
      expected match {
        case Left(_) => true
        case Right(null) => true
        case Right(n) => false
      }
    }

    def argsToString(any: Any): String = {
      any match {
        case list: List[_] =>
          val vals = list.map(s => argsToString(s)).mkString(" ")
          s""""$vals""""
        case str: String =>
          s"$str"
        case _ => any.toString
      }
    }

    def sutArgs(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
      argNames map (name => argsToString(parseResult(name))) mkString ", "

    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgs(labeledTest.result, argNames: _*)
          val property = labeledTest.property
          val isErr = isError(labeledTest.expected)
          val sutCall = if (isErr)
              s"""forth.eval($args).isLeft"""
            else
              s"""forth.eval($args).fold(_ => "", _.toString)"""

          val expected = if (isErr)
              "true"
            else
              toString(labeledTest.expected)

          TestCaseData(s"${labeledTest.parentDescriptions.mkString(" - " )} - ${labeledTest.description}", sutCall, expected)
      }

    val code =
      TestSuiteBuilder.build(file, fromLabeledTest("input"), Seq(), Seq("private val forth = new Forth"))

    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
