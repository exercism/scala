import play.api.libs.json.Json

import scala.io.Source

class BracketPushTestGenerator {
  implicit val testCaseReader = Json.reads[BracketTestCase]

  private val filename = "bracket-push.json"
  private val fileContents = Source.fromFile(filename).getLines.mkString
  private val json = Json.parse(fileContents)

  def write {
    print("import org.scalatest.{FunSuite, Matchers}" + System.lineSeparator())
    print(System.lineSeparator())
    print("class BracketsTest extends FunSuite with Matchers {" + System.lineSeparator())

    writeTestCases()

    print("}" + System.lineSeparator())
  }

  private def writeTestCases(): Unit = {
    val testCases = (json \ "cases").get.as[List[BracketTestCase]]

    testCases.foreach(tc => {
      print("\ttest(\"" + tc.description + "\") {" + System.lineSeparator())
      println("pending")
      println("Brackets.areBalanced(\"\"\"" + tc.input + "\"\"\") should be (" + tc.expected + ")")

      print("\t}" + System.lineSeparator())
      print(System.lineSeparator())
    })
  }
}

case class BracketTestCase(description: String,
                           input: String,
                           expected: Boolean)

object BracketPushTestGenerator {
  def main(args: Array[String]): Unit = {
    new BracketPushTestGenerator().write
  }
}
