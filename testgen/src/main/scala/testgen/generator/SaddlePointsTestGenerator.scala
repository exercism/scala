package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object SaddlePointsTestGenerator:
  def toString(expected: CanonicalDataParser.Expected): String =
    expected match
      case Right(xs: List[Map[String, Int]]) =>
        val tuples = xs.map(m => s"(${m("row")}, ${m("column")})").mkString(", ")
        s"""Set($tuples)"""
      case _                                 => throw new IllegalArgumentException

  def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
    withLabeledTest { sut => labeledTest =>
      val args     = sutArgsFromInput(labeledTest.result, argNames*)
      val property = labeledTest.property
      val sutCall  =
        s"""Matrix($args).$property"""
      val expected = toString(labeledTest.expected)
      TestCaseData(labeledTest.description, sutCall, expected)
    }

  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/saddle-points.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("matrix"))
    println(s"-------------")
    println(code)
    println(s"-------------")
