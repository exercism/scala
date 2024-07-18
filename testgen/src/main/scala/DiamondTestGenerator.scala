import java.io.File

import testgen.TestSuiteBuilder.{sutArgsFromInput, _}
import testgen._

object DiamondTestGenerator {

  def main(args: Array[String]): Unit = {

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Right(xs: List[String]) => s"""List(${xs.map(c => s"""\"$c\"""").mkString(", ")})"""
        case _ => throw new IllegalArgumentException()
      }
    }

    def sutArgsFromInput(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String = {
      val input = parseResult("input").asInstanceOf[Map[String, Int]]

      s"\'${input("letter")}\'"
    }

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgsFromInput(labeledTest.result, argNames*)
          val property = labeledTest.property
          val sutCall =
            s"""$sut.$property($args)"""
          TestCaseData(labeledTest.description, sutCall, toString(labeledTest.expected))
      }

    val file = new File("src/main/resources/diamond.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("letter"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
