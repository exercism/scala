package testgen
package generator

import TestSuiteBuilder.*
import java.io.File

object HelloWorldTestGenerator:
  def main(args: Array[String]): Unit =
    val file = new File("src/main/resources/hello-world.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest())
    println(s"-------------")
    println(code)
    println(s"-------------")
