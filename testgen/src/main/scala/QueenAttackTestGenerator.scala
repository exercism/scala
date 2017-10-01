import java.io.File

import testgen.TestSuiteBuilder._
import testgen._

object QueenAttackTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/queen-attack.json")

    def getPositionArgs(queenMap: Map[String, String]): String =
      queenMap("position").stripPrefix("(").stripSuffix(")")

    def getQueen(labeledTest: LabeledTest): Map[String, String] = {
      labeledTest.result("queen").
        asInstanceOf[Map[String, String]]
    }

    def getWhiteQueen(labeledTest: LabeledTest): Map[String, String] = {
      labeledTest.result("white_queen").
        asInstanceOf[Map[String, String]]
    }

    def getBlackQueen(labeledTest: LabeledTest): Map[String, String] = {
      labeledTest.result("black_queen").
        asInstanceOf[Map[String, String]]
    }

    def getCanAttackArgs(labeledTest: LabeledTest): String = {
      val whiteQueenArgs = getPositionArgs(getWhiteQueen(labeledTest))
      val blackQueenArgs = getPositionArgs(getBlackQueen(labeledTest))
      s"create($whiteQueenArgs), create($blackQueenArgs)"
    }

    def toCreateExpected(labeledTest: LabeledTest): String = {
      val expected = labeledTest.expected
      expected match {
        case Right(-1) => s"None"
        case Right(0) =>
          val args = getPositionArgs(getQueen(labeledTest))
          s"Some(Queen($args))"
        case _ => throw new IllegalStateException
      }
    }


    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val property = labeledTest.property
          val (clazz, args, expected) = property match {
            case "create" =>
              ("Queen",
                getPositionArgs(getQueen(labeledTest)),
                toCreateExpected(labeledTest))
            case "canAttack" =>
              ("QueenAttack",
                getCanAttackArgs(labeledTest),
                labeledTest.expected.fold(_ => "false", _.toString))
            case _ => throw new IllegalStateException()
          }
          val sutCall = s"""$clazz.$property($args)"""
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val code =
      TestSuiteBuilder.build(file, fromLabeledTest(),
        Seq(),
        Seq(" private def create(x: Int, y: Int): Queen = {",
          "    Queen.create(x, y) match {",
          "      case Some(q) => q",
          "      case None => fail(\"Error creating queen\")",
          "    }",
          "  }"))

    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
