import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object CryptoSquareTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/crypto-square.json")

    val code = TestSuiteBuilder.build(file,
        fromLabeledTestAlt("normalizedPlaintext" -> Seq("plaintext"), "plaintextSegments" -> Seq("plaintext"),
          "encoded" -> Seq("plaintext"), "ciphertext" -> Seq("plaintext")))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
