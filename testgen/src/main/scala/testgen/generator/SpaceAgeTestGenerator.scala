package testgen
package generator

import java.io.File

import CanonicalDataParser.ParseResult
import TestSuiteBuilder.{toString, *}

object SpaceAgeTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/space-age.json")

    def toString(expected: CanonicalDataParser.Expected): String =
      expected match
        case Left(_)  => throw new IllegalStateException()
        case Right(n) => s"$n"

    def sutArgsFromInput(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
      argNames map (name => TestSuiteBuilder.toString(fromInputMap(parseResult, name))) mkString ", "

    def functionName(parseResult: CanonicalDataParser.ParseResult): String =
      "on" + fromInputMap(parseResult, "planet").toString

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut => labeledTest =>
        val args     = sutArgsFromInput(labeledTest.result, argNames*)
        val sutCall  =
          s"""$sut.${functionName(labeledTest.result)}($args)"""
        val expected = toString(labeledTest.expected) + " +- 0.01"
        TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("seconds"))
    println(s"-------------")
    println(code)
    println(s"-------------")
