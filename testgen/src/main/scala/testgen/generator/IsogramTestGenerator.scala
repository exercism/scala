package testgen
package generator

import java.io.File

import TestSuiteBuilder.*

object IsogramTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/isogram.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTestFromInput("phrase"))
    println(s"-------------")
    println(code)
    println(s"-------------")
