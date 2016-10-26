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
    Connect(mkBoard(lines)).result should be (None)
  }

  test("black single item board") {
    pending
    val lines = List("X")
    Connect(mkBoard(lines)).result should be (Some(Color.Black))
  }

  test("white single item board") {
    pending
    val lines = List("O")
    Connect(mkBoard(lines)).result should be (Some(Color.White))
  }

  test("convoluted path") {
    pending
    val lines = List(". X X . ."
                    ," X . X . X"
                    ,"  . X . X ."
                    ,"   . X X . ."
                    ,"    O O O O O")
    Connect(mkBoard(lines)).result should be (Some(Color.Black))
  }

  test("rectangle - black wins") {
    pending
    val lines = List(". O . ."
                    ," O X X X"
                    ,"  O X O ."
                    ,"   X X O X"
                    ,"    . O X .")
    Connect(mkBoard(lines)).result should be (Some(Color.Black))
  }

  test("rectangle - white wins") {
    pending
    val lines = List(". O . ."
                    ," O X X X"
                    ,"  O O O ."
                    ,"   X X O X"
                    ,"    . O X .")
    Connect(mkBoard(lines)).result should be (Some(Color.White))
  }

  test("spiral - black wins") {
    pending
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
    pending
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
