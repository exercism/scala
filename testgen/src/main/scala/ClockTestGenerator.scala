import java.io.File

import testgen.TestSuiteBuilder.{toString, _}
import testgen._

object ClockTestGenerator {
  private def toString(expected: CanonicalDataParser.Expected): String =
    expected.fold(_ => "", TestSuiteBuilder.toString)

  private def expectedToClockStr(expected: CanonicalDataParser.Expected): String =
    expected.fold(_ => "", any => any match {
      case str: String => str.split(':').map(_.toInt).mkString(", ")
      case _ => throw new IllegalArgumentException
    })

  private def toOperation(addArg: String): String =
    if (addArg.startsWith("-")) "-" else "+"

  def mapArgToStringForEqual(arg: Any): String = {
    arg match {
      case vals: Map[String, String] => s"${vals("hour")}, ${vals("minute")}"
      case _ => throw new IllegalArgumentException
    }
  }

  def sutArgsForEqual(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
    argNames map (name => mapArgToStringForEqual(parseResult(name))) mkString

  def fromLabeledTestAlt(propArgs: (String, Seq[String])*): ToTestCaseData =
    withLabeledTest { sut => labeledTest =>
      val sutFunction = labeledTest.property
      val args = sutArgsAlt(labeledTest.result, propArgs:_*)

      val sutCall =
        if (sutFunction.toString == "create")
          s"$sut($args)"
        else if (sutFunction.toString == "add") {
          val addArgs = sutArgs(labeledTest.result, "add")
          s"$sut($args) ${toOperation(addArgs)} $sut(${addArgs.stripPrefix("-")})"
        } else {
          val argsForEqual1 = sutArgsForEqual(labeledTest.result, "clock1")
          val argsForEqual2 = sutArgsForEqual(labeledTest.result, "clock2")
          s"$sut($argsForEqual1) == $sut($argsForEqual2)"
        }

      val expected =
        if (sutFunction.toString == "create" || sutFunction.toString == "add")
          s"$sut(${expectedToClockStr(labeledTest.expected)})"
        else
          toString(labeledTest.expected)

      TestCaseData(s"${labeledTest.description}", sutCall, expected)
    }

  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/clock.json")

    val code = TestSuiteBuilder.build(file,
      fromLabeledTestAlt("create" -> Seq("hour", "minute"),
        "add" -> Seq("hour", "minute"),
        "equal" -> Seq("clock1")))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}