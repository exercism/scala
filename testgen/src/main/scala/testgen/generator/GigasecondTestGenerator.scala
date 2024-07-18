package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object GigasecondTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/gigasecond.json")

    def toString(expected: CanonicalDataParser.Expected): String =
      expected match
        case Left(_)     => throw new IllegalStateException
        case Right(null) => throw new IllegalStateException
        case Right(n)    => s""""$n""""

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut => labeledTest =>
        val args     = sutArgsFromInput(labeledTest.result, argNames*)
        val property = labeledTest.property
        val expected = toString(labeledTest.expected)

        val useDateTime = labeledTest
          .result("input")
          .asInstanceOf[Map[String, Any]]("birthdate")
          .asInstanceOf[String]
          .length > "xxxx-xx-xx".length
        val sutCall     =
          if useDateTime then s"""val input = dateTime($args)
    val expected = dateTime($expected)
    $sut.$property(input)"""
          else s"""val input = date($args)
    val expected = dateTime($expected)
    $sut.$property(input)"""

        TestCaseData(labeledTest.description, sutCall, "expected")
      }

    val dateTimeFn = "private def dateTime(str: String): LocalDateTime =\n    LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(str))"
    val dateFn     = "private def date(str: String): LocalDate =\n    LocalDate.from(DateTimeFormatter.ISO_DATE.parse(str))"
    val code       = TestSuiteBuilder.build(
      file,
      fromLabeledTestFromInput("birthdate"),
      Seq("java.time.LocalDate", "java.time.LocalDateTime", "java.time.format.DateTimeFormatter"),
      Seq(dateTimeFn, dateFn),
    )
    println(s"-------------")
    println(code)
    println(s"-------------")
