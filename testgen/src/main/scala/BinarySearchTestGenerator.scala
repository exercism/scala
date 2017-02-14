import play.api.libs.json.Json

import scala.io.Source

class BinarySearchTestGenerator {
  implicit val testCaseReader = Json.reads[BinarySearchTestCase]

  private val filename = "binarysearch.json"
  private val fileContents = Source.fromFile(filename).getLines.mkString
  private val json = Json.parse(fileContents)

  def write {
    val testCases = (json \ "cases").get.as[List[BinarySearchTestCase]]

    implicit def testCaseToGen(tc: BinarySearchTestCase): TestCaseGen = {
      val elements = tc.array.map(elem => elem.toString).mkString(", ")
      val elementStr =
        if (elements.isEmpty)
          "Array[Int]()"
        else
          s"Array(${elements})"

      val callSUT =
        s"BinarySearch.search(${elementStr}, ${tc.value})"
      val expected =
        if (tc.expected == -1)
          "None"
        else
          s"Some(${tc.expected})"

      TestCaseGen(tc.description, callSUT, expected)
    }

    val testBuilder = new TestBuilder("BinarySearchTest")
    testBuilder.addTestCases(testCases)
    testBuilder.toFile
  }
}

case class BinarySearchTestCase(description: String,
                                array: Array[Int],
                                value: Int, expected: Int)

object BinarySearchTestGenerator {
  def main(args: Array[String]): Unit = {
    new BinarySearchTestGenerator().write
  }
}
