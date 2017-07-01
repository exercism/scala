import java.io.File

import testgen.TestSuiteBuilder
import testgen.TestSuiteBuilder.fromLabeledTest

object SieveTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/sieve.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest("limit"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
