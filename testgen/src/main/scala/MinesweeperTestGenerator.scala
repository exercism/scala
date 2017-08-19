import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object MinesweeperTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/minesweeper.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest("input"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}