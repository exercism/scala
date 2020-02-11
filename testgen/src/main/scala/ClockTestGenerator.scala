import java.io.File

import testgen.TestSuiteBuilder.{toString, _}
import testgen._

object ClockTestGenerator {
  private def toString(expected: CanonicalDataParser.Expected): String =
    expected.fold(_ => "", TestSuiteBuilder.toString)

  private def expectedToClockStr(expected: CanonicalDataParser.Expected): String =
    expected.fold(_ => "", {
      case str: String => str.split(':').map(_.toInt).mkString(", ")
      case _ => throw new IllegalArgumentException
    })

  private def toOperation(function: String): String =
    if (function.equalsIgnoreCase("subtract")) "-" else "+"

  def mapArgToStringForEqual(arg: Any): String = {
    arg match {
      case vals: Map[String, String] => s"${vals("hour")}, ${vals("minute")}"
      case _ => throw new IllegalArgumentException
    }
  }

  def sutArgsForEqual(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
    argNames map (name => mapArgToStringForEqual(parseResult("input").asInstanceOf[Map[String, Any]](name))) mkString ", "

  def fromLabeledTestAltFromInput(propArgs: (String, Seq[String])*): ToTestCaseData =
    withLabeledTest { sut => labeledTest =>
      val sutFunction = labeledTest.property
      val args = sutArgsAltFromInput(labeledTest.result, propArgs:_*)

      val sutFunc = sutFunction.toString
      val sutCall =
        if (sutFunc == "create")
          s"$sut($args)"
        else if (sutFunc == "add" || sutFunc == "subtract") {
          val constructorArgs = sutArgsFromInput(labeledTest.result, "hour", "minute")
          val functionArgs = sutArgsFromInput(labeledTest.result, "value")
          s"$sut($constructorArgs) ${toOperation(sutFunc)} $sut(${functionArgs.stripPrefix("-")})"
        } else {
          val argsForEqual1 = sutArgsForEqual(labeledTest.result, "clock1")
          val argsForEqual2 = sutArgsForEqual(labeledTest.result, "clock2")
          s"$sut($argsForEqual1) == $sut($argsForEqual2)"
        }

      val expected =
        if (sutFunc == "create" || sutFunc == "add" || sutFunc == "subtract")
          s"$sut(${expectedToClockStr(labeledTest.expected)})"
        else
          toString(labeledTest.expected)

      TestCaseData(s"${labeledTest.description}", sutCall, expected)
    }

  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/clock.json")

    val code = TestSuiteBuilder.build(file,
      fromLabeledTestAltFromInput("create" -> Seq("hour", "minute"),
        "add" -> Seq("hour", "minute", "value"),
        "subtract" -> Seq("hour", "minute", "value"),
        "equal" -> Seq("clock1")))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}