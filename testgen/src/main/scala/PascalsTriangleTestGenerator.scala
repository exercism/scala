import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object PascalsTriangleTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/pascals-triangle.json")

    def toStr(any: Any): String = {
      any match {
        case list: List[_] =>
          val vals = list.map(s => toStr(s)).mkString(", ")
          s"List($vals)"
        case n: Int =>
          if (n == -1)
            "List()"
          else
            n.toString
        case v => throw new IllegalStateException("Unexpected value - " + v)
      }
    }

    def toString(expected: CanonicalDataParser.Expected): String = {
        expected match {
          case Left(error) => throw new IllegalStateException("Unexpected error - " + error)
          case Right(exp) => toStr(exp)
        }
    }

    def withLabeledTestOption(argNames: String*): ToOptionTestCaseData =
      withLabeledTestOpt { sut =>
        labeledTest =>
          if (labeledTest.result.valuesIterator.contains(null)) {
            None
          } else {
            val args = sutArgsFromInput(labeledTest.result, argNames*)
            val property = labeledTest.property
            val sutCall =
              s"""$sut.$property($args)"""
            val expected = toString(labeledTest.expected)
            Some(TestCaseData(labeledTest.description, sutCall, expected))
          }
      }

    val code = TestSuiteBuilder.buildFromOption(file, withLabeledTestOption("count"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
