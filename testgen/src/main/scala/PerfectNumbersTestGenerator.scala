import java.io.File

import testgen.TestSuiteBuilder.{toString, _}
import testgen._

object PerfectNumbersTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/perfect-numbers.json")

    def toEnumStr(str: String): String = {
      str match {
        case "perfect" => "NumberType.Perfect"
        case "abundant" => "NumberType.Abundant"
        case "deficient" => "NumberType.Deficient"
        case _ => throw new IllegalStateException("Invalid NumberType -" + str)
      }
    }

    def toString(expected: CanonicalDataParser.Expected): String = {
        expected match {
          case Left(error) => s"Left(${TestSuiteBuilder.toString(error)})"
          case Right(exp) => s"Right(${toEnumStr(exp.toString)})"
        }
    }

    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgs(labeledTest.result, argNames: _*)
          val property = labeledTest.property.mkString
          val sutCall =
            s"""PerfectNumbers.$property($args)"""
          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTest("input"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
