import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object GrainsTestGenerator {
  def main(args: Array[String]): Unit = {

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Left(_) => "None"
        case Right(-1) => "None"
        case Right(n: BigDecimal) => if (n > Int.MaxValue) s"""Some(BigInt(\"$n\"))""" else s"Some($n)"
        case _ => "None"
      }
    }

    def toStringForTotal(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Right(n: BigDecimal) => s"""BigInt(\"$n\")"""
        case _ => throw new IllegalStateException
      }
    }

    val file = new File("src/main/resources/grains.json")
    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = if (!labeledTest.result.contains("input"))
              ""
            else
              sutArgs(labeledTest.result, argNames: _*)
          val property = labeledTest.property.mkString
          val sutCall = if (args.length > 0)
              s"""Grains.$property($args)"""
            else
              s"""Grains.$property"""
          val expected = if (args.length > 0)
              toString(labeledTest.expected)
            else
              toStringForTotal(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTest("input"))
    println(s"‐‐‐‐‐‐‐‐‐‐‐‐‐")
    println(code)
    println(s"‐‐‐‐‐‐‐‐‐‐‐‐‐")
  }
}
