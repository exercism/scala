import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object HouseTestGenerator {
  def sutArgs(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String = {
    val startVerse = parseResult.get("start verse")
    val endVerse = parseResult.get("end verse")
    val verse = parseResult.get("verse")

    startVerse match {
      case Some(s) => s.toString + endVerse.map(s => ", " + s.toString).getOrElse("")
      case _ => verse match {
        case Some(s) => s.toString
        case _ => throw new IllegalStateException("Missing params")
      }
    }
  }


  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/house.json")

    val RawQuote = "\"\"\""
    def asRawString(str: String): String = s"$RawQuote$str$RawQuote"

    def fromLabeledTest(argNames: String*)(
      implicit sutFunction: LabeledTest => String = _.property): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val sutFunction = labeledTest.property
          val args = sutArgs(labeledTest.result, argNames: _*)
          val sutCall = s"$sut.$sutFunction($args)"
          val expectedLines = labeledTest.expected.right.get.asInstanceOf[List[String]]
          val expected = expectedLines mkString ("", "\n", "\n\n")

          TestCaseData(labeledTest.description, sutCall, asRawString(expected))
      }

    val code = TestSuiteBuilder.build(file,
      fromLabeledTest("verse", "start verse", "end verse"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
