import org.scalatest.{Matchers, FlatSpec}

class MinesweeperTest extends FlatSpec with Matchers {

  it should "handle zero size board" in {
    Minesweeper.annotate(List.empty) should equal(List.empty)
  }

  it should "handle empty board" in {
    Minesweeper.annotate(List("   ",
                              "   ",
                              "   ")) should
      equal(List("   ",
                 "   ",
                 "   "))
  }

  it should "handle board full of mines" in {
    Minesweeper.annotate(List("***",
                              "***",
                              "***")) should
      equal(List("***",
                 "***",
                 "***"))
  }

  it should "handle surrounded" in {
    Minesweeper.annotate(List("***",
                              "* *",
                              "***")) should
      equal(List("***",
                 "*8*",
                 "***"))
  }

  it should "handle horizontal line" in {
    Minesweeper.annotate(List(" * * ")) should
      equal(List("1*2*1"))
  }


  it should "handle vertical line" in {
    Minesweeper.annotate(List(" ",
                              "*",
                              " ",
                              "*",
                              " ")) should
      equal(List("1",
                 "*",
                 "2",
                 "*",
                 "1"))
  }

  it should "handle cross" in {
    Minesweeper.annotate(List("  *  ",
                              "  *  ",
                              "*****",
                              "  *  ",
                              "  *  ")) should
      equal(List(" 2*2 ",
                 "25*52",
                 "*****",
                 "25*52",
                 " 2*2 "))
  }
}
