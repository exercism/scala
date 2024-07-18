import java.io.File

import testgen.TestSuiteBuilder.{toString, _}
import testgen._

object AlphameticsTestGenerator {

  def mapExpectedToString(arg: Map[String, Int]): String = {
    s"Map(${arg.toSeq
      .sortBy(_._1)
      .map{case (key, value) => "\'" + key.toString + "\' -> " + value.toString}
      .mkString(",\n")})"
  }

  def toString(expected: CanonicalDataParser.Expected): String = {
    expected match {
      case Left(_) => "None"
      case Right(null) => "None"
      case Right(n) => s"Some(${mapExpectedToString(n.asInstanceOf[Map[String, Int]])})"
    }
  }

  def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
    withLabeledTest { sut =>
      labeledTest =>
        val args = sutArgsFromInput(labeledTest.result, argNames*)
        val property = labeledTest.property
        val sutCall =
          s"""$sut.$property($args)"""
        val expected = toString(labeledTest.expected)
        TestCaseData(labeledTest.description, sutCall, expected)
    }

  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/alphametics.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("puzzle"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
