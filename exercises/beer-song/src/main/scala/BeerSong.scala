object BeerSong {
  def verse(i: Int): String =
    s"""$i bottle${if(i>1) "s"} of beer on the wall, $i bottle${if(i>1) "s"} of beer.
       |Take one down and pass it around, ${if(i == 1) "no more" else i -1} bottle${if(i!=2) "s"} of beer on the wall.
       |""".stripMargin

  def verses(start: Int, finish: Int): String = (start to (finish - 1)).map(verse).mkString("\n")
}
