# Recursion

```scala
object PhoneNumber {

  def clean(input: String): Option[String] = {
    cleanRecur(input.to(List), Nil)
  }

  @scala.annotation.tailrec
  private def cleanRecur(
      raw: List[Char],
      cleaned: List[Char]
  ): Option[String] = {
    (raw, cleaned.length) match {
      case (Nil, index) => {
        if (index == 10) return Some(cleaned.reverse.mkString) else None
      }
      case (head :: tail, index) if List(0, 3).contains(index) => {
        head.isDigit match {
          case true if (head > '1') => cleanRecur(tail, head :: cleaned)
          case true if (index == 0 && head == '1') => cleanRecur(tail, cleaned)
          case false if (index == 0 && List('+', '(', ' ').contains(head)) =>
            cleanRecur(tail, cleaned)
          case false if (index == 3 && List(')', ' ', '.').contains(head)) =>
            cleanRecur(tail, cleaned)
          case _ => None
        }
      }
      case (head :: tail, index) => {
        head.isDigit match {
          case true => cleanRecur(tail, head :: cleaned)
          case false if (index == 6 && List(' ', '-', '.').contains(head)) =>
            cleanRecur(tail, cleaned)
          case false if (head == ' ') => cleanRecur(tail, cleaned)
          case _                      => None
        }
      }
    }
  }
}
```
