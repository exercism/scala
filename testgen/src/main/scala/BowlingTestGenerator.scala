import testgen._
import TestSuiteBuilder._
import java.io.File

object BowlingTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/bowling.json")

  def fromLabeledTest(argNames: String*): ToTestCaseData =
    withLabeledTest { sut => labeledTest =>
      val args = sutArgs(labeledTest.result, argNames: _*)
      val isDefined =
        labeledTest.expected.fold(Function.const(".isDefined"), Function.const(""))
      val sutCall =
s"""val score = ${args}.foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score$isDefined"""
      val expected =
        labeledTest.expected.fold(Function.const("true"), x => s"Some($x)")

      TestCaseData(labeledTest.description, sutCall, expected)
    }

    val code = TestSuiteBuilder.build(file, fromLabeledTest("previous_rolls"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
