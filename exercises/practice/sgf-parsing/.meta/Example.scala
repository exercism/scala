import scala.util.parsing.combinator.RegexParsers

object Sgf extends RegexParsers {

  case class Node(properties: SgfProperties, children: List[Node] = List())

  override val skipWhitespace = false

  type SgfProperties = Map[String, List[String]]

  private implicit def parseResultToOption[T](parseResult: ParseResult[T]): Option[T] =
    parseResult map (Some(_)) getOrElse None

  def parseSgf(text: String): Option[Node] =
    parseAll(sgfGameTree, text)

  private val sgfGameTree: Parser[Node] =
    ("(" ~ rep1(sgfProperties) ~ rep(sgfGameTree) ~ ")") ^^ {
      case _ ~ (rootNode::subNodes) ~ subTrees ~ _ =>
        val subNodeForest: List[Node] = subNodes map (Node(_))
        Node(rootNode, subNodeForest ++ subTrees)
    }

  private val sgfProperties: Parser[SgfProperties] =
    ";" ~ (sgfProperty | emptySgfNode) ^^ {
      case _ ~ sgfProps => sgfProps
    }

  private val emptySgfNode: Parser[SgfProperties] =
    "" ^^ const(Map())

  private def sgfProperty: Parser[SgfProperties] =
    propIdent ~ propValues ^^ {
      case identifier ~ values => Map(identifier -> values)
    }

  private val propIdent: Parser[String] = "[A-Z]".r

  private val propValues: Parser[List[String]] = rep1(propValue)

  private val propValue: Parser[String] =
    "[" ~ rep1(propValuePart) ~ "]" ^^ {
      case _ ~ values ~ _ => values.mkString
    }

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
