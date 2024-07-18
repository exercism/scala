import java.io.File

import testgen.TestSuiteBuilder.{toString, _}
import testgen._

object HammingTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/hamming.json")

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Left(_) => "None"
        case Right(-1) => "None"
        case Right(n) => s"Some($n)"
      }
    }

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgsFromInput(labeledTest.result, argNames*)
          val property = labeledTest.property
          val sutCall =
            s"""Hamming.$property($args)"""
          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("strand1", "strand2"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
