import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.collection.immutable.HashMap


/** @version 1.2.0 */
class ResultsAnalysisTest extends AnyFunSuite with Matchers {

  test("Task 1") {
    val lines = List(
      "ID,Name,1,2,3,4,5,6,7,8,9,10",
      "1,Jane Doe,9,10,9,6,10,9,10,10,10,9",
      "2,John Doe,8,6,5,7,-1,-1,8,6,8,10",
      "3,Jake Doe,10,9,9,-1,5,-1,8,-1,10,9",
      "4,Jill Doe,10,8,2,7,10,9,7,6,7,2",
    )

    val expectedResults = List(
      Results(1, "Jane Doe", IndexedSeq(9, 10, 9, 6, 10, 9, 10, 10, 10, 9)),
      Results(2, "John Doe", IndexedSeq(8, 6, 5, 7, -1, -1, 8, 6, 8, 10)),
      Results(3, "Jake Doe", IndexedSeq(10, 9, 9, -1, 5, -1, 8, -1, 10, 9)),
      Results(4, "Jill Doe", IndexedSeq(10, 8, 2, 7, 10, 9, 7, 6, 7, 2))
    )

    val resultList = ResultsAnalysis.task1(lines)
    resultList shouldEqual expectedResults
  }

  test("Task 2") {
    val results = List(
      Results(1, "Jane Doe", IndexedSeq(9, 10, 9, 6, 10, 9, 10, 10, 10, 9)),
      Results(2, "John Doe", IndexedSeq(8, 6, 5, 7, -1, -1, 8, 6, 8, 10)),
      Results(3, "Jake Doe", IndexedSeq(10, 9, 9, -1, 5, -1, 8, -1, 10, 9)),
      Results(4, "Jill Doe", IndexedSeq(10, 8, 2, 7, 10, 9, 7, 6, 7, 2))
    )

    val expectedResults = Map(
      "Jane Doe" -> 10,
      "John Doe" -> 8,
      "Jake Doe" -> 7,
      "Jill Doe" -> 8
    )

    val resultList = ResultsAnalysis.task2(results)
    resultList shouldEqual expectedResults
  }

  test("Task 3") {
    val results = Map(
      "Jane Doe" -> 10,
      "John Doe" -> 8,
      "Jake Doe" -> 7,
      "Jill Doe" -> 8
    )

    val expectedResults = (Set("Jane Doe", "John Doe", "Jill Doe"), Set("Jake Doe"))

    val resultList = ResultsAnalysis.task3(results)
    resultList shouldEqual expectedResults
  }

  test("Task 4") {
    val results = List(
      Results(1, "Jane Doe", IndexedSeq(9, 10, 9, 6, 10, 9, 10, 10, 10, 9)),
      Results(2, "John Doe", IndexedSeq(8, 6, 5, 7, -1, -1, 8, 6, 8, 10)),
      Results(3, "Jake Doe", IndexedSeq(10, 9, 9, -1, 5, -1, 8, -1, 10, 9)),
      Results(4, "Jill Doe", IndexedSeq(10, 8, 2, 7, 10, 9, 7, 6, 7, 2))
    )

    val expectedResults = Map(
      "Jane Doe" -> Grade.EXCELLENT,
      "John Doe" -> Grade.SATISFACTORY,
      "Jake Doe" -> Grade.INSUFFICIENT,
      "Jill Doe" -> Grade.GOOD
    )

    val resultList = ResultsAnalysis.task4(results)
    resultList shouldEqual expectedResults
  }

  test("Task 5") {
    val results = List(
      Results(1, "Jane Doe", IndexedSeq(9, 10, 9, 6, 10, 9, 10, 10, 10, 9)),
      Results(2, "John Doe", IndexedSeq(8, 6, 5, 7, -1, -1, 8, 6, 8, 10)),
      Results(3, "Jake Doe", IndexedSeq(10, 9, 9, -1, 5, -1, 8, -1, 10, 9)),
      Results(4, "Jill Doe", IndexedSeq(10, 8, 2, 7, 10, 9, 7, 6, 7, 2))
    )

    val expectedResults = HashMap(
      Grade.EXCELLENT -> 1,
      Grade.SATISFACTORY -> 1,
      Grade.INSUFFICIENT -> 1,
      Grade.GOOD -> 1
    )

    val resultList = ResultsAnalysis.task5(results)
    resultList shouldEqual expectedResults
  }

  test("Task 6") {
    val results = List(
      Results(1, "Jane Doe", IndexedSeq(9, 10, 9, 6, 10, 9, 10, 10, 10, 9)),
      Results(2, "John Doe", IndexedSeq(8, 6, 5, 7, -1, -1, 8, 6, 8, 10)),
      Results(3, "Jake Doe", IndexedSeq(10, 9, 9, -1, 5, -1, 8, -1, 10, 9)),
      Results(4, "Jill Doe", IndexedSeq(10, 8, 2, 7, 10, 9, 7, 6, 7, 2))
    )

    val expectedResults = List(
      (1, 4), (2, 4), (3, 3), (4, 3), (5, 3), (6, 2), (7, 4), (8, 3), (9, 4), (10, 3)
    )

    val resultList = ResultsAnalysis.task6(results)
    resultList shouldEqual expectedResults
  }

  test("Task 7"){
    val results = List(
      Results(1, "Jane Doe", IndexedSeq(9, 10, 9, 6, 10, 9, 10, 10, 10, 9)),
      Results(2, "John Doe", IndexedSeq(8, 6, 5, 7, -1, -1, 8, 6, 8, 10)),
      Results(3, "Jake Doe", IndexedSeq(10, 9, 9, -1, 5, -1, 8, -1, 10, 9)),
      Results(4, "Jill Doe", IndexedSeq(10, 8, 2, 7, 10, 9, 7, 6, 7, 2))
    )

    val expectedResults = List(
      (1,9.25), (2,8.25), (3,6.25), (4,6.666666666666667), (5,8.333333333333334), (6,9.0), (7,8.25), (8,7.333333333333333), (9,8.75), (10,7.5)
    )

    val resultList = ResultsAnalysis.task7(results)
    resultList shouldEqual expectedResults

  }

}
