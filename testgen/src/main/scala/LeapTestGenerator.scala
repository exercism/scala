import java.io.File

import testgen.TestSuiteBuilder
import testgen.TestSuiteBuilder.fromLabeledTestFromInput

object LeapTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/leap.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("year"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
