import java.io.File

import PrimeFactorsTestGenerator.toString
import testgen.TestSuiteBuilder.{sutArgs, _}
import testgen._

object SayTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/say.json")

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Left(_) => "None"
        case Right(-1) => "None"
        case Right(n)  => s"""Some("$n")"""
      }
    }

    def sutArgs(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
      argNames map (name => toArgString(parseResult(name))) mkString ", "

    def toArgString(any: Any): String = {
      any match {
        case lstr: Long =>
          lstr.toString + "l"
        case istr: Integer =>
          istr.toString
        case dstr: Double =>
          dstr.toLong + "l"
        case _ => any.toString
      }
    }

    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgs(labeledTest.result, argNames: _*)
          val property = labeledTest.property
          val sutCall =
            s"""$sut.inEnglish($args)"""
          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTest("input"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
