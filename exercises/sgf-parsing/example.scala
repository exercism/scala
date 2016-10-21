import scala.util.parsing.combinator.RegexParsers

object Sgf extends RegexParsers {

  override val skipWhitespace = false

  type Tree[A] = Node[A] // to separate the type from the constructor, cf. Haskell's Data.Tree
  type Forest[A] = List[Tree[A]]
  case class Node[A](rootLabel: A, subForest: Forest[A] = List())

  // A tree of nodes.
  type SgfTree = Tree[SgfNode]

  // A node is a property list, each key can only occur once.
  // Keys may have multiple values associated with them.
  type SgfNode = Map[String, List[String]]

  private implicit def parseResultToOption[T](parseResult: ParseResult[T]): Option[T] =
    parseResult map (Some(_)) getOrElse None

  def parseSgf(text: String): Option[SgfTree] =
    parseAll(sgfGameTree, text)

  private def sgfGameTree: Parser[SgfTree] =
    ("(" ~ rep1(sgfNode) ~ rep(sgfGameTree) ~ ")") ^^ {
      case _ ~ (rootNode::subNodes) ~ subTrees ~ _ =>
        val subNodeForest: List[SgfTree] = subNodes map (Node(_))
        Node(rootNode, subNodeForest ++ subTrees)
    } named "sgfGameTree"

  private def sgfNode: Parser[SgfNode] =
    ";" ~ (sgfProperty | emptySgfNode) ^^ {
      case _ ~ sgfNode => sgfNode
    } named "sgfNode"

  private def emptySgfNode: Parser[SgfNode] =
    "" ^^ { _ => Map() }

  private def sgfProperty: Parser[SgfNode] =
    propIdent ~ propValues ^^ {
      case identifier ~ values => Map(identifier -> values)
    } named "sgfProperty"

  private def propIdent: Parser[String] = "[A-Z]".r named "propIdent"

  private def propValues: Parser[List[String]] = rep1(propValue)

  private def propValue: Parser[String] =
    "[" ~ rep1(propValuePart) ~ "]" ^^ {
      case _ ~ values ~ _ => values mkString
    } named "propValue"

  private def propValuePart: Parser[String] = {
    implicit class AsStringParser(self: String) { def p: Parser[String] = self }
    val ignore = const("") _

    def quotedClosingBracket: Parser[String] = "\\]".p map const("]")
    def quotedNewline: Parser[String] = "\\\n".p map ignore
    def quotedBackslash: Parser[String] = "\\\\".p map const("\\")
    def backslash: Parser[String] = "\\".p map ignore
    def whitespace: Parser[String] = """\s""".r map const(" ")
    def ident: Parser[String] = "[^]]".r map identity

    quotedClosingBracket | quotedNewline | quotedBackslash | backslash | whitespace | ident
  }

  private def const[A](a: A)(ignore: Any) = a
}
