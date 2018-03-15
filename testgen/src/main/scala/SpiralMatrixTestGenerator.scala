import java.io.File

import testgen.TestSuiteBuilder
import testgen.TestSuiteBuilder.fromLabeledTestFromInput

object SpiralMatrixGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/spiral-matrix.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("size"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
