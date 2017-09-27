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

    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val diagram = labeledTest.result("diagram")
          val student = labeledTest.result("student")
          val students: List[String] = (labeledTest.result getOrElse("students", List())).asInstanceOf[List[String]]
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

    // TODO: Move this escape up to TestSuiteBuilder
    def escape(raw: String): String = {
      import scala.reflect.runtime.universe._
      Literal(Constant(raw)).toString
    }

    val code =
      TestSuiteBuilder.build(file, fromLabeledTest("diagram", "student"))

    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
