import play.api.libs.json.Json

import scala.io.Source

// Generates test suite from json test definition for the CustomSet exercise.
class CustomSetTestGenerator {
  implicit val emptyTestCaseReader = Json.reads[EmptyTestCase]
  implicit val containsTestCasesReader = Json.reads[ContainsTestCase]
  implicit val subsetTestCasesReader = Json.reads[SubsetTestCase]
  implicit val disjointTestCasesReader = Json.reads[DisjointTestCase]
  implicit val equalTestCasesReader = Json.reads[EqualTestCase]
  implicit val addTestCasesReader = Json.reads[AddTestCase]
  implicit val intersectionTestCasesReader = Json.reads[IntersectionTestCase]
  implicit val differenceTestCasesReader = Json.reads[DifferenceTestCase]
  implicit val unionTestCasesReader = Json.reads[UnionTestCase]

  private val filename = "custom-set.json"
  private val fileContents = Source.fromFile(filename).getLines.mkString
  private val json = Json.parse(fileContents)

  def write {
    val testBuilder = new TestBuilder("CustomSetTest")
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
  }

  private def addEmptyTests(testBuilder: TestBuilder): Unit = {
    val description =
      "Empty test cases - " +  (json \ "empty" \ "description").get.as[String]

    val emptyTestCases = (json \ "empty" \ "cases").get.as[List[EmptyTestCase]]

    implicit def testCaseToGen(tc: EmptyTestCase): TestCaseGen = {
      val set = s"CustomSet.fromList(${tc.set})"
      val callSUT = s"CustomSet.empty(set)"
      val expected = tc.expected.toString
      val result = s"val set = $callSUT"
      val checkResult = s"$callSUT should be ($expected)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)
    }

    testBuilder.addTestCases(emptyTestCases, Some(description))
  }

  private def addContainsTests(testBuilder: TestBuilder): Unit = {
    val description =
      "Contains test cases - " + (json \ "contains" \ "description").get.as[String]

    val containsTestCases = (json \ "contains" \ "cases").get.as[List[ContainsTestCase]]

    implicit def testCaseToGen(tc: ContainsTestCase): TestCaseGen = {
      val set = s"CustomSet.fromList(${tc.set})"
      val callSUT = s"CustomSet.member(set, ${tc.element})"
      val expected = tc.expected.toString
      val result = s"val set = $set"
      val checkResult = s"$callSUT should be ($expected)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)
    }

    testBuilder.addTestCases(containsTestCases, Some(description))
  }

  private def addSubsetTests(testBuilder: TestBuilder): Unit = {
    val description =
      "Subset test cases - " + (json \ "subset" \ "description").get.as[String]

    val subsetTestCases = (json \ "subset" \ "cases").get.as[List[SubsetTestCase]]

    implicit def testCaseToGen(tc: SubsetTestCase): TestCaseGen = {
      val set1 = s"CustomSet.fromList(${tc.set1})"
      val set2 = s"CustomSet.fromList(${tc.set2})"
      val callSUT = s"CustomSet.isSubsetOf(set1, set2)"
      val expected = tc.expected.toString
      val result =
s"""val set1 = $set1
    val set2 = $set2"""
      val checkResult = s"$callSUT should be ($expected)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)
    }

    testBuilder.addTestCases(subsetTestCases, Some(description))
  }

  private def addDisjointTests(testBuilder: TestBuilder): Unit = {
    val description =
      "Disjoint test cases - " + (json \ "disjoint" \ "description").get.as[String]

    val disjointTestCases = (json \ "disjoint" \ "cases").get.as[List[DisjointTestCase]]

    implicit def testCaseToGen(tc: DisjointTestCase): TestCaseGen = {
      val set1 = s"CustomSet.fromList(${tc.set1})"
      val set2 = s"CustomSet.fromList(${tc.set2})"
      val callSUT = s"CustomSet.isDisjointFrom(set1, set2)"
      val expected = tc.expected.toString
      val result =
s"""val set1 = $set1
    val set2 = $set2"""
      val checkResult = s"$callSUT should be ($expected)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)
    }

    testBuilder.addTestCases(disjointTestCases, Some(description))
  }

  private def addEqualTests(testBuilder: TestBuilder): Unit = {
    val description =
      "Equal test cases - " + (json \ "equal" \ "description").get.as[String]

    val equalTestCases = (json \ "equal" \ "cases").get.as[List[EqualTestCase]]

    implicit def testCaseToGen(tc: EqualTestCase): TestCaseGen = {
      val set1 = s"CustomSet.fromList(${tc.set1})"
      val set2 = s"CustomSet.fromList(${tc.set2})"
      val callSUT = s"CustomSet.isEqual(set1, set2)"
      val expected = tc.expected.toString
      val result =
s"""val set1 = $set1
    val set2 = $set2"""
      val checkResult = s"$callSUT should be ($expected)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)
    }

    testBuilder.addTestCases(equalTestCases, Some(description))
  }

  private def addAddTests(testBuilder: TestBuilder): Unit = {
    val description =
      "Add test cases - " + (json \ "add" \ "description").get.as[String]

    val addTestCases = (json \ "add" \ "cases").get.as[List[AddTestCase]]

    implicit def testCaseToGen(tc: AddTestCase): TestCaseGen = {
      val set = s"CustomSet.fromList(${tc.set})"
      val callSUT = s"CustomSet.insert(set, ${tc.element})"
      val expected = s"CustomSet.fromList(${tc.expected})"
      val result =
s"""val set = $set
    val expected = $expected"""
      val checkResult = s"CustomSet.isEqual($callSUT, expected) should be (true)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)
    }

    testBuilder.addTestCases(addTestCases, Some(description))
  }

  private def addIntersectionTests(testBuilder: TestBuilder): Unit = {
    val description =
      "Intersection test cases - " + (json \ "intersection" \ "description").get.as[String]

    val intersectionTestCases = (json \ "intersection" \ "cases").get.as[List[IntersectionTestCase]]

    implicit def testCaseToGen(tc: IntersectionTestCase): TestCaseGen = {
      val set1 = s"CustomSet.fromList(${tc.set1})"
      val set2 = s"CustomSet.fromList(${tc.set2})"
      val callSUT = s"CustomSet.intersection(set1, set2)"
      val expected = s"CustomSet.fromList(${tc.expected})"
      val result =
s"""val set1 = $set1
    val set2 = $set2
    val expected = $expected"""
      val checkResult = s"CustomSet.isEqual($callSUT, expected) should be (true)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)
    }

    testBuilder.addTestCases(intersectionTestCases, Some(description))
  }

  private def addDifferenceTests(testBuilder: TestBuilder): Unit = {
    val description =
      "Difference test cases - " + (json \ "difference" \ "description").get.as[String]

    val differenceTestCases = (json \ "difference" \ "cases").get.as[List[DifferenceTestCase]]

    implicit def testCaseToGen(tc: DifferenceTestCase): TestCaseGen = {
      val set1 = s"CustomSet.fromList(${tc.set1})"
      val set2 = s"CustomSet.fromList(${tc.set2})"
      val callSUT = s"CustomSet.difference(set1, set2)"
      val expected = s"CustomSet.fromList(${tc.expected})"
      val result =
s"""val set1 = $set1
    val set2 = $set2
    val expected = $expected"""
      val checkResult = s"CustomSet.isEqual($callSUT, expected) should be (true)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)
    }

    testBuilder.addTestCases(differenceTestCases, Some(description))
  }

  private def addUnionTests(testBuilder: TestBuilder): Unit = {
    val description =
      "Union test cases - " + (json \ "union" \ "description").get.as[String]

    val unionTestCases = (json \ "union" \ "cases").get.as[List[UnionTestCase]]

    implicit def testCaseToGen(tc: UnionTestCase): TestCaseGen = {
      val set1 = s"CustomSet.fromList(${tc.set1})"
      val set2 = s"CustomSet.fromList(${tc.set2})"
      val callSUT = s"CustomSet.union(set1, set2)"
      val expected = s"CustomSet.fromList(${tc.expected})"
      val result =
s"""val set1 = $set1
    val set2 = $set2
    val expected = $expected"""
      val checkResult = s"CustomSet.isEqual($callSUT, expected) should be (true)"

      TestCaseGen(tc.description, callSUT, expected, result, checkResult)
    }

    testBuilder.addTestCases(unionTestCases, Some(description))
  }

}

case class EmptyTestCase(description: String, set: List[Int], expected: Boolean)
case class ContainsTestCase(description: String, set: List[Int], element: Int, expected: Boolean)
case class SubsetTestCase(description: String, set1: List[Int], set2: List[Int], expected: Boolean)
case class DisjointTestCase(description: String, set1: List[Int], set2: List[Int], expected: Boolean)
case class EqualTestCase(description: String, set1: List[Int], set2: List[Int], expected: Boolean)
case class AddTestCase(description: String, set: List[Int], element: Int, expected: List[Int])
case class IntersectionTestCase(description: String, set1: List[Int], set2: List[Int], expected: List[Int])
case class DifferenceTestCase(description: String, set1: List[Int], set2: List[Int], expected: List[Int])
case class UnionTestCase(description: String, set1: List[Int], set2: List[Int], expected: List[Int])


object CustomSetTestGenerator {
  def main(args: Array[String]): Unit = {
    new CustomSetTestGenerator().write
  }
}