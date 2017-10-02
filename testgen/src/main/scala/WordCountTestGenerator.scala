import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object WordCountTestGenerator {
  def toString(expected: CanonicalDataParser.Expected): String = {
    expected match {
      case Right(m: Map[String, Int]) =>
        s"""Map(${m.map{case (s, i) => s"""("$s", $i)"""}.mkString(", ")})"""
      case _ => throw new IllegalArgumentException
    }
  }

  def fromLabeledTest(argNames: String*): ToTestCaseData =
    withLabeledTest { sut =>
      labeledTest =>
        val args = escape(labeledTest.result("input").toString)
        val property = labeledTest.property
        val sutCall =
          s"""$sut($args).$property"""
        val expected = toString(labeledTest.expected)
        TestCaseData(labeledTest.description, sutCall, expected)
    }

  def escape(raw: String): String = {
    import scala.reflect.runtime.universe._
    Literal(Constant(raw)).toString
  }

  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/word-count.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest("input"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
