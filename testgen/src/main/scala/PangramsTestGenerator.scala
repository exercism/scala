import testgen._
import TestSuiteBuilder._
import java.io.File

object PangramsTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/pangram.json")
    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgsFromInput(labeledTest.result, argNames*)
          val isPangram = labeledTest.property.mkString
          val sutCall =
            s"""Pangrams.$isPangram($args)"""
          val expected =
            labeledTest.expected.fold(Function.const("true"), x => s"$x")
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("sentence"))
    println(s"‐‐‐‐‐‐‐‐‐‐‐‐‐")
    println(code)
    println(s"‐‐‐‐‐‐‐‐‐‐‐‐‐")
  }
}
