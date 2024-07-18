package testgen
package generator

import play.api.libs.json.{Json, Reads}

import scala.io.Source

// Generates test suite from json test definition for the CustomSet exercise.
class CustomSetTestGenerator:
  given Reads[SingleSetInput]       = Json.reads[SingleSetInput]
  given Reads[DoubleSetInput]       = Json.reads[DoubleSetInput]
  given Reads[SetWithElementInput]  = Json.reads[SetWithElementInput]
  given Reads[EmptyTestCase]        = Json.reads[EmptyTestCase]
  given Reads[ContainsTestCase]     = Json.reads[ContainsTestCase]
  given Reads[SubsetTestCase]       = Json.reads[SubsetTestCase]
  given Reads[DisjointTestCase]     = Json.reads[DisjointTestCase]
  given Reads[EqualTestCase]        = Json.reads[EqualTestCase]
  given Reads[AddTestCase]          = Json.reads[AddTestCase]
  given Reads[IntersectionTestCase] = Json.reads[IntersectionTestCase]
  given Reads[DifferenceTestCase]   = Json.reads[DifferenceTestCase]
  given Reads[UnionTestCase]        = Json.reads[UnionTestCase]

  private val filename     = "src/main/resources/custom-set.json"
  private val fileContents = Source.fromFile(filename).getLines.mkString
  private val json         = Json.parse(fileContents)

  def write: Unit =
    val testBuilder = new TestBuilder("CustomSetTest")
    setVersion(testBuilder)
    addEmptyTests(testBuilder)
    addContainsTests(testBuilder)
    addSubsetTests(testBuilder)
    addDisjointTests(testBuilder)
    addEqualTests(testBuilder)
    addAddTests(testBuilder)
    addIntersectionTests(testBuilder)
    addDifferenceTests(testBuilder)
    addUnionTests(testBuilder)
    testBuilder.toFile

  private def setVersion(testBuilder: TestBuilder): Unit =
    testBuilder.setVersion((json \ "version").get.as[String])

  private def addEmptyTests(testBuilder: TestBuilder): Unit =
    val description =
      "Empty test cases - " + (json \ "cases" \ 0 \ "description").get.as[String]

    val emptyTestCases = (json \ "cases" \ 0 \ "cases").get.as[List[EmptyTestCase]]

    implicit def testCaseToGen(tc: EmptyTestCase): TestCaseGen =
      val set         = s"CustomSet.fromList(${tc.input.set})"
      val callSUT     = s"CustomSet.empty(set)"
      val expected    = tc.expected.toString
      val result      = s"val set = $set"
      val checkResult = s"$callSUT should be ($expected)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)

    testBuilder.addTestCases(emptyTestCases, Some(description))

  private def addContainsTests(testBuilder: TestBuilder): Unit =
    val description =
      "Contains test cases - " + (json \ "cases" \ 1 \ "description").get.as[String]

    val containsTestCases = (json \ "cases" \ 1 \ "cases").get.as[List[ContainsTestCase]]

    implicit def testCaseToGen(tc: ContainsTestCase): TestCaseGen =
      val set         = s"CustomSet.fromList(${tc.input.set})"
      val callSUT     = s"CustomSet.member(set, ${tc.input.element})"
      val expected    = tc.expected.toString
      val result      = s"val set = $set"
      val checkResult = s"$callSUT should be ($expected)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)

    testBuilder.addTestCases(containsTestCases, Some(description))

  private def addSubsetTests(testBuilder: TestBuilder): Unit =
    val description =
      "Subset test cases - " + (json \ "cases" \ 2 \ "description").get.as[String]

    val subsetTestCases = (json \ "cases" \ 2 \ "cases").get.as[List[SubsetTestCase]]

    implicit def testCaseToGen(tc: SubsetTestCase): TestCaseGen =
      val set1        = s"CustomSet.fromList(${tc.input.set1})"
      val set2        = s"CustomSet.fromList(${tc.input.set2})"
      val callSUT     = s"CustomSet.isSubsetOf(set1, set2)"
      val expected    = tc.expected.toString
      val result      =
        s"""val set1 = $set1
    val set2 = $set2"""
      val checkResult = s"$callSUT should be ($expected)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)

    testBuilder.addTestCases(subsetTestCases, Some(description))

  private def addDisjointTests(testBuilder: TestBuilder): Unit =
    val description =
      "Disjoint test cases - " + (json \ "cases" \ 3 \ "description").get.as[String]

    val disjointTestCases = (json \ "cases" \ 3 \ "cases").get.as[List[DisjointTestCase]]

    implicit def testCaseToGen(tc: DisjointTestCase): TestCaseGen =
      val set1        = s"CustomSet.fromList(${tc.input.set1})"
      val set2        = s"CustomSet.fromList(${tc.input.set2})"
      val callSUT     = s"CustomSet.isDisjointFrom(set1, set2)"
      val expected    = tc.expected.toString
      val result      =
        s"""val set1 = $set1
    val set2 = $set2"""
      val checkResult = s"$callSUT should be ($expected)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)

    testBuilder.addTestCases(disjointTestCases, Some(description))

  private def addEqualTests(testBuilder: TestBuilder): Unit =
    val description =
      "Equal test cases - " + (json \ "cases" \ 4 \ "description").get.as[String]

    val equalTestCases = (json \ "cases" \ 4 \ "cases").get.as[List[EqualTestCase]]

    implicit def testCaseToGen(tc: EqualTestCase): TestCaseGen =
      val set1        = s"CustomSet.fromList(${tc.input.set1})"
      val set2        = s"CustomSet.fromList(${tc.input.set2})"
      val callSUT     = s"CustomSet.isEqual(set1, set2)"
      val expected    = tc.expected.toString
      val result      =
        s"""val set1 = $set1
    val set2 = $set2"""
      val checkResult = s"$callSUT should be ($expected)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)

    testBuilder.addTestCases(equalTestCases, Some(description))

  private def addAddTests(testBuilder: TestBuilder): Unit =
    val description =
      "Add test cases - " + (json \ "cases" \ 5 \ "description").get.as[String]

    val addTestCases = (json \ "cases" \ 5 \ "cases").get.as[List[AddTestCase]]

    implicit def testCaseToGen(tc: AddTestCase): TestCaseGen =
      val set         = s"CustomSet.fromList(${tc.input.set})"
      val callSUT     = s"CustomSet.insert(set, ${tc.input.element})"
      val expected    = s"CustomSet.fromList(${tc.expected})"
      val result      =
        s"""val set = $set
    val expected = $expected"""
      val checkResult = s"CustomSet.isEqual($callSUT, expected) should be (true)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)

    testBuilder.addTestCases(addTestCases, Some(description))

  private def addIntersectionTests(testBuilder: TestBuilder): Unit =
    val description =
      "Intersection test cases - " + (json \ "cases" \ 6 \ "description").get.as[String]

    val intersectionTestCases = (json \ "cases" \ 6 \ "cases").get.as[List[IntersectionTestCase]]

    implicit def testCaseToGen(tc: IntersectionTestCase): TestCaseGen =
      val set1        = s"CustomSet.fromList(${tc.input.set1})"
      val set2        = s"CustomSet.fromList(${tc.input.set2})"
      val callSUT     = s"CustomSet.intersection(set1, set2)"
      val expected    = s"CustomSet.fromList(${tc.expected})"
      val result      =
        s"""val set1 = $set1
    val set2 = $set2
    val expected = $expected"""
      val checkResult = s"CustomSet.isEqual($callSUT, expected) should be (true)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)

    testBuilder.addTestCases(intersectionTestCases, Some(description))

  private def addDifferenceTests(testBuilder: TestBuilder): Unit =
    val description =
      "Difference test cases - " + (json \ "cases" \ 7 \ "description").get.as[String]

    val differenceTestCases = (json \ "cases" \ 7 \ "cases").get.as[List[DifferenceTestCase]]

    implicit def testCaseToGen(tc: DifferenceTestCase): TestCaseGen =
      val set1        = s"CustomSet.fromList(${tc.input.set1})"
      val set2        = s"CustomSet.fromList(${tc.input.set2})"
      val callSUT     = s"CustomSet.difference(set1, set2)"
      val expected    = s"CustomSet.fromList(${tc.expected})"
      val result      =
        s"""val set1 = $set1
    val set2 = $set2
    val expected = $expected"""
      val checkResult = s"CustomSet.isEqual($callSUT, expected) should be (true)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)

    testBuilder.addTestCases(differenceTestCases, Some(description))

  private def addUnionTests(testBuilder: TestBuilder): Unit =
    val description =
      "Union test cases - " + (json \ "cases" \ 8 \ "description").get.as[String]

    val unionTestCases = (json \ "cases" \ 8 \ "cases").get.as[List[UnionTestCase]]

    implicit def testCaseToGen(tc: UnionTestCase): TestCaseGen =
      val set1        = s"CustomSet.fromList(${tc.input.set1})"
      val set2        = s"CustomSet.fromList(${tc.input.set2})"
      val callSUT     = s"CustomSet.union(set1, set2)"
      val expected    = s"CustomSet.fromList(${tc.expected})"
      val result      =
        s"""val set1 = $set1
    val set2 = $set2
    val expected = $expected"""
      val checkResult = s"CustomSet.isEqual($callSUT, expected) should be (true)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)

    testBuilder.addTestCases(unionTestCases, Some(description))

case class SingleSetInput(set: List[Int])
case class DoubleSetInput(set1: List[Int], set2: List[Int])
case class SetWithElementInput(set: List[Int], element: Int)
case class EmptyTestCase(description: String, input: SingleSetInput, expected: Boolean)
case class ContainsTestCase(description: String, input: SetWithElementInput, expected: Boolean)
case class SubsetTestCase(description: String, input: DoubleSetInput, expected: Boolean)
case class DisjointTestCase(description: String, input: DoubleSetInput, expected: Boolean)
case class EqualTestCase(description: String, input: DoubleSetInput, expected: Boolean)
case class AddTestCase(description: String, input: SetWithElementInput, expected: List[Int])
case class IntersectionTestCase(description: String, input: DoubleSetInput, expected: List[Int])
case class DifferenceTestCase(description: String, input: DoubleSetInput, expected: List[Int])
case class UnionTestCase(description: String, input: DoubleSetInput, expected: List[Int])

object CustomSetTestGenerator:
  def main(args: Array[String]): Unit =
    new CustomSetTestGenerator().write
