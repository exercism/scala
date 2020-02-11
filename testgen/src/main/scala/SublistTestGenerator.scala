import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object SublistTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/sublist.json")

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Right("equal") => s"Sublist.Equal"
        case Right("sublist") => s"Sublist.Sublist"
        case Right("superlist") => s"Sublist.Superlist"
        case Right("unequal") => s"Sublist.Unequal"
        case _ => throw new IllegalStateException()
      }
    }

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgsFromInput(labeledTest.result, argNames: _*)
          val property = labeledTest.property
          val sutCall =
            s"$sut.$property($args)"
          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code =
      TestSuiteBuilder.build(file, fromLabeledTestFromInput("listOne", "listTwo"))

    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
