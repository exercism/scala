package testgen
package generator

import java.io.File

import TestSuiteBuilder.fromLabeledTestFromInput

object BobTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/bob.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("heyBob"))

    println(s"-------------")
    println(code)
    println(s"-------------")
