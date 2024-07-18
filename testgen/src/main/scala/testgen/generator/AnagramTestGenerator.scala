package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object AnagramTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/anagram.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("subject", "candidates"))
    println(s"-------------")
    println(code)
    println(s"-------------")
