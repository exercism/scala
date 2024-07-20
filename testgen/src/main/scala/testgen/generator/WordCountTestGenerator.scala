package testgen
package generator

import play.api.libs.json.*

final case class WordCountInput(sentence: String) derives Reads
type ExpectedSuccess = Map[String, Int]

object WordCountTestGenerator extends TestGenerator[WordCountInput, ExpectedSuccess]:

  override def toTestCaseData(suite: String, testItem: TestItem): Seq[TestCaseData] =
    Seq(
      TestCaseData(
        description = testItem.description,
        sutCall = s"""$suite("${testItem.input.sentence}").${testItem.property}""",
        expected = testItem.expected.toCode: success =>
          s"""Map(${success.map { case (s, i) => s"""("$s", $i)""" }.mkString(", ")})""",
      ),
    )

  // def main(args: Array[String]): Unit =
  //   val file        = File("/Users/grzegorzbielski/Library/Caches/exercism/configlet/problem-specifications/exercises/word-count/canonical-data.json")
  //   val fileContent = io.Source.fromFile(file).getLines.mkString

  //   val code = WordCountTestGenerator.generate(fileContent)

  //   println(code)

