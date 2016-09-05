import play.api.libs.json.Json

import scala.io.Source

class AllYourBaseTestGenerator {
  implicit val testCaseReader = Json.reads[TestCase]

  private val filename = "all-your-base.json"
  private val fileContents = Source.fromFile(filename).getLines.mkString
  private val json = Json.parse(fileContents)

  def write {
    print("import org.scalatest.{FunSuite, Matchers}" + System.lineSeparator())
    print(System.lineSeparator())
    print("class AllYourBaseTest extends FunSuite with Matchers {" + System.lineSeparator())

    writeTestCases()

    print("}" + System.lineSeparator())
  }

  private def writeTestCases(): Unit = {
    val testCases = (json \ "cases").get.as[List[TestCase]]

    testCases.foreach(tc => {
      print("\ttest(\"" + tc.description + "\") {" + System.lineSeparator())
      println("pending")
      println("AllYourBase.rebase(" + tc.input_base  + ", " + toListString(tc.input_digits) +
        "," + System.lineSeparator() + tc.output_base + ") should be (" + toListString(tc.output_digits) + ")")

      print("\t}" + System.lineSeparator())
      print(System.lineSeparator())
    })
  }

  private def toListString(a: Array[Int]): String =
      "List(" + a.mkString(", ") + ")"

  private def toListString(o: Option[Array[Int]]): String = {
    o match {
      case Some(a) => "Some(List(" + a.mkString(" ,") + "))"
      case None => "None"
    }
  }
}

case class TestCase(description: String,
                    input_base: Int, input_digits: Array[Int],
                    output_base: Int, output_digits: Option[Array[Int]])

object AllYourBaseTestGenerator {
  def main(args: Array[String]): Unit = {
    new AllYourBaseTestGenerator().write
  }
}
