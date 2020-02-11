import testgen._
import TestSuiteBuilder.{toString, _}
import java.io.File

object BowlingTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/bowling.json")

  def previousRolls(parseResult: CanonicalDataParser.ParseResult): String =
    TestSuiteBuilder.toString(parseResult("previous_rolls"))

  def roll(parseResult: CanonicalDataParser.ParseResult): Option[String] =
    if (parseResult contains "roll")
      Some(TestSuiteBuilder.toString(parseResult("roll")))
    else
      None

  def fromLabeledTest(argNames: String*): ToTestCaseData =
    withLabeledTest { sut => labeledTest =>
      val previousRollsVal = previousRolls(labeledTest.result)
      val rollVal = roll(labeledTest.result)

      val isDefined =
        labeledTest.expected.fold(Function.const(".isLeft"), Function.const(""))
      val sutCall = rollVal match {
        case Some(r) => s"""val score = ${previousRollsVal}.foldLeft(Bowling())((acc, roll) => acc.roll(roll)).roll($r).score()
    score$isDefined"""
        case None => s"""val score = ${previousRollsVal}.foldLeft(Bowling())((acc, roll) => acc.roll(roll)).score()
    score$isDefined"""
      }

      val expected =
        labeledTest.expected.fold(Function.const("true"), x => s"Right($x)")

      TestCaseData(labeledTest.description, sutCall, expected)
    }

    val code = TestSuiteBuilder.build(file, fromLabeledTest("previous_rolls", "roll"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
