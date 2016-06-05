import play.api.libs.json.Json

import scala.io.Source

// Generates test suite from json test definitionfor the CustomSet exercise.
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
    print("import org.scalatest.{FunSuite, Matchers}" + System.lineSeparator())
    print(System.lineSeparator())
    print("class CustomSetTest extends FunSuite with Matchers {" + System.lineSeparator())

    writeEmptyTests()
    writeContainsTests()
    writeSubsetTests()
    writeDisjointTests()
    writeEqualTests()
    writeAddTests()
    writeIntersectionTests()
    writeDifferenceTests()
    writeUnionTests()

    print("}" + System.lineSeparator())
  }

  private def writeEmptyTests(): Unit = {
    println("// Empty test cases - " +  (json \ "empty" \ "description").get.as[String])

    val emptyTestCases = (json \ "empty" \ "cases").get.as[List[EmptyTestCase]]

    emptyTestCases.foreach(tc => {
      print("\ttest(\"" + tc.description + "\") {" + System.lineSeparator())

      println("val set = CustomSet.fromList(" + tc.set + ")")
      println("CustomSet.empty(set) should be (" + tc.expected + ")")

      print("\t}" + System.lineSeparator())
      print(System.lineSeparator())
    })
  }

  private def writeContainsTests(): Unit = {
    println("// Contains test cases - " +  (json \ "contains" \ "description").get.as[String])

    val containsTestCases = (json \ "contains" \ "cases").get.as[List[ContainsTestCase]]

    containsTestCases.foreach(tc => {
      print("\ttest(\"" + tc.description + "\") {" + System.lineSeparator())

      println("val set = CustomSet.fromList(" + tc.set + ")")
      println("CustomSet.member(set, " + tc.element + ") should be (" + tc.expected + ")")

      print("\t}" + System.lineSeparator())
      print(System.lineSeparator())
    })
  }

  private def writeSubsetTests(): Unit = {
    println("// Subset test cases - " +  (json \ "subset" \ "description").get.as[String])

    val subsetTestCases = (json \ "subset" \ "cases").get.as[List[SubsetTestCase]]

    subsetTestCases.foreach(tc => {
      print("\ttest(\"" + tc.description + "\") {" + System.lineSeparator())

      println("val set1 = CustomSet.fromList(" + tc.set1 + ")")
      println("val set2 = CustomSet.fromList(" + tc.set2 + ")")
      println("CustomSet.isSubsetOf(set1, set2) should be (" + tc.expected + ")")

      print("\t}" + System.lineSeparator())
      print(System.lineSeparator())
    })
  }

  private def writeDisjointTests(): Unit = {
    println("// Disjoint test cases - " +  (json \ "disjoint" \ "description").get.as[String])

    val disjointTestCases = (json \ "disjoint" \ "cases").get.as[List[DisjointTestCase]]

    disjointTestCases.foreach(tc => {
      print("\ttest(\"" + tc.description + "\") {" + System.lineSeparator())

      println("val set1 = CustomSet.fromList(" + tc.set1 + ")")
      println("val set2 = CustomSet.fromList(" + tc.set2 + ")")
      println("CustomSet.isDisjointFrom(set1, set2) should be (" + tc.expected + ")")

      print("\t}" + System.lineSeparator())
      print(System.lineSeparator())
    })
  }

  private def writeEqualTests(): Unit = {
    println("// Equal test cases - " +  (json \ "equal" \ "description").get.as[String])

    val equalTestCases = (json \ "equal" \ "cases").get.as[List[EqualTestCase]]

    equalTestCases.foreach(tc => {
      print("\ttest(\"" + tc.description + "\") {" + System.lineSeparator())

      println("val set1 = CustomSet.fromList(" + tc.set1 + ")")
      println("val set2 = CustomSet.fromList(" + tc.set2 + ")")
      println("CustomSet.isEqual(set1, set2) should be (" + tc.expected + ")")

      print("\t}" + System.lineSeparator())
      print(System.lineSeparator())
    })
  }

  private def writeAddTests(): Unit = {
    println("// Add test cases - " +  (json \ "add" \ "description").get.as[String])

    val addTestCases = (json \ "add" \ "cases").get.as[List[AddTestCase]]

    addTestCases.foreach(tc => {
      print("\ttest(\"" + tc.description + "\") {" + System.lineSeparator())

      println("val set = CustomSet.fromList(" + tc.set + ")")
      println("val expected = CustomSet.fromList(" + tc.expected + ")")
      println("CustomSet.isEqual(CustomSet.insert(set, " +  tc.element + " ), expected) should be (true)")

      print("\t}" + System.lineSeparator())
      print(System.lineSeparator())
    })
  }

  private def writeIntersectionTests(): Unit = {
    println("// Intersection test cases - " +  (json \ "intersection" \ "description").get.as[String])

    val intersectionTestCases = (json \ "intersection" \ "cases").get.as[List[IntersectionTestCase]]

    intersectionTestCases.foreach(tc => {
      print("\ttest(\"" + tc.description + "\") {" + System.lineSeparator())

      println("val set1 = CustomSet.fromList(" + tc.set1 + ")")
      println("val set2 = CustomSet.fromList(" + tc.set2 + ")")
      println("val expected = CustomSet.fromList(" + tc.expected + ")")
      println("CustomSet.isEqual(CustomSet.intersection(set1, set2), expected) should be (true)")

      print("\t}" + System.lineSeparator())
      print(System.lineSeparator())
    })
  }

  private def writeDifferenceTests(): Unit = {
    println("// Difference test cases - " +  (json \ "difference" \ "description").get.as[String])

    val differenceTestCases = (json \ "difference" \ "cases").get.as[List[DifferenceTestCase]]

    differenceTestCases.foreach(tc => {
      print("\ttest(\"" + tc.description + "\") {" + System.lineSeparator())

      println("val set1 = CustomSet.fromList(" + tc.set1 + ")")
      println("val set2 = CustomSet.fromList(" + tc.set2 + ")")
      println("val expected = CustomSet.fromList(" + tc.expected + ")")
      println("CustomSet.isEqual(CustomSet.difference(set1, set2), expected) should be (true)")

      print("\t}" + System.lineSeparator())
      print(System.lineSeparator())
    })
  }

  private def writeUnionTests(): Unit = {
    println("// Union test cases - " +  (json \ "union" \ "description").get.as[String])

    val differenceTestCases = (json \ "union" \ "cases").get.as[List[UnionTestCase]]

    differenceTestCases.foreach(tc => {
      print("\ttest(\"" + tc.description + "\") {" + System.lineSeparator())

      println("val set1 = CustomSet.fromList(" + tc.set1 + ")")
      println("val set2 = CustomSet.fromList(" + tc.set2 + ")")
      println("val expected = CustomSet.fromList(" + tc.expected + ")")
      println("CustomSet.isEqual(CustomSet.union(set1, set2), expected) should be (true)")

      print("\t}" + System.lineSeparator())
      print(System.lineSeparator())
    })
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