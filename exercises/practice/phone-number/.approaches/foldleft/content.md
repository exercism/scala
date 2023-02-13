# `foldLeft()`

```scala
object PhoneNumber {

  def clean(input: String): Option[String] = {
    input
      .foldLeft((List[Char](), 0))((tup, head) =>
        tup._2 match {
          case index if (List(0, 3).contains(index)) => {
            head.isDigit match {
              case true if (head > '1') => (head :: tup._1, index + 1)
              case true if (index == 0 && head == '1') =>
                tup
              case false
                  if (index == 0 && List('+', '(', ' ').contains(head)) =>
                tup
              case false
                  if (index == 3 && List(')', ' ', '.').contains(head)) =>
                tup
              case _ => (Nil, -100)
            }
          }
          case index => {
            head.isDigit match {
              case true => (head :: tup._1, index + 1)
              case false
                  if (index == 6 && List(' ', '-', '.').contains(head)) =>
                tup
              case false if (head == ' ') => tup
              case _                      => (Nil, -100)
            }
          }
        }
      ) match {
      case (output, index) =>
        if (index == 10) Some(output.reverse.mkString) else None
    }
  }
}
```

