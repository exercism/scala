import testgen._
import TestSuiteBuilder.{toString, _}
import java.io.File

object FoodChainTestGenerator {
  def sutArgs(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String = {
    val firstParam = parseResult.get("start verse") match {
      case Some(s) => s.toString
      case _ => throw new IllegalArgumentException("Missing start verse")
    }
    val secondParam = parseResult.get("end verse")

    firstParam + secondParam.map(s => ", " + s.toString).getOrElse("")
  }


  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/food-chain.json")

    val RawQuote = "\"\"\""
    def asRawString(str: String): String = s"$RawQuote$str$RawQuote"

    def fromLabeledTest(argNames: String*)(
      implicit sutFunction: LabeledTest => String = _.property): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val sutFunction = labeledTest.property
          val args = sutArgs(labeledTest.result, argNames: _*)
          val sutCall = s"$sut.$sutFunction(${args})"
          val expectedLines = labeledTest.expected.right.get.asInstanceOf[List[String]]
          val expected = expectedLines mkString ("", "\n", "\n\n")

          TestCaseData(labeledTest.description, sutCall, asRawString(expected))
      }

    val code = TestSuiteBuilder.build(file,
      fromLabeledTest("start verse", "end verse"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
