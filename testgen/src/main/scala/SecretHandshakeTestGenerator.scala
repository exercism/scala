import java.io.File

import testgen.TestSuiteBuilder
import testgen.TestSuiteBuilder.fromLabeledTest

object SecretHandshakeTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/secret-handshake.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest("input"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
