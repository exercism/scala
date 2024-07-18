package testgen
package generator

import java.io.File

import TestSuiteBuilder.{toString, *}

object AllYourBaseTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/all-your-base.json")

    def toString(expected: CanonicalDataParser.Expected): String =
      expected match
        case Left(_)     => "None"
        case Right(null) => "None"
        case Right(n)    => s"Some($n)"

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut => labeledTest =>
        val args     = sutArgsFromInput(labeledTest.result, argNames*)
        val property = labeledTest.property
        val sutCall  =
          s"""AllYourBase.$property($args)"""
        val expected = toString(labeledTest.expected)
        TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code =
      TestSuiteBuilder.build(file, fromLabeledTestFromInput("inputBase", "digits", "outputBase"))
    println(s"-------------")
    println(code)
    println(s"-------------")
