package testgen

import play.twirl.api.*
import play.api.libs.json.*
import org.apache.commons.lang3.StringEscapeUtils

type TestSuiteTemplate = Template1[TestSuiteData, Txt]
type Code              = String

final case class TestCaseData(description: String, suiteCall: String, expected: String, pending: Boolean = true)

final case class TestSuiteData(name: String, version: String, imports: Seq[String], testCases: Seq[TestCaseData], statements: Seq[String] = Seq())

trait TestGenerator[I: Reads, S: Reads]:
  final type Input   = I
  final type Success = S

  def slug: String
  protected def toTestCaseData(suite: String, testItem: TestItem): Seq[TestCaseData]
  protected def imports: Seq[String]        = Seq()
  protected def statements: Seq[String]     = Seq()
  protected def template: TestSuiteTemplate = txt.funSuiteTemplate.asInstanceOf[Template1[TestSuiteData, Txt]]

  final type TestItem       = LabeledTestItem.LabeledTest[Input, Success]
  final type ToTestCaseData = String => TestItem => TestCaseData

  final def generate(fileContents: String): Result[Code] =
    CanonicalDataParser
      .parse[Input, Success](fileContents)
      .map: exercise =>
        import exercise.*

        val suiteName           = suiteNameOf(name)
        val tsName              = suiteName + "Test"
        val testCasesAllPending = cases.flatMap: item =>
          // atm all test cases are flattened in the `Exercise.flattenCases` method, so this is safe
          toTestCaseData(suiteName, item.asInstanceOf[TestItem])
        val testCases           =
          testCasesAllPending.prepended(testCasesAllPending.head.copy(pending = false)) // TODO: head could throw here
        val testSuiteData =
          TestSuiteData(tsName, version.getOrElse(""), imports, testCases, statements)

        template.render(testSuiteData).toString

  private def suiteNameOf(exerciseName: String): String =
    exerciseName.split("-").map(_.capitalize).mkString

  protected def escape(raw: String): String = StringEscapeUtils.escapeJava(raw)
