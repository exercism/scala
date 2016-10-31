import play.api.libs.json.Json

import scala.io.Source

class BowlingTestGenerator {
  implicit val testCaseReader = Json.reads[BowlingTestCase]

  private val filename = "bowling.json"
  private val fileContents = Source.fromFile(filename).getLines.mkString
  private val json = Json.parse(fileContents)

  def write {
    print("import org.scalatest.{FunSuite, Matchers}" + System.lineSeparator())
    print(System.lineSeparator())
    print("class BowlingSuite extends FunSuite with Matchers {" + System.lineSeparator())

    writeTestCases()

    print("}" + System.lineSeparator())
  }

  private def writeTestCases(): Unit = {
    println("// " +  (json \ "score" \ "description").get.as[List[String]].mkString(" "))

    val testCases = (json \ "score" \ "cases").get.as[List[BowlingTestCase]]

    testCases.foreach(tc => {
      print("\ttest(\"" + tc.description + "\") {" + System.lineSeparator())
      println("pending")
      print("val score = List(")
      print(tc.rolls.map(roll => roll.toString).mkString(", "))
      println(").foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()")

      println("score match {")
      if (tc.expected == -1) {
        println("case Right(_) => fail(\"Unexpected score returned. Failure expected\")")
        println("case Left(_) => ")
      } else {
        println("case Right(n) => assert(n == " + tc.expected.toString + ")")
        println("case Left(_) => fail(\"" + tc.description + "\")")
      }
      println("}")
      print("\t}" + System.lineSeparator())
      print(System.lineSeparator())
    })
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
