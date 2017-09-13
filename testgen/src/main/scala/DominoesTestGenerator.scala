import java.io.File

import testgen.TestSuiteBuilder._
import testgen._
import play.twirl.api.Txt
import play.twirl.api.Template1

object DominoesTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/dominoes.json")

    def toString(expected: CanonicalDataParser.Expected): String = {
      expected match {
        case Left(_) => "false"
        case Right(false) => "false"
        case Right(true) => "true"
      }
    }

    def sutArgs(parseResult: CanonicalDataParser.ParseResult, argNames: String*): String =
      argNames map (name => toArgString(parseResult(name))) mkString ", "

    def toArgString(any: Any): String = {
      any match {
        case xs: List[List[_]] => s"List(${xs.map(ys => s"(${ys.mkString(", ")})").mkString(", ")})"
        case _ => any.toString
      }
    }

    def fromLabeledTest(argNames: String*): ToTestCaseData =
      withLabeledTest { sut =>
        labeledTest =>
          val args = sutArgs(labeledTest.result, argNames: _*)
          val property = labeledTest.property
          val expected = toString(labeledTest.expected)
          val sutCall =
            s"""check($args, $expected)"""
          TestCaseData(labeledTest.description, sutCall, expected)
      }

    val template: TestSuiteTemplate =
      txt.funSuiteTemplateIgnoreExpected.asInstanceOf[Template1[TestSuiteData, Txt]]

    val code = TestSuiteBuilder.build(file, fromLabeledTest("input"), Seq(),
      Seq("private def check(input: List[(Int, Int)], hasResult: Boolean): Unit = {",
        "    val result = Dominoes.chain(input)", "    if (hasResult) {",
        "      checkChain(result getOrElse fail(\"should have had a chain, but didn't\"), input)",
        "    }", "    else assert(result == None)", "  }",
        "",
        "  private def checkChain(result: List[(Int, Int)], input: List[(Int, Int)]): Unit = {",
        "    def sortDomino(ab: (Int, Int)): (Int, Int) =", "      if (ab._1 > ab._2) ab.swap else ab",
        "    def consecutivesShouldMatch(dominoes: List[((Int, Int), Int)]): Unit =",
        "      dominoes.tails foreach {",
        "        case (a@(_,x), i1)::(b@(y,_), i2)::_ =>",
        "          assert(x == y, s\"dominoes $i1 and $i2 don't match: $a $b\")",
        "        case _ =>",
        "      }",
        "    def endsShouldMatch: Unit =",
        "      if (!result.isEmpty)",
        "        consecutivesShouldMatch(List((result.last, result.length - 1),",
        "                                     (result.head, 0)))",
        "",
        "    assert(result.map(sortDomino).sorted == input.map(sortDomino).sorted,",
        "        \"doesn't consist of input dominoes\")",
        "    consecutivesShouldMatch(result.zipWithIndex)",
        "    endsShouldMatch", "  }"))(template)
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
