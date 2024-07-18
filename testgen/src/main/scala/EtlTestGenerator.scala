import java.io.File

import testgen.TestSuiteBuilder.{toString, _}
import testgen._

object EtlTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/etl.json")

    def seqToString(seq: Seq[String]): String = s"Seq(${seq.map(s => "\"" + s + "\"").mkString(", ")})"

    def mapArgToString(arg: Map[String, List[String]]): String = {
      s"Map(${arg.toSeq
        .sortBy(_._1.toInt)
        .map{case (key, xs) => key.toString + " -> " + seqToString(xs)}
        .mkString(",\n")})"
    }

    def mapExpectedToString(arg: Map[String, Int]): String = {
      s"Map(${arg.toSeq
        .sortBy(_._1)
        .map{case (key, value) => "\"" + key.toString + "\" -> " + value.toString}
        .mkString(",\n")})"
    }

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Left(_) => ""
        case Right(m) => mapExpectedToString(m.asInstanceOf[Map[String, Int]])
      }
    }

    def sutArgs(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
      argNames map (name => mapArgToString(parseResult(name).asInstanceOf[Map[String, List[String]]])) mkString(", ")

    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgs(labeledTest.result, argNames*)
          val property = labeledTest.property
          val sutCall =
            s"""Etl.$property($args)"""
          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file, fromLabeledTest("input"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
