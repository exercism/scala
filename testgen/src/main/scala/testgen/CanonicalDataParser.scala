package testgen

import play.api.libs.json.*
import play.api.libs.functional.syntax.*
import java.io.File

import Exercise.*

object CanonicalDataParser:
  def parse[Input: Reads, Success: Reads](fileContents: String): Result[Exercise[Input, Success]] =
    Json
      .parse(fileContents)
      .validate[Exercise[Input, Success]]
      .asEither
      .left
      .map(v => TestGenError.ParserError(v.toString))

/** Represents a single exercise in the canonical data.
  *
  * Based on the JSON schema at: https://github.com/exercism/problem-specifications/blob/9c864c448fc1cc85cf2959ccffe9c768001a7a39/canonical-data.schema.json#L11
  *
  * @param name
  *   the name of the exercise
  * @param version
  *   not provided in the current schema (!)
  * @param cases
  *   a list of test cases
  * @param comments
  *   a list of multiline comments
  */
final case class Exercise[Input, Success](
  name: String,
  version: Option[String],
  cases: Cases[Input, Success],
  comments: Option[Comments], // TODO: flatten the None comments to empty collection?
)
object Exercise:
  type Description           = String
  type Comments              = Vector[String]
  type Cases[Input, Success] = Seq[LabeledTestItem[Input, Success]]
  type Property              = String
  type Error                 = String

  opaque type Expected[S] = Either[Error, S]
  object Expected:
    inline def apply[R](value: R): Expected[R]     = Right(value)
    inline def apply[R](error: Error): Expected[R] = Left(error)

    extension [S](e: Expected[S])
      def toCode(fromSuccess: S => String): String =
        e.fold(identity, fromSuccess)

  given [I: Reads, S: Reads]: Reads[Exercise[I, S]] = (
    (__ \ "exercise").read[String] and
      (__ \ "version").readNullable[String] and
      (__ \ "cases").read[Seq[LabeledTestItem[I, S]]].map(flattenCases(_, parentDescriptions = Nil)) and
      (__ \ "comments").readNullable[Comments]
  )(Exercise.apply(_, _, _, _))

  // Flattens the nested LabeledTestGroups into a flat list of LabeledTests
  // So far there are too few LabeledTestGroups to handle them separately
  private def flattenCases[I, S](cases: Seq[LabeledTestItem[I, S]], parentDescriptions: List[String]): Seq[LabeledTestItem[I, S]] =
    cases match
      case Seq()                               => Seq()
      case (ltg: LabeledTestGroup[?, ?]) +: xs =>
        flattenCases(ltg.cases, ltg.description :: parentDescriptions) ++
          flattenCases(xs, parentDescriptions)
      case (lt: LabeledTest[?, ?]) +: xs       =>
        lt.copy(parentDescriptions = parentDescriptions) +:
          flattenCases(xs, parentDescriptions)

enum LabeledTestItem[+Input, +Success]:
  /** A single test case.
    * @param uuid
    *   test case identifier
    * @param description
    *   a description of the test case
    * @param property
    *   the property being tested
    * @param expected
    *   the expected result of the test
    * @param result
    *   not available in the schema, JsObject of the whole test case
    * @param parentDescriptions
    *   not available in the schema, used for flattening
    */
  case LabeledTest[Input, Success](
    uuid: String,
    // scenario
    description: Description,
    property: Property,
    expected: Expected[Success],
    input: Input,
    parentDescriptions: List[String] = List(),
  ) extends LabeledTestItem[Input, Success]

  /** A group of test cases.
    *
    * @param description
    *   the description of the group
    * @param cases
    *   the test cases in the group
    */
  case LabeledTestGroup[Input, Success](description: Description, cases: Cases[Input, Success]) extends LabeledTestItem[Input, Success]

export LabeledTestItem.*

object LabeledTestItem:
  given [S: Reads]: Reads[Expected[S]] = Reads[Expected[S]]: json =>
    (json \ "error").asOpt[String] match
      case Some(error) => JsSuccess(Expected(error))
      case None        => json.validate[S].map(Expected(_))

  given labeledTestReads[I: Reads, S: Reads]: Reads[LabeledTest[I, S]] =
    Json.reads[LabeledTest[I, S]]

  given labeledTestGroupReads[I: Reads, S: Reads]: Reads[LabeledTestGroup[I, S]] =
    Json.reads[LabeledTestGroup[I, S]]

  given [I: Reads, S: Reads]: Reads[LabeledTestItem[I, S]] =
    labeledTestGroupReads[I, S].widen[LabeledTestItem[I, S]] orElse
      labeledTestReads[I, S].widen[LabeledTestItem[I, S]]

// add `derives` syntax
extension [A](reads: Reads.type)
  inline def derived: Reads[A] =  Json.reads[A]
