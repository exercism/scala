package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object RomanNumeralsTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/roman-numerals.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("number"))
    println(s"-------------")
    println(code)
    println(s"-------------")
