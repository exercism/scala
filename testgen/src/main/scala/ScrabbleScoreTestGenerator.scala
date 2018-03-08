import java.io.File

import testgen.TestSuiteBuilder
import testgen.TestSuiteBuilder.fromLabeledTestFromInput

object ScrabbleScoreTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/scrabble-score.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("word"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
