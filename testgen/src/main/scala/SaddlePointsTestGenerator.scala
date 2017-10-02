import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object SaddlePointsTestGenerator {
  def toString(expected: CanonicalDataParser.Expected): String = {
    expected match {
      case Right(xs: List[Map[String, Int]]) =>
        val tuples = xs.map(m => s"(${m("row")}, ${m("column")})").mkString(", ")
        s"""Set($tuples)"""
      case _ => throw new IllegalArgumentException
    }
  }

  def fromLabeledTest(argNames: String*): ToTestCaseData =
    withLabeledTest { sut =>
      labeledTest =>
        val args = sutArgs(labeledTest.result, argNames: _*)
        val property = labeledTest.property
        val sutCall =
          s"""Matrix($args).$property"""
        val expected = toString(labeledTest.expected)
        TestCaseData(labeledTest.description, sutCall, expected)
    }

  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/saddle-points.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest("input"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
