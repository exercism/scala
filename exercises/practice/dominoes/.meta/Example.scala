object Dominoes:

  type Domino = (Int, Int)

  def chain(dominoes: List[Domino]): Option[List[Domino]] = dominoes match
    case Nil           => Some(Nil)
    case first :: rest => extend(List(first), rest).find(chain => chain.head._1 == chain.last._2)

  /** Every way to lay all the remaining dominoes after the chain so far. */
  private def extend(chain: List[Domino], remaining: List[Domino]): Iterator[List[Domino]] =
    if remaining.isEmpty then Iterator(chain)
    else
      val end = chain.last._2
      remaining.indices.iterator.flatMap: i =>
        val rest = remaining.patch(i, Nil, 1)
        remaining(i) match
          case (a, b) if a == end => extend(chain :+ (a, b), rest)
          case (a, b) if b == end => extend(chain :+ (b, a), rest)
          case _                  => Iterator.empty
