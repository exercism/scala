import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object CollatzConjectureTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/collatz-conjecture.json")

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Left(_) => "None"
        case Right(null) => "None"
        case Right(n) => s"Some($n)"
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

    val code = TestSuiteBuilder.build(file, fromLabeledTest("number"))

    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
