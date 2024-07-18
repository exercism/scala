package testgen
package generator

import java.io.File

import TestSuiteBuilder.fromLabeledTestFromInput

object SieveTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/sieve.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("limit"))
    println(s"-------------")
    println(code)
    println(s"-------------")
