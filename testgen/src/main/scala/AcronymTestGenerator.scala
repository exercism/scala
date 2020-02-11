import testgen._
import TestSuiteBuilder._
import java.io.File

object AcronymTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/acronym.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("phrase"))
    println(s"-------------")
    println(code)
    println(s"-------------")

    TestSuiteBuilder.writeToFile(code, new File("AcronymTest.scala"))
  }
}
