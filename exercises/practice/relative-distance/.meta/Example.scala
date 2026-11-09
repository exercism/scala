import scala.annotation.tailrec

class RelativeDistance(familyTree: Map[String, List[String]]):

  /** Everyone a person is one step away from: their parents, children and siblings. */
  private val relatives: Map[String, Set[String]] =
    val links =
      for
        (parent, children) <- familyTree.toList
        child              <- children
        relative           <- parent :: children.filterNot(_ == child)
        link               <- List(child -> relative, relative -> child)
      yield link
    links.groupMapReduce(_._1)((_, relative) => Set(relative))(_ ++ _)

  /** A breadth-first search, one generation of the search frontier at a time. */
  def degreeOfSeparation(personA: String, personB: String): Option[Int] =
    @tailrec
    def search(frontier: Set[String], visited: Set[String], distance: Int): Option[Int] =
      if frontier.contains(personB) then Some(distance)
      else if frontier.isEmpty then None
      else
        val next = frontier.flatMap(relatives.getOrElse(_, Set.empty)) -- visited
        search(next, visited ++ next, distance + 1)

    search(Set(personA), Set(personA), 0)
