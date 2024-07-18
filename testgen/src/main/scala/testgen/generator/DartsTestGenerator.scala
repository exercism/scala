package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object DartsTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/darts.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("x", "y"))
    println(s"-------------")
    println(code)
    println(s"-------------")
