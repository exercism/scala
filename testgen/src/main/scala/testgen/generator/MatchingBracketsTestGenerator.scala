package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object MatchingBracketsTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/matching-brackets.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("value"))
    println(s"-------------")
    println(code)
    println(s"-------------")
