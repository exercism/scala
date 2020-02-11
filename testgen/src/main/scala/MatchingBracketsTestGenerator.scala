import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object MatchingBracketsTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/matching-brackets.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("value"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
