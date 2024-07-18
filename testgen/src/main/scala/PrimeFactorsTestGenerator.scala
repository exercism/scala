import java.io.File

import testgen.TestSuiteBuilder.{toString, _}
import testgen.{CanonicalDataParser, TestCaseData, TestSuiteBuilder}

object PrimeFactorsTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/prime-factors.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("value"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }

  private def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
    withLabeledTest { sut =>
      labeledTest =>
        val sutFunction = labeledTest.property
        val args = sutArgsFromInput(labeledTest.result, argNames*)
        val sutCall = s"$sut.$sutFunction($args)"
        val expected = toString(labeledTest.expected)

        TestCaseData(labeledTest.description, sutCall, expected)
    }

  def sutArgsFromInput(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
    argNames map (name => toString(parseResult("input").asInstanceOf[Map[String, Any]](name))) mkString ", "

  def toString(expected: CanonicalDataParser.Expected): String = {
    expected match {
      case Left(_) => throw new IllegalStateException("Unexpected Left")
      case Right(exp) => s"${toString(exp)}"
    }
  }

  private def toString(any: Any): String = {
    def quote(str: String): String =
      if ("\"\n" exists (str.contains(_:Char))) "\"\"\"" else "\""

    any match {
      case list: List[_] =>
        val vals = list.map(s => toString(s)).mkString(", ")
        s"List($vals)"
      case str: String =>
        val quot = quote(str)
        s"$quot$str$quot"
      case dbl: Double =>
        dbl.asInstanceOf[Long].toString + "l"
      case _ => any.toString
    }
  }
}