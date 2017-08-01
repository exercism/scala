import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object BracketPushTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/bracket-push.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest("input"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
