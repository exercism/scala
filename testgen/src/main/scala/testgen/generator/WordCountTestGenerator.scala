package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object WordCountTestGenerator:
  def toString(expected: CanonicalDataParser.Expected): String =
    expected match
      case Right(m: Map[String, Int]) =>
        s"""Map(${m.map { case (s, i) => s"""("$s", $i)""" }.mkString(", ")})"""
      case _                          => throw new IllegalArgumentException

  def fromLabeledTestFromInput(): ToTestCaseData =
    withLabeledTest { sut => labeledTest =>
      val args     = labeledTest.result("input").asInstanceOf[Map[String, Any]]
      val sentence = escape(args("sentence").toString)
      val property = labeledTest.property
      val sutCall  =
        s"""$sut($sentence).$property"""
      val expected = toString(labeledTest.expected)
      TestCaseData(labeledTest.description, sutCall, expected)
    }

  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/word-count.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput())
    println(s"-------------")
    println(code)
    println(s"-------------")
