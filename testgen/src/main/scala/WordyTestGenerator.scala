import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object WordyTestGenerator {

  def toString(expected: CanonicalDataParser.Expected): String = {
    expected match {
      case Right(false) => s"None"
      case Right(n) => s"Some($n)"
      case _ => throw new IllegalStateException()
    }
  }

  def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
    withLabeledTest { sut =>
      labeledTest =>
        val args = sutArgsFromInput(labeledTest.result, argNames: _*)
        val property = labeledTest.property
        val sutCall =
          s"$sut.$property($args)"
        val expected = toString(labeledTest.expected)
        TestCaseData(labeledTest.description, sutCall, expected)
    }

  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/wordy.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("question"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
