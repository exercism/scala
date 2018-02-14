import java.io.File

import testgen.TestSuiteBuilder
import testgen.TestSuiteBuilder.fromLabeledTestFromInput

object BookStoreTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/book-store.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("basket"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
