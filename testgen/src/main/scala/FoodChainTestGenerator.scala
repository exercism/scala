import testgen._
import TestSuiteBuilder.{toString, _}
import java.io.File

object FoodChainTestGenerator {
  def sutArgsFromInput(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String = {
    val input = parseResult("input").asInstanceOf[Map[String, Int]]

    val firstParam = input("startVerse").toString
    val secondParam = input("endVerse").toString

    firstParam + ", " + secondParam
  }

  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/food-chain.json")

    val RawQuote = "\"\"\""
    def asRawString(str: String): String = s"$RawQuote$str$RawQuote"

    def fromLabeledTestFromInput(argNames: String*)(
      implicit sutFunction: LabeledTest => String = _.property): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val sutFunction = labeledTest.property
          val args = sutArgsFromInput(labeledTest.result, argNames*)
          val sutCall = s"$sut.$sutFunction(${args})"
          val expectedLines = labeledTest.expected.getOrElse(List()).asInstanceOf[List[String]]
          val expected = expectedLines mkString ("", "\n", "\n\n")

          TestCaseData(labeledTest.description, sutCall, asRawString(expected))
      }

    val code = TestSuiteBuilder.build(file,
      fromLabeledTestFromInput("startVerse", "endVerse"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
