def readCSV(): List[String] = {
  List(
    "ID,Name,1,2,3,4,5,6,7,8,9,10",
    "1,Jane Doe,9,10,9,6,10,9,10,10,10,9",
    "2,John Doe,8,6,5,7,-1,-1,8,6,8,10",
    "3,Jake Doe,10,9,9,-1,5,-1,8,-1,10,9",
    "4,Jill Doe,10,8,2,7,10,9,7,6,7,2",
  )
}

case class Results(id: Int, name: String, points: IndexedSeq[Int])

enum Grade:
  case EXCELLENT, GOOD, SATISFACTORY, SUFFICIENT, INSUFFICIENT

object ResultsAnalysis {

  def task1(lines: List[String]): List[Results] =
    //TODO: Implement this method
    ???

  def task2(resultList: List[Results]): Map[String, Int] =
    //TODO: Implement this method
    ???

  def task3(nSolvedPerStnd: Map[String, Int]): (Set[String], Set[String]) =
    //TODO: Implement this method
    ???

  def task4(resultList: List[Results]): Map[String, Grade] =
    //TODO: Implement this method
    ???

  def task5(resultList: List[Results]): Map[Grade, Int] =
    //TODO: Implement this method
    ???

  def task6(resultList: List[Results]): List[(Int, Int)] =
    //TODO: Implement this method
    ???

  def task7(resultList: List[Results]):  List[(Int, Double)] =
    //TODO: Implement this method
    ???

  def main(args: Array[String]): Unit = {
    val lines = readCSV()

    // Task 1
    val resultList: List[Results] = task1(lines)

    // Task 2
    val nSolvedPerStnd = task2(resultList)

    // Task 3
    val sufficientSolved = task3(nSolvedPerStnd)

    // Task 4
    val grades = task4(resultList)

    //Task 5
    val nStudentsWithGrade = task5(resultList)

    //Task 6
    val nSolvedPerAssnmt = task6(resultList)

    //Task 7
    val avrgPointsPerAssnmt = task7(resultList)
  }

  private def computeGrade(points: IndexedSeq[Int]): Grade = {
    if (points.count(p => p >= 3) < 8) then Grade.INSUFFICIENT
    else {
      val avrg = points.sorted.drop(2).sum / 8
      if (avrg < 5.0) then Grade.INSUFFICIENT
      else if (avrg < 6.5) then Grade.SUFFICIENT
      else if (avrg < 8.0) then Grade.SATISFACTORY
      else if (avrg < 9.0) then Grade.GOOD
      else Grade.EXCELLENT
    }
  }
}