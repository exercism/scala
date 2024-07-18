package testgen
package generator

import java.io.File

import TestSuiteBuilder.{toString, *}

object RunLengthEncodingTestGenerator:
  private def toString(expected: CanonicalDataParser.Expected): String =
    expected.fold(_ => "", TestSuiteBuilder.toString)

  def fromLabeledTestAlt(propArgs: (String, Seq[String])*): ToTestCaseData =
    withLabeledTest { sut => labeledTest =>
      val sutFunction = labeledTest.property
      val args        = sutArgsAlt(labeledTest.result, propArgs*)
      val sutCall     =
        if sutFunction.toString == "consistency" then s"$sut.decode($sut.encode($args))"
        else s"$sut.$sutFunction($args)"
      val expected    = toString(labeledTest.expected)

      TestCaseData(s"$sutFunction - ${labeledTest.description}", sutCall, expected)
    }

  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/run-length-encoding.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestAlt("encode" -> Seq("input"), "decode" -> Seq("input"), "consistency" -> Seq("input")))
    println(s"-------------")
    println(code)
    println(s"-------------")
