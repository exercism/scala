import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object DartsTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/darts.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("x", "y"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
