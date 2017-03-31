import testgen._
import TestSuiteBuilder._
import java.io.File

object SumOfMultiplesTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/sum-of-multiples.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest("factors", "limit"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
