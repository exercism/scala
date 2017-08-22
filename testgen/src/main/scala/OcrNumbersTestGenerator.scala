import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object OcrNumbersTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/ocr-numbers.json")

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Left(_) => throw new IllegalStateException()
        case Right(-1) => "\"?\""
        case Right(n) => s"""\"$n\""""
      }
    }

    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgs(labeledTest.result, argNames: _*)
          val property = labeledTest.property
          val sutCall =
            s"""$sut.$property($args)"""
          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTest("input"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}