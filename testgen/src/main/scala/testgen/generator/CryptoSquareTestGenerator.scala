package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object CryptoSquareTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/crypto-square.json")

    val code = TestSuiteBuilder.build(
      file,
      fromLabeledTestAltFromInput(
        "normalizedPlaintext" -> Seq("plaintext"),
        "plaintextSegments"   -> Seq("plaintext"),
        "encoded"             -> Seq("plaintext"),
        "ciphertext"          -> Seq("plaintext"),
      ),
    )
    println(s"-------------")
    println(code)
    println(s"-------------")
