package testgen
package generator

import java.io.File

import TestSuiteBuilder.{ToTestCaseData, withLabeledTest}

object TwoferTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/two-fer.json")

    def sutArgsFromInput(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
      argNames map (name => toArgString(parseResult("input").asInstanceOf[Map[String, Any]](name))) mkString ", "

    def toArgString(any: Any): String =
      any match
        case null => ""
        case _    => s""""${any.toString}""""

    def toExpectedString(expected: CanonicalDataParser.Expected): String =
      expected match
        case Left(_)          => throw new IllegalStateException()
        case Right(s: String) => s""""$s""""

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut => labeledTest =>
        val args     = sutArgsFromInput(labeledTest.result, argNames*)
        val property = labeledTest.property
        val sutCall  = s"""Twofer.twofer($args)"""
        val expected = toExpectedString(labeledTest.expected)
        TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("name"))

    println(s"-------------")
    println(code)
    println(s"-------------")
