import play.api.libs.json.Json

import scala.io.Source

// Generates test suite from json definition for the Panframs exercise.
class PangramsTestGenerator {
  implicit val pangramTestCaseReader = Json.reads[PangramTestCase]

  private val filename = "pangram.json"
  private val fileContents = Source.fromFile(filename).getLines.mkString
  private val json = Json.parse(fileContents)

  def write {
    print("import org.scalatest.{FunSuite, Matchers}" + System.lineSeparator())
    print(System.lineSeparator())
    print("class PangramsTest extends FunSuite with Matchers {" + System.lineSeparator())

    writeTestCases()

    print("}" + System.lineSeparator())
  }

  private def writeTestCases(): Unit = {
    val testCases = (json \ "cases").get.as[List[PangramTestCase]]

    testCases.foreach(tc => {
      print("\ttest(\"" + tc.description + "\") {" + System.lineSeparator())

      println("Pangrams.isPangram(\"" + tc.input + "\") should be (" + tc.expected + ")")

      print("\t}" + System.lineSeparator())
      print(System.lineSeparator())
    })
  }
}

case class PangramTestCase(description: String, input: String, expected: Boolean)

object PangramsTestGenerator {
  def main(args: Array[String]): Unit = {
    new PangramsTestGenerator().write
  }
}
