import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object TriangleTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/triangle.json")

    def mapArgToString(arg: Any): String = {
      arg match {
        case vals: List[_] => vals.mkString(", ")
        case _ => throw new IllegalStateException
      }
    }

    def sutArgs(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
          argNames map (name => mapArgToString(parseResult(name))) mkString

    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgs(labeledTest.result, argNames: _*)
          val func = labeledTest.property.mkString
          val sutCall = s"""Triangle(${args}).$func"""
          val expected =
            labeledTest.expected.fold(Function.const("true"), x => s"$x")
          TestCaseData(labeledTest.property + " - " + labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file,
        fromLabeledTest("sides"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
