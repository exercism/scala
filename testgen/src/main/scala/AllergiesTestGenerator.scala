import java.io.File

import testgen.TestSuiteBuilder.{fromLabeledTestFromInput, _}
import testgen._

object AllergiesTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/allergies.json")

    def toAlergenEnum(s: String): String =
      "Allergen." + s.toLowerCase.capitalize


    def toAllergicToExpected(expected: CanonicalDataParser.Expected): List[(String, Boolean)] = {
      expected match {
        case Right(xs: List[Map[String, Any]]) =>
          xs.map(f => (toAlergenEnum(f("substance").asInstanceOf[String]),
            f("result").asInstanceOf[Boolean]))
        case _ => throw new IllegalStateException
      }
    }

    def toListExpected(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Right(xs: List[String]) => s"List(${xs.map(toAlergenEnum).mkString(", ")})"
        case _ => throw new IllegalStateException
      }
    }

    def sutArgs(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
      argNames map (name => TestSuiteBuilder.toString(parseResult(name))) mkString ", "

    def getScore(labeledTest: LabeledTest): Int =
      labeledTest.result("input").asInstanceOf[Map[String, Any]]("score").asInstanceOf[Int]

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseDataList =
      withLabeledList { sut =>
        labeledTest =>
          val score = getScore(labeledTest)
          val property = labeledTest.property
          if ("allergicTo".equals(property)) {
            val expected: List[(String, Boolean)] = toAllergicToExpected(labeledTest.expected)
            expected.map(e => {
              val sutCall =
                s"""$sut.$property(${e._1}, $score)"""
              val result = e._2.toString
              TestCaseData(s"${e._1} - ${labeledTest.description}", sutCall, result)
            })
          } else {
            val args = sutArgsFromInput(labeledTest.result, "score")
            val expected = toListExpected(labeledTest.expected)
            val sutCall =
              s"""$sut.$property($args)"""
            List(TestCaseData(labeledTest.description, sutCall, expected))
          }
      }

    val code = TestSuiteBuilder.buildFromList(file, fromLabeledTestFromInput("score"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
