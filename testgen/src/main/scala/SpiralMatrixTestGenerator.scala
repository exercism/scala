import java.io.File

import testgen.TestSuiteBuilder
import testgen.TestSuiteBuilder.fromLabeledTest

object SpiralMatrixGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/spiral-matrix.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest("input"))
    println(s"-------------")
    println(code)
    println(s"-------------")

    TestSuiteBuilder.writeToFile(code, new File("SpiralMatrixTest.scala"))
  }
}
