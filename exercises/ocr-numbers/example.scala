import scala.annotation.tailrec

object OcrNumbers {

  def convert(grid: List[String]): String = {
    val validGridSize = grid.length % 4 != 0 || !grid.forall(_.length % 3 == 0)
    if (validGridSize)
      "?"
    else {
      ocrReadoutLines(grid).map(ocrLine => {
        val iterator = ocrLine.iterator
        val line0 = iterator.next()
        val line1 = iterator.next()
        val line2 = iterator.next()
        val line3 = iterator.next()

        toDigits(line0, line1, line2, line3, "")
      }).mkString(",")
    }
  }

  private def ocrReadoutLines(grid: List[String]) = grid.grouped(4)

  @tailrec
  private def toDigits(line0: String, line1: String,
                       line2: String, line3: String,
                       acc: String): String = {
    if (line0.isEmpty || line1.isEmpty || line2.isEmpty || line3.isEmpty) acc
    else {
      val (l0Head, l0Tail) = line0.splitAt(3)
      val (l1Head, l1Tail) = line1.splitAt(3)
      val (l2Head, l2Tail) = line2.splitAt(3)
      val (l3Head, l3Tail) = line3.splitAt(3)

      val ocrDigit = List(l0Head, l1Head, l2Head, l3Head)
      val digit = fontToDigit.getOrElse(ocrDigit, '?')

      toDigits(l0Tail, l1Tail, l2Tail, l3Tail, acc + digit)
    }
  }

  private lazy val fontToDigit = Map(List(" _ "
    , "| |"
    , "|_|"
    , "   ") -> '0',
    List("   "
      , "  |"
      , "  |"
      , "   ") -> '1',
    List(" _ "
      , " _|"
      , "|_ "
      , "   ") -> '2',
    List(" _ "
      , " _|"
      , " _|"
      , "   ") -> '3',
    List("   "
      , "|_|"
      , "  |"
      , "   ") -> '4',
    List(" _ "
      , "|_ "
      , " _|"
      , "   ") -> '5',
    List(" _ "
      , "|_ "
      , "|_|"
      , "   ") -> '6',
    List(" _ "
      , "  |"
      , "  |"
      , "   ") -> '7',
    List(" _ "
      , "|_|"
      , "|_|"
      , "   ") -> '8',
    List(" _ "
      , "|_|"
      , " _|"
      , "   ") -> '9')
}
