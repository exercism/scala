import java.io.File

import testgen.TestSuiteBuilder
import testgen.TestSuiteBuilder.fromLabeledTest

object RaindropsTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/raindrops.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest("number"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
