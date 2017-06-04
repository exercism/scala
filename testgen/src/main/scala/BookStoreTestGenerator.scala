import java.io.File

import testgen.TestSuiteBuilder
import testgen.TestSuiteBuilder.fromLabeledTest

object BookStoreTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("book-store.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest("basket"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
