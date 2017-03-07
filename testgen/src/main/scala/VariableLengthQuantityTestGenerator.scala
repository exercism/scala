import play.api.libs.json.Json

import scala.io.Source

// Generates test suite from json test definition for the VariableLengthQuantity exercise.
class VariableLengthQuantityTestGenerator {
  implicit val vlqTestCaseReader = Json.reads[VlqTestCase]

  private val filename = "variable-length-quantity.json"
  private val fileContents = Source.fromFile(filename).getLines.mkString
  private val json = Json.parse(fileContents)

  def write {
    val testBuilder = new TestBuilder("VariableLengthQuantityTest")
    addEncodeTests(testBuilder)
    addDecodeTests(testBuilder)
    testBuilder.toFile
  }

  private def addEncodeTests(testBuilder: TestBuilder): Unit = {
    val description = (json \ "encode" \ "description").get.as[List[String]].mkString(" ")
    val encodeTestCases = (json \ "encode" \ "cases").validate[List[VlqTestCase]].asEither match {
      case Left(l) => List()
      case Right(r) => r
    }

    implicit def testCaseToGen(tc: VlqTestCase): TestCaseGen = {
      val callSUT = s"""VariableLengthQuantity.encode(${listToStr(tc.input)})"""
      val expected = listToStr(tc.expected)
      val result = s"val encoded = $callSUT"
      val checkResult = s"""encoded should be ($expected)"""

      TestCaseGen("encode: " + tc.description, callSUT, expected, result, checkResult)
    }

    testBuilder.addTestCases(encodeTestCases, Some(description))
  }

  private def addDecodeTests(testBuilder: TestBuilder): Unit = {
    val description = (json \ "decode" \ "description").get.as[List[String]].mkString(" ")
    val decodeTestCases = (json \ "decode" \ "cases").validate[List[VlqTestCase]].asEither match {
      case Left(l) => List()
      case Right(r) => r
    }

    implicit def testCaseToGen(tc: VlqTestCase): TestCaseGen = {
      val input = listToStr(tc.input)
      val callSUT = s"""VariableLengthQuantity.decode($input)"""
      val checkResult = tc.expected match {
        case None => "decoded.isLeft should be (true)"
        case Some(arr) => s"decoded should be (Right(${listToStr(arr)}))"
      }
      val result = s"val decoded = $callSUT"

      TestCaseGen("decode: " + tc.description, callSUT, "", result, checkResult)
    }

    testBuilder.addTestCases(decodeTestCases, Some(description))
  }

  private def listToStr(arr: Option[List[Long]]): String = {
    arr match {
      case None => "List[Int]()"
      case Some(x) => listToStr(x)
    }
  }

  private def listToStr(arr: List[Long]): String = {
    val elements = arr.map(n => s"0x${n.toHexString}").mkString(", ")
    s"List($elements)"
  }
}

case class VlqTestCase(description: String, input: List[Long], expected: Option[List[Long]])

object VariableLengthQuantityTestGenerator {
  def main(args: Array[String]): Unit = {
    new VariableLengthQuantityTestGenerator().write
  }
}
