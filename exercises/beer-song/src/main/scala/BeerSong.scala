
object BeerSong {

  def bottlesOfBeer(line: Int) =
    if(line > 1) line.toString + " bottles of beer"
    else if(line == 1) "1 bottle of beer"
    else "No more bottles of beer"

  def beersOnTheWall(line: Int) = s"${bottlesOfBeer(line)} on the wall"

  def afterPassing(line: Int) = {
    val one = if(line == 1) "it" else "one"
    if (line > 0) s"Take $one down and pass it around"
    else "Go to the store and buy some more"
  }

  def nextCount(line: Int) = (line + 99) % 100

  def lyricFor(line: Int) = {
    s"${beersOnTheWall(line)}, ${bottlesOfBeer(line).toLowerCase}.\n"+
      s"${afterPassing(line)}, ${beersOnTheWall(nextCount(line)).toLowerCase}.\n"
  }

  def recite(start: Int, count: Int) =
    1.until(count)
      .scan(start)((line, i) => nextCount(line))
      .map(lyricFor)
      .mkString("\n")

}