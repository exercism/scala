package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object PhoneNumberTestGenerator:
  def fromLabeledTest(argNames: String*): ToTestCaseData =
    withLabeledTest { sut => labeledTest =>
      val args     = sutArgsFromInput(labeledTest.result, argNames*)
      val property = labeledTest.property
      val sutCall  =
        s"""PhoneNumber.$property($args)"""
      val expected = toString(labeledTest.expected)
      TestCaseData(labeledTest.description, sutCall, expected)
    }

  def toString(expected: CanonicalDataParser.Expected): String =
    expected match
      case Left(_)     => "None"
      case Right(null) => "None"
      case Right(str)  => s"""Some("$str")"""

  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/phone-number.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest("phrase"))
    println(s"-------------")
    println(code)
    println(s"-------------")
