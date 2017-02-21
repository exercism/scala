import play.api.libs.json.Json

import scala.io.Source

// Generates test suite from json test definition for the RailFenceCipher exercise.
class RailFenceCipherTestGenerator {
  implicit val railFenceTestCaseReader = Json.reads[RailFenceTestCase]

  private val filename = "rail-fence.json"
  private val fileContents = Source.fromFile(filename).getLines.mkString
  private val json = Json.parse(fileContents)

  def write {
    val testBuilder = new TestBuilder("RailFenceCipherTest")
    addEncodeTests(testBuilder)
    addDecodeTests(testBuilder)
    testBuilder.toFile
  }

  private def addEncodeTests(testBuilder: TestBuilder): Unit = {
    val description = "Encode test cases"
    val encodeTestCases = (json \ "encode" \ "cases").get.as[List[RailFenceTestCase]]

    implicit def testCaseToGen(tc: RailFenceTestCase): TestCaseGen = {
      val callSUT = s"""RailFenceCipher.encode("${tc.msg}", ${tc.rails})"""
      val expected = tc.expected
      val result = s"val encoded = $callSUT"
      val checkResult = s"""encoded should be ("$expected")"""

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)
    }

    testBuilder.addTestCases(encodeTestCases, Some(description))
  }

  private def addDecodeTests(testBuilder: TestBuilder): Unit = {
    val description = "Decode test cases"
    val encodeTestCases = (json \ "decode" \ "cases").get.as[List[RailFenceTestCase]]

    implicit def testCaseToGen(tc: RailFenceTestCase): TestCaseGen = {
      val callSUT = s"""RailFenceCipher.decode("${tc.msg}", ${tc.rails})"""
      val expected = tc.expected
      val result = s"val decoded = $callSUT"
      val checkResult = s"""decoded should be ("$expected")"""

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)
    }

    testBuilder.addTestCases(encodeTestCases, Some(description))
  }
}

case class RailFenceTestCase(description: String, msg: String, rails: Int, expected: String)

object RailFenceCipherTestGenerator {
  def main(args: Array[String]): Unit = {
    new RailFenceCipherTestGenerator().write
  }
}
