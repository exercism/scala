import java.io.File

import testgen.CanonicalDataParser.{Expected, ParseResult, getRequired}
import testgen.TestSuiteBuilder._
import testgen._

import scala.util.Try

object MeetupTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/meetup.json")

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Right(s: String) => val tokens = s.split("-")
          s"LocalDate.of(${tokens(0)}, ${trimLeadingZero(tokens(1))}," +
            s" ${trimLeadingZero(tokens(2))})"
        case _ => throw new IllegalArgumentException
      }
    }

    def trimLeadingZero(s: String) = s.replaceFirst("^0+(?!$)", "")

    def getYear(labeledTest: LabeledTest): Map[String, String] = {
      labeledTest.result("queen").
        asInstanceOf[Map[String, String]]
    }

    def fromLabeledTestFromInput(): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = labeledTest.result("input").asInstanceOf[Map[String, Any]]
          val month = args("month")
          val year = args("year")
          val dayOfWeek = args("dayofweek").toString.take(3)
          val week = args("week").toString.capitalize
          val sutCall =
            s"""$sut($month, $year).day(Meetup.$dayOfWeek, Schedule.$week)"""
          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code =
      TestSuiteBuilder.build(file,
        fromLabeledTestFromInput(),
        Seq("java.time.LocalDate"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
