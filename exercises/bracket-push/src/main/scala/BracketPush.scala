import scala.annotation.tailrec

object BracketPush {

  private val closingToOpeningMap = Map(']'-> '[', '}' -> '{', ')' -> '(')
  private val openinBraces = closingToOpeningMap.values.toSet
  private val closingBraces = closingToOpeningMap.keySet


  def isPaired(s: String): Boolean = {
    @tailrec
    def go(stack: List[Char], remaining: List[Char]): Boolean = remaining match {
      case x :: xs if openinBraces.contains(x) => go(x :: stack, xs)
      case x :: xs if closingBraces.contains(x) =>
        val matches = for {
          openingBrace <- closingToOpeningMap.get(x)
          stackPop <- stack.headOption
        } yield {
          stackPop == openingBrace
        }
        if(matches.getOrElse(false)) go(stack.tail, xs)
        else false
      case x :: xs => go(stack, xs)
      case Nil => stack.isEmpty
    }
    go(Nil, s.toList)
  }
}
