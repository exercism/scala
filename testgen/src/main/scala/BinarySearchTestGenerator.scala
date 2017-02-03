import play.api.libs.json.Json

import scala.io.Source

class BinarySearchTestGenerator {
  implicit val testCaseReader = Json.reads[BinarySearchTestCase]

  private val filename = "binarysearch.json"
  private val fileContents = Source.fromFile(filename).getLines.mkString
  private val json = Json.parse(fileContents)

  def write {
    print("import org.scalatest.{FunSuite, Matchers}" + System.lineSeparator())
    print(System.lineSeparator())
    print("class BinarySearchTest extends FunSuite with Matchers {" + System.lineSeparator())

    writeTestCases()

    println("}")
  }

  private def writeTestCases(): Unit = {
    val testCases = (json \ "cases").get.as[List[BinarySearchTestCase]]

    testCases.foreach(tc => {
      print("\ttest(\"" + tc.description + "\") {" + System.lineSeparator())
      println("pending")
      print("val elements = Array[Int](")
      print(tc.array.map(elem => elem.toString).mkString(", "))
      println(")")
      println("val result = BinarySearch.search(elements, " + tc.value + ")")

      if (tc.expected == -1) {
        println("assert(result.isEmpty)")
      } else {
        println("assert(result.contains(" + tc.expected + "))")
      }


      println("}")
      println()
    })
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
