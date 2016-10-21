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

  override val skipWhitespace = false

  private implicit def parseResultToOption[T](parseResult: ParseResult[T]): Option[T] =
    parseResult map (Some(_)) getOrElse None

  def parseSgf(text: String): Option[SgfTree] =
    parseAll(sgfGameTree, text)

  private val sgfGameTree: Parser[SgfTree] =
    ("(" ~ rep1(sgfNode) ~ rep(sgfGameTree) ~ ")") ^^ {
      case _ ~ (rootNode::subNodes) ~ subTrees ~ _ =>
        val subNodeForest: List[SgfTree] = subNodes map (Node(_))
        Node(rootNode, subNodeForest ++ subTrees)
    } named "sgfGameTree"

  private val sgfNode: Parser[SgfNode] =
    ";" ~ (sgfProperty | emptySgfNode) ^^ {
      case _ ~ sgfNode => sgfNode
    } named "sgfNode"

  private val emptySgfNode: Parser[SgfNode] =
    "" ^^ const(Map())

  private def sgfProperty: Parser[SgfNode] =
    propIdent ~ propValues ^^ {
      case identifier ~ values => Map(identifier -> values)
    } named "sgfProperty"

  private val propIdent: Parser[String] = "[A-Z]".r named "propIdent"

  private val propValues: Parser[List[String]] = rep1(propValue)

  private val propValue: Parser[String] =
    "[" ~ rep1(propValuePart) ~ "]" ^^ {
      case _ ~ values ~ _ => values mkString
    } named "propValue"

  private val propValuePart: Parser[String] = {
    implicit class AsStringParser(self: String) { def p: Parser[String] = self }
    val ignore = const("") _

    val escapedNewline: Parser[String] = "\\\n".p ^^ ignore
    val escapedChar: Parser[String] = """\\.""".r ^^ (_.takeRight(1))
    val whitespace: Parser[String] = """\s""".r ^^ const(" ")
    val ident: Parser[String] = "[^]]".r

    escapedNewline | escapedChar | whitespace | ident
  }

  private def const[A](a: A)(ignore: Any) = a
}
