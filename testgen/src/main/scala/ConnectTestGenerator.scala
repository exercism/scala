import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object ConnectTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/connect.json")

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Right("") => "None"
        case Right("X") => s"""Some(Color.Black)"""
        case Right("O") => s"""Some(Color.White)"""
        case _ => throw new IllegalArgumentException
      }
    }

    def sutArgs(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
      argNames map (name => toArgString(parseResult(name))) mkString ", "

    def toArgString(any: Any): String = {
      any match {
        case list: List[_] =>
          val vals = list.map(s => TestSuiteBuilder.toString(s)).mkString(", ")
          s"mkBoard(List($vals))"
        case _ => any.toString
      }
    }

    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgs(labeledTest.result, argNames: _*)
          val property = labeledTest.property
          val sutCall =
            s"""$sut($args).$property"""
          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTest("board"),
      Seq(),
      Seq("// Filter readable board into valid input",
          "private def mkBoard(lines: List[String]): List[String] =",
          "lines.map(l => l.filter(!_.isSpaceChar))"))

    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
