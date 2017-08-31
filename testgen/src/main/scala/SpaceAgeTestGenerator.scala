import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object SpaceAgeTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/space-age.json")

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Left(_) => throw new IllegalStateException()
        case Right(n)  => s"$n"
      }
    }

    def sutArgs(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
      argNames map (name => TestSuiteBuilder.toString(parseResult(name))) mkString ", "

    def functionName(parseResult: CanonicalDataParser.ParseResult): String =
      "on" + parseResult("planet").toString

    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgs(labeledTest.result, argNames: _*)
          val property = labeledTest.property
          val sutCall =
            s"""$sut.${functionName(labeledTest.result)}($args)"""
          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTest("seconds"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
