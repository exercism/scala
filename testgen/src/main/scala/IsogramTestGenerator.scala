import play.api.libs.json.Json

import scala.io.Source

class IsogramTestGenerator {
  implicit val testCaseReader = Json.reads[IsogramTestCase]

  private val filename = "isogram.json"
  private val fileContents = Source.fromFile(filename).getLines.mkString
  private val json = Json.parse(fileContents)

  def write {
    val testCases = (json \ "cases").get.as[List[IsogramTestCase]]

    implicit def testCaseToGen(tc: IsogramTestCase): TestCaseGen = {
      val callSUT =
        s"""Isogram.isIsogram("${tc.input}")"""
      val expected = tc.expected.toString

      TestCaseGen(tc.description, callSUT, expected)
    }

    val testBuilder = new TestBuilder("IsogramTest")
    testBuilder.addTestCases(testCases)
    testBuilder.toFile
  }
}

case class IsogramTestCase(description: String,
                           input: String,
                           expected: Boolean)

object IsogramTestGenerator {
  def main(args: Array[String]): Unit = {
    new IsogramTestGenerator().write
  }
}
