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
        case Left(_) => "None"
        case Right(-1) => "None"
        case Right(n) => s"Some($n)"
      }
    }

    def getYear(labeledTest: LabeledTest): Map[String, String] = {
      labeledTest.result("queen").
        asInstanceOf[Map[String, String]]
    }

    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val month = labeledTest.result("month")
          val year = labeledTest.result("year")
          val dayOfWeek = labeledTest.result("dayofweek").toString.take(3)
          val week = labeledTest.result("week").toString.capitalize
          val dayOfMonth = labeledTest.result("dayofmonth")
          val sutCall =
            s"""$sut($month, $year).day(Meetup.$dayOfWeek, Schedule.$week)"""
          val expected = s"LocalDate.of($year, $month, $dayOfMonth)"
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code =
      TestSuiteBuilder.build(file,
        fromLabeledTest("year", "month", "week", "dayofweek", "dayofmonth"),
        Seq("java.time.LocalDate"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
