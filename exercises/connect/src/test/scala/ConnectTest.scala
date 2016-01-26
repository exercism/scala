import org.scalatest.{Matchers, FunSuite}

class ConnectTest extends FunSuite with Matchers {
  // Filter readable board into valid input
  private def mkBoard(lines: List[String]): List[String] =
    lines.map(l => l.filter(!_.isSpaceChar))

  test("empty board") {
    val lines = List(". . . . ."
                    ," . . . . ."
                    ,"  . . . . ."
                    ,"   . . . . ."
                    ,"    . . . . .")
    Connect(lines).result should be (None)
  }

  test("black single item board") {
    val lines = List("X")
    Connect(mkBoard(lines)).result should be (Some(Color.Black))
  }

  test("white single item board") {
    val lines = List("O")
    Connect(mkBoard(lines)).result should be (Some(Color.White))
  }

  test("convoluted path") {
    val lines = List(". X X . ."
                    ," X . X . X"
                    ,"  . X . X ."
                    ,"   . X X . ."
                    ,"    O O O O O")
    Connect(mkBoard(lines)).result should be (Some(Color.Black))
  }

  test("rectangle - black wins") {
    val lines = List(". O . ."
                    ," O X X X"
                    ,"  O X O ."
                    ,"   X X O X"
                    ,"    . O X .")
    Connect(mkBoard(lines)).result should be (Some(Color.Black))
  }

  test("rectangle - white wins") {
    val lines = List(". O . ."
                    ," O X X X"
                    ,"  O O O ."
                    ,"   X X O X"
                    ,"    . O X .")
    Connect(mkBoard(lines)).result should be (Some(Color.White))
  }

  test("spiral - black wins") {
    val lines = List("OXXXXXXXX"
                    ,"OXOOOOOOO"
                    ,"OXOXXXXXO"
                    ,"OXOXOOOXO"
                    ,"OXOXXXOXO"
                    ,"OXOOOXOXO"
                    ,"OXXXXXOXO"
                    ,"OOOOOOOXO"
                    ,"XXXXXXXXO")
    Connect(mkBoard(lines)).result should be (Some(Color.Black))
  }

  test("spiral - none") {
    val lines = List("OXXXXXXXX"
                    ,"OXOOOOOOO"
                    ,"OXOXXXXXO"
                    ,"OXOXOOOXO"
                    ,"OXOX.XOXO"
                    ,"OXOOOXOXO"
                    ,"OXXXXXOXO"
                    ,"OOOOOOOXO"
                    ,"XXXXXXXXO")
    Connect(mkBoard(lines)).result should be (None)
  }
}
