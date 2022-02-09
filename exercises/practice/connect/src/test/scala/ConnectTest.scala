import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 1.1.0 */
class ConnectTest extends AnyFunSuite with Matchers {

  // Filter readable board into valid input
  private def mkBoard(lines: List[String]): List[String] =
    lines.map(l => l.filter(!_.isSpaceChar))

  test("an empty board has no winner") {
    Connect(
      mkBoard(
        List(". . . . .",
             " . . . . .",
             "  . . . . .",
             "   . . . . .",
             "    . . . . ."))).winner should be(None)
  }

  test("X can win on a 1x1 board") {
    pending
    Connect(mkBoard(List("X"))).winner should be(Some(Color.Black))
  }

  test("O can win on a 1x1 board") {
    pending
    Connect(mkBoard(List("O"))).winner should be(Some(Color.White))
  }

  test("only edges does not make a winner") {
    pending
    Connect(mkBoard(List("O O O X", " X . . X", "  X . . X", "   X O O O"))).winner should be(
      None)
  }

  test("illegal diagonal does not make a winner") {
    pending
    Connect(
      mkBoard(List("X O . .",
                   " O X X X",
                   "  O X O .",
                   "   . O X .",
                   "    X X O O"))).winner should be(None)
  }

  test("nobody wins crossing adjacent angles") {
    pending
    Connect(
      mkBoard(List("X . . .",
                   " . X O .",
                   "  O . X O",
                   "   . O . X",
                   "    . . O ."))).winner should be(None)
  }

  test("X wins crossing from left to right") {
    pending
    Connect(
      mkBoard(List(". O . .",
                   " O X X X",
                   "  O X O .",
                   "   X X O X",
                   "    . O X ."))).winner should be(Some(Color.Black))
  }

  test("O wins crossing from top to bottom") {
    pending
    Connect(
      mkBoard(List(". O . .",
                   " O X X X",
                   "  O O O .",
                   "   X X O X",
                   "    . O X ."))).winner should be(Some(Color.White))
  }

  test("X wins using a convoluted path") {
    pending
    Connect(
      mkBoard(
        List(". X X . .",
             " X . X . X",
             "  . X . X .",
             "   . X X . .",
             "    O O O O O"))).winner should be(Some(Color.Black))
  }

  test("X wins using a spiral path") {
    pending
    Connect(
      mkBoard(List(
        "O X X X X X X X X",
        " O X O O O O O O O",
        "  O X O X X X X X O",
        "   O X O X O O O X O",
        "    O X O X X X O X O",
        "     O X O O O X O X O",
        "      O X X X X X O X O",
        "       O O O O O O O X O",
        "        X X X X X X X X O"
      ))).winner should be(Some(Color.Black))
  }
}
