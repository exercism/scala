import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object DifferenceOfSquaresTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/difference-of-squares.json")

    val code = TestSuiteBuilder.build(file,
        fromLabeledTestAlt("squareOfSum" -> Seq("number"), "sumOfSquares" -> Seq("number"),
          "differenceOfSquares" -> Seq("number")))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
