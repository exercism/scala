import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object FlattenArrayTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/flatten-array.json")

    def sutArgs(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
      argNames map (name => toArgString(parseResult(name))) mkString ", "

    def toArgString(any: Any): String = {
      any match {
        case xs: List[Any] => s"List(${xs.map(x => toArgString(x)).mkString(", ")})"
        case null => "null"
        case _ => any.toString
      }
    }

    def toExpectedString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Left(_) => throw new IllegalStateException()
        case Right(xs: List[Any]) => s"List(${xs.mkString(", ")})"
      }
    }

    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgs(labeledTest.result, argNames: _*)
          val property = labeledTest.property
          val sutCall =
            s"""$sut.$property($args)"""
          val expected = toExpectedString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTest("input"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
