package testgen
package generator

import java.io.File

import TestSuiteBuilder.fromLabeledTestFromInput

object RotationalCipherTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/rotational-cipher.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("text", "shiftKey"))

    println(s"-------------")
    println(code)
    println(s"-------------")
