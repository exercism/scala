import play.api.libs.json.Json

import scala.io.Source


class AllYourBaseTestGenerator {
  implicit val testCaseReader = Json.reads[AllYourBaseTestCase]

  private val filename = "all-your-base.json"
  private val fileContents = Source.fromFile(filename).getLines.mkString
  private val json = Json.parse(fileContents)

  def write {
    val testCases: List[AllYourBaseTestCase] = (json \ "cases").get.as[List[AllYourBaseTestCase]]

    implicit def testCaseToGen(tc: AllYourBaseTestCase): TestCaseGen = {
      val callSUT =
        s"AllYourBase.rebase(${tc.input_base}, ${toListString(tc.input_digits)}, ${tc.output_base})"
      val expected = toListString(tc.output_digits)

      TestCaseGen(tc.description, callSUT, expected)
    }

    val testBuilder = new TestBuilder("AllYourBaseTest")
    testBuilder.addTestCases(testCases)
    testBuilder.toFile
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

case class AllYourBaseTestCase(description: String,
                    input_base: Int, input_digits: Array[Int],
                    output_base: Int, output_digits: Option[Array[Int]])

object AllYourBaseTestGenerator {
  def main(args: Array[String]): Unit = {
    new AllYourBaseTestGenerator().write
  }
}
