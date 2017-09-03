import testgen._
import TestSuiteBuilder._
import java.io.File

object SumOfMultiplesTestGenerator {
  def toArgString(any: Any): String = {
    any match {
      case list: List[_] =>
        val vals = list.map(s => TestSuiteBuilder.toString(s)).mkString(", ")
        s"Set($vals)"
      case lstr: Long =>
        lstr.toString + "l"
      case _ => any.toString
    }
  }

  def sutArgs(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
    argNames map (name => toArgString(parseResult(name))) mkString ", "

  def toString(expected: CanonicalDataParser.Expected): String = {
    expected match {
      case Left(_) => throw new IllegalStateException()
      case Right(n) => s"$n"
    }
  }

  def fromLabeledTest(argNames: String*): ToTestCaseData =
    withLabeledTest { sut =>
      labeledTest =>
        val args = sutArgs(labeledTest.result, argNames: _*)
        val property = labeledTest.property
        val sutCall =
          s"$sut.$property($args)"
        val expected = toString(labeledTest.expected)
        TestCaseData(labeledTest.description, sutCall, expected)
    }

  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/sum-of-multiples.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest("factors", "limit"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
