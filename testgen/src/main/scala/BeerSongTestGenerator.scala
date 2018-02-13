import testgen._
import TestSuiteBuilder._
import java.io.File

object BeerSongTestGenerator {


  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/beer-song.json")

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Right(xs: List[String]) => s"""\"${xs.mkString("\\n")}\\n\""""
        case _ => throw new IllegalArgumentException()
      }
    }

    def fromLabeledTestFromInput(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgsFromInput(labeledTest.result, argNames: _*)
          val property = labeledTest.property
          val sutCall =
            s"""$sut.$property($args)"""
          val expected = toString(labeledTest.expected)
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code = TestSuiteBuilder.build(file,
      fromLabeledTestFromInput("startBottles", "takeDown"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
