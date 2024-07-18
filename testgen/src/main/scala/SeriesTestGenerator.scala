import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object SeriesTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/largest-series-product.json")

    def toExpected(any: Any): String = {
      any match {
        case -1 => "None"
        case i: Int => s"Some($i)"
        case s: String =>
          val quot = quote(s)
          s"$quot$s$quot"
        case _ => throw new IllegalStateException("Invalid expected val -" + any)
      }
    }

    def toString(expected: CanonicalDataParser.Expected): String = {
        expected match {
          case Left(error) => s"None"
          case Right(exp) => toExpected(exp)
        }
    }

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgsFromInput(labeledTest.result, argNames*)
          val property = labeledTest.property
          val sutCall =
            s"""Series.$property($args)"""
          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("span", "digits"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
