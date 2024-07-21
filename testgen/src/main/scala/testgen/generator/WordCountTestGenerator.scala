package testgen
package generator

import play.api.libs.json.*

final case class WordCountInput(sentence: String) derives Reads

object WordCountTestGenerator extends TestGenerator[WordCountInput, Map[String, Int]]:
  override def slug: String = "word-count"

  override def toTestCaseData(suite: String, testItem: TestItem): Seq[TestCaseData] =
    Seq(
      TestCaseData(
        description = testItem.description,
        suiteCall = s"""$suite("${escape(testItem.input.sentence)}").${testItem.property}""",
        expected = testItem.expected.toCode: success =>
          s"""Map(${success.map { case (s, i) => s"""("$s", $i)""" }.mkString(", ")})""",
      ),
    )
