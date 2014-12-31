object House {
  private val parts = Vector(("lay in", "the house that Jack built"),
    ("ate", "the malt"),
    ("killed", "the rat"),
    ("worried", "the cat"),
    ("tossed", "the dog"),
    ("milked", "the cow with the crumpled horn"),
    ("kissed", "the maiden all forlorn"),
    ("married", "the man all tattered and torn"),
    ("woke", "the priest all shaven and shorn"),
    ("kept", "the rooster that crowed in the morn"),
    ("belonged to", "the farmer sowing his corn"),
    ("", "the horse and the hound and the horn"))

  private def verse(n: Int) = {
    "This is " + parts(n)._2 +
      (for {
        i <- List.range(0, n).reverse
      } yield {"\nthat %s %s".format(parts(i)._1, parts(i)._2)}).mkString +
    ".\n\n"
  }

  lazy val rhyme: String = (for {i <- List.range(0, parts.length)} yield {verse(i)}).mkString
}
