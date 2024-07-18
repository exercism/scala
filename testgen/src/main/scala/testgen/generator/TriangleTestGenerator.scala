package testgen
package generator

import java.io.File
import scala.language.postfixOps

import TestSuiteBuilder.{toString, *}

object TriangleTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/triangle.json")

    def mapArgToString(arg: Any): String =
      arg match
        case vals: List[?] => vals.mkString(", ")
        case _             => throw new IllegalStateException

    def sutArgsFromInput(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
      argNames map (name => mapArgToString(parseResult("input").asInstanceOf[Map[String, Any]](name))) mkString

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut => labeledTest =>
        val args     = sutArgsFromInput(labeledTest.result, argNames*)
        val func     = labeledTest.property.mkString
        val sutCall  = s"""Triangle(${args}).$func"""
        val expected =
          labeledTest.expected.fold(Function.const("true"), x => s"$x")
        TestCaseData(labeledTest.property + " - " + labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("sides"))
    println(s"-------------")
    println(code)
    println(s"-------------")
