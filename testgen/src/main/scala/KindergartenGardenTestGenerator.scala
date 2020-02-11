import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object KindergartenGardenTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/kindergarten-garden.json")

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Right(xs: List[String]) => s"List(${xs.map(x => "Plant." + x.capitalize).mkString(", ")})"
        case _ => throw new IllegalArgumentException
      }
    }

    def fromLabeledTestFromInput(): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = labeledTest.result("input").asInstanceOf[Map[String, Any]]
          val diagram = args("diagram")
          val student = args("student")
          val students: List[String] = (args getOrElse("students", List())).asInstanceOf[List[String]]
          val property = labeledTest.property
          val sutCall =
            if (students.isEmpty)
              s"""Garden.defaultGarden(${escape(diagram.toString)}).$property("$student")"""
            else
              s"""Garden(List(${students.map(s => escape(s)).mkString(", ")}), ${escape(diagram.toString)}).$property(${escape(student.toString)})"""

          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.parentDescriptions.mkString(", ") + " - " + labeledTest.description,
            sutCall, expected)
      }

    val code =
      TestSuiteBuilder.build(file, fromLabeledTestFromInput())

    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
