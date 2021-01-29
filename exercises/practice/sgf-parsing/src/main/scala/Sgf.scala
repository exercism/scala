import scala.util.parsing.combinator.RegexParsers

object Sgf extends RegexParsers {

  type Tree[A] = Node[A] // to separate the type from the constructor, cf. Haskell's Data.Tree
  type Forest[A] = List[Tree[A]]
  case class Node[A](rootLabel: A, subForest: Forest[A] = List())

  // A tree of nodes.
  type SgfTree = Tree[SgfNode]

  // A node is a property list, each key can only occur once.
  // Keys may have multiple values associated with them.
  type SgfNode = Map[String, List[String]]

  def parseSgf(text: String): Option[SgfTree] = ???
}
