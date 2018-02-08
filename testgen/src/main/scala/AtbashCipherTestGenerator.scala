import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object AtbashCipherTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/atbash-cipher.json")

    val code = TestSuiteBuilder.build(file,
        fromLabeledTestAltFromInput("encode" -> Seq("phrase"), "decode" -> Seq("phrase")))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
