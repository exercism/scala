import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object RailFenceCipherTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/rail-fence-cipher.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("msg", "rails"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
