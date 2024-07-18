package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object RailFenceCipherTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/rail-fence-cipher.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("msg", "rails"))
    println(s"-------------")
    println(code)
    println(s"-------------")
