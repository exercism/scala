import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class ConnectTest extends AnyFunSuite with Matchers:

  // The canonical boards are indented like the game's rhombus; Connect takes them without spaces.
  private def mkBoard(lines: List[String]): List[String] = lines.map(_.filterNot(_.isSpaceChar))

  test("an empty board has no winner"):
    val board = List(
      ". . . . .",
      " . . . . .",
      "  . . . . .",
      "   . . . . .",
      "    . . . . .",
    )
    Connect(mkBoard(board)).winner should be(None)

  test("X can win on a 1x1 board"):
    pending
    val board = List(
      "X",
    )
    Connect(mkBoard(board)).winner should be(Some(Color.Black))

  test("O can win on a 1x1 board"):
    pending
    val board = List(
      "O",
    )
    Connect(mkBoard(board)).winner should be(Some(Color.White))

  test("only edges does not make a winner"):
    pending
    val board = List(
      "O O O X",
      " X . . X",
      "  X . . X",
      "   X O O O",
    )
    Connect(mkBoard(board)).winner should be(None)

  test("illegal diagonal does not make a winner"):
    pending
    val board = List(
      "X O . .",
      " O X X X",
      "  O X O .",
      "   . O X .",
      "    X X O O",
    )
    Connect(mkBoard(board)).winner should be(None)

  test("nobody wins crossing adjacent angles"):
    pending
    val board = List(
      "X . . .",
      " . X O .",
      "  O . X O",
      "   . O . X",
      "    . . O .",
    )
    Connect(mkBoard(board)).winner should be(None)

  test("X wins crossing from left to right"):
    pending
    val board = List(
      ". O . .",
      " O X X X",
      "  O X O .",
      "   X X O X",
      "    . O X .",
    )
    Connect(mkBoard(board)).winner should be(Some(Color.Black))

  test("X wins with left-hand dead end fork"):
    pending
    val board = List(
      ". . X .",
      " X X . .",
      "  . X X X",
      "   O O O O",
    )
    Connect(mkBoard(board)).winner should be(Some(Color.Black))

  test("X wins with right-hand dead end fork"):
    pending
    val board = List(
      ". . X X",
      " X X . .",
      "  . X X .",
      "   O O O O",
    )
    Connect(mkBoard(board)).winner should be(Some(Color.Black))

  test("O wins crossing from top to bottom"):
    pending
    val board = List(
      ". O . .",
      " O X X X",
      "  O O O .",
      "   X X O X",
      "    . O X .",
    )
    Connect(mkBoard(board)).winner should be(Some(Color.White))

  test("X wins using a convoluted path"):
    pending
    val board = List(
      ". X X . .",
      " X . X . X",
      "  . X . X .",
      "   . X X . .",
      "    O O O O O",
    )
    Connect(mkBoard(board)).winner should be(Some(Color.Black))

  test("X wins using a spiral path"):
    pending
    val board = List(
      "O X X X X X X X X",
      " O X O O O O O O O",
      "  O X O X X X X X O",
      "   O X O X O O O X O",
      "    O X O X X X O X O",
      "     O X O O O X O X O",
      "      O X X X X X O X O",
      "       O O O O O O O X O",
      "        X X X X X X X X O",
    )
    Connect(mkBoard(board)).winner should be(Some(Color.Black))
