object FoodChain {
  private lazy val parts = Vector(("fly", ""),
    ("spider", "It wriggled and jiggled and tickled inside her.\n"),
    ("bird", "How absurd to swallow a bird!\n"),
    ("cat", "Imagine that, to swallow a cat!\n"),
    ("dog", "What a hog, to swallow a dog!\n"),
    ("goat", "Just opened her throat and swallowed a goat!\n"),
    ("cow", "I don't know how she swallowed a cow!\n"),
    ("horse", "She's dead, of course!\n\n"))

  private def animal(i: Int) = parts(i)._1
  private def result(i: Int) = parts(i)._2
  private def finalVerse(i: Int) = i == parts.length - 1

  private def verseInternal(n: Int) = {
    "I know an old lady who swallowed a %s.\n".format(animal(n)) +
      (if (finalVerse(n)) result(n)
      else {
        result(n) +
          (for {
            i <- List.range(0, n).reverse
          } yield {
            if ("bird".equals(animal(i + 1)))
              "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"
            else
              "She swallowed the %s to catch the %s.\n".format(animal(i + 1), animal(i))
          }).mkString +
          result(0) + "I don't know why she swallowed the fly. Perhaps she'll die.\n\n"
      })
  }

  def recite(n: Int): String = verseInternal(n - 1)

  def recite(n: Int, m: Int): String = (for {i <- List.range(n - 1, m)} yield {verseInternal(i)}).mkString
}
