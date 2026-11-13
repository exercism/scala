import scala.util.parsing.combinator.RegexParsers

object Sgf extends RegexParsers:

  type Tree[A]   = Node[A] // to separate the type from the constructor, cf. Haskell's Data.Tree
  type Forest[A] = List[Tree[A]]
  case class Node[A](rootLabel: A, subForest: Forest[A] = List())

  // A tree of nodes.
  type SgfTree = Tree[SgfNode]

  // A node is a property list, each key can only occur once.
  // Keys may have multiple values associated with them.
  type SgfNode = Map[String, List[String]]

  override val skipWhitespace = false

  def parseSgf(text: String): Option[SgfTree] = parseAll(gameTree, text) match
    case Success(tree, _) => Some(tree)
    case _                => None

  // A sequence of nodes is a chain: each node is the only child of the one before it.
  private def gameTree: Parser[SgfTree] = ("(" ~> rep1(node) ~ rep(gameTree) <~ ")") ^^ { case nodes ~ children =>
    nodes.init.foldRight(Node(nodes.last, children))((properties, child) => Node(properties, List(child)))
  }

  private def node: Parser[SgfNode] = ";" ~> rep(property) ^^ (_.toMap)

  private def property: Parser[(String, List[String])] = "[A-Z]+".r ~ rep1(value) ^^ { case key ~ values => key -> values }

  private def value: Parser[String] = "[" ~> rep(valuePart) <~ "]" ^^ (_.mkString)

  private def valuePart: Parser[String] =
    "\\\n" ^^^ "" |             // an escaped newline is removed
      """\\\s""".r ^^^ " " |    // other escaped whitespace becomes a space
      """\\.""".r ^^ (_.tail) | // any other escaped character is kept as it is
      "\n" |                    // a newline stays
      """\s""".r ^^^ " " |      // other whitespace becomes a space
      """[^\]\\]""".r
