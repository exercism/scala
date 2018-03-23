object House {
  private val parts = Vector(("lay in", "the house that Jack built."),
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

  private def reciteInternal(n: Int) = {
    "This is " + parts(n)._2 +
      (for {
        i <- List.range(0, n).reverse
      } yield {" that %s %s".format(parts(i)._1, parts(i)._2)}).mkString + "\n"
  }

  def recite(n: Int): String = reciteInternal(n - 1) + "\n"

  def recite(n: Int, m: Int): String = (for {i <- List.range(n - 1, m)} yield {
    reciteInternal(i)
  }).mkString + "\n"
}
