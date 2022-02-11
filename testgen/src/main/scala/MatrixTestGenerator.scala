import java.io.File

import testgen.{CanonicalDataParser, TestCaseData, TestSuiteBuilder}
import testgen.TestSuiteBuilder.{ToTestCaseData, quote, withLabeledTest}

import scala.language.postfixOps

object MatrixTestGenerator {
  def toString(expected: CanonicalDataParser.Expected): String = {
    expected match {
      case Right(xs: List[Int]) => xs mkString ", "
      case _ => throw new IllegalArgumentException
    }
  }

  def fromLabeledTestFromInput(): ToTestCaseData =
    withLabeledTest { sut =>
      labeledTest =>
        val input = labeledTest.result("input").asInstanceOf[Map[String, Any]]
        val matrix = input("string").asInstanceOf[String].lines.map(l => s""""$l""").toString.mkString("\\n\" +") + "\""
        val index = input("index").toString
        val property = labeledTest.property
        val sutCall =
          s"""$sut($matrix).$property($index)"""
        val expected = s"""Vector(${toString(labeledTest.expected)})"""
        TestCaseData(labeledTest.description, sutCall, expected)
    }

  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/matrix.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput())
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
