import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object HighScoresTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/high-scores.json")

    val code = TestSuiteBuilder.build(file,
      fromLabeledTestAltFromInput(
        "scores" -> Seq("scores"),
        "latest" -> Seq("scores"),
        "personalBest" -> Seq("scores"),
        "personalTop" -> Seq("scores"),
        "report" -> Seq("scores")))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}