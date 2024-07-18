package testgen
package generator

import java.io.File

import TestSuiteBuilder.fromLabeledTestFromInput

object SecretHandshakeTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/secret-handshake.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("number"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
