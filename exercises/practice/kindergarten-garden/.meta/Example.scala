import Plant.Plant

class Garden(children: List[String], plantsStr: String) {
  private val plantRows = plantsStr.split("\\r?\\n")
  private val chunkedRows = plantRows.map(s => s.grouped(2).toList).toList
  private val childPlants = chunkedRows.head.zip(chunkedRows.tail.head).map{case (s1, s2) => s1 + s2}
  private val sortedChildren = children.sorted
  private val gardenMap = sortedChildren.zip(childPlants).toMap

  def plants(child: String): List[Plant] = {
    val plantChars = gardenMap.getOrElse(child, "")
    plantChars.map(c => Plant.withName(c.toString)).toList
  }
}

object Garden {
  def defaultGarden(plantsStr: String): Garden =
    new Garden(List("Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny",
      "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"), plantsStr)

  def apply(children: List[String], plantsStr: String): Garden = new Garden(children, plantsStr)
}

object Plant extends Enumeration  {
  type Plant = Value

  val Grass = Value("G")
  val Clover = Value("C")
  val Radishes = Value("R")
  val Violets = Value("V")
}
