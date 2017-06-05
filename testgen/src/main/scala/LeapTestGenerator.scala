import java.io.File

import testgen.TestSuiteBuilder
import testgen.TestSuiteBuilder.fromLabeledTest

object LeapTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("leap.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest("input"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
