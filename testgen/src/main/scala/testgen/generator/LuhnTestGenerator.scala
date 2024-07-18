package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object LuhnGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/luhn.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("value"))
    println(s"-------------")
    println(code)
    println(s"-------------")
