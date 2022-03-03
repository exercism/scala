object Dominoes {
  type Domino = (Int, Int)
  type Dominoes = List[Domino]

  def chain(dominoes: Dominoes): Option[Dominoes] = {
    val solutions = dominoes.permutations map createChain
    (solutions find (_.isDefined)).flatten
  }

  private def createChain(dominoes: Dominoes): Option[Dominoes] = {
    val matchingDomino: (Domino, Domino) => Option[Domino] = {
      case ((_, y1), (x2, y2)) =>
        if (y1 == x2) Some((x2, y2))
        else if (y1 == y2) Some((y2, x2))
        else None
    }
    def matchingDominoes(x: Domino, y: Domino): Option[(Domino, Domino)] = {
      matchingDomino(x, y) map ((x, _)) orElse {
        val sx = x.swap
        matchingDomino(sx, y) map ((sx, _))
      }
    }
    def appendDomino(mxs: Option[Dominoes], candidate: Domino): Option[Dominoes] =
      for {
        chainSoFar <- mxs
        lastDomino = chainSoFar.last
        nextDomino <- matchingDomino(lastDomino, candidate)
      } yield chainSoFar :+ nextDomino

    dominoes match {
      case Nil => Some(Nil)
      case ys@(x::xs) =>
        for {
          (rightDomino, leftDomino) <- matchingDominoes(ys.last, x)
          startChain = Option(List(leftDomino))
          result <- xs.foldLeft(startChain)(appendDomino) if result.last == rightDomino
        } yield result
      }
  }
}

