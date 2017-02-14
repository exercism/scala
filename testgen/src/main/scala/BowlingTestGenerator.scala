import play.api.libs.json.Json

import scala.io.Source

class BowlingTestGenerator {
  implicit val testCaseReader = Json.reads[BowlingTestCase]

  private val filename = "bowling.json"
  private val fileContents = Source.fromFile(filename).getLines.mkString
  private val json = Json.parse(fileContents)

  def write {
    val testCases = (json \ "score" \ "cases").get.as[List[BowlingTestCase]]
    val description = (json \ "score" \ "description").get.as[List[String]].mkString(" ")

    implicit def testCaseToGen(tc: BowlingTestCase): TestCaseGen = {
      val callSUT =
        s"${tc.rolls}.foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()"
      val expected = ""
      val result = s"val score = $callSUT"
      val (matchRight, matchLeft) =
        if (tc.expected == -1)
          ("""fail("Unexpected score returned. Failure expected")""", "")
        else
          (s"assert(n == ${tc.expected})", s"""fail("${tc.description}")""")
      val checkResult =
s"""score match {
      case Right(n) => $matchRight
      case Left(_) => $matchLeft
    }"""

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)
    }

    val testBuilder = new TestBuilder("BowlingTest")
    testBuilder.addTestCases(testCases, Some(description))
    testBuilder.toFile
  }
}

case class BowlingTestCase(description: String,
                           rolls: List[Int],
                           expected: Int)

object BowlingTestGenerator {
  def main(args: Array[String]): Unit = {
    new BowlingTestGenerator().write
  }
}
