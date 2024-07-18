import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object ArmstrongNumbersTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/armstrong-numbers.json")

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Right(b: Boolean) => b.toString
        case _ => throw new IllegalStateException()
      }
    }

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgsFromInput(labeledTest.result, argNames*)
          val property = labeledTest.property
          val sutCall =
            s"""$sut.$property($args)"""
          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("number"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
