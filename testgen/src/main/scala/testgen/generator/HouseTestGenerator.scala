package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object HouseTestGenerator:
  def sutArgsFromInput(parseResult: CanonicalDataParser.ParseResult): String =
    val input = parseResult("input").asInstanceOf[Map[String, Int]]

    val firstParam  = input("startVerse").toString
    val secondParam = input("endVerse").toString

    firstParam + ", " + secondParam

  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/house.json")

    val RawQuote                         = "\"\"\""
    def asRawString(str: String): String = s"$RawQuote$str$RawQuote"

    def fromLabeledTestFromInput(argNames: String*)(implicit sutFunction: LabeledTest => String = _.property): ToTestCaseData =
      withLabeledTest { sut => labeledTest =>
        val sutFunction   = labeledTest.property
        val args          = sutArgsFromInput(labeledTest.result)
        val sutCall       = s"$sut.$sutFunction($args)"
        val expectedLines = labeledTest.expected.right.get.asInstanceOf[List[String]]
        val expected      = expectedLines mkString ("", "\n", "\n\n")

        TestCaseData(labeledTest.description, sutCall, asRawString(expected))
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput())
    println(s"-------------")
    println(code)
    println(s"-------------")
