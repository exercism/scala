object FoodChain:

  private val Animals = Vector(
    "fly"    -> "",
    "spider" -> "It wriggled and jiggled and tickled inside her.\n",
    "bird"   -> "How absurd to swallow a bird!\n",
    "cat"    -> "Imagine that, to swallow a cat!\n",
    "dog"    -> "What a hog, to swallow a dog!\n",
    "goat"   -> "Just opened her throat and swallowed a goat!\n",
    "cow"    -> "I don't know how she swallowed a cow!\n",
    "horse"  -> "She's dead, of course!\n",
  )

  def recite(startVerse: Int, endVerse: Int): String = (startVerse to endVerse).map(n => verse(n - 1) + "\n").mkString

  private def verse(n: Int): String =
    val (animal, remark) = Animals(n)
    val opening          = s"I know an old lady who swallowed a $animal.\n$remark"
    if n == Animals.length - 1 then opening
    else opening + (n until 0 by -1).map(chase).mkString + "I don't know why she swallowed the fly. Perhaps she'll die.\n"

  private def chase(i: Int): String =
    val predator = Animals(i)._1
    val prey     = if i == 2 then "spider that wriggled and jiggled and tickled inside her" else Animals(i - 1)._1
    s"She swallowed the $predator to catch the $prey.\n"
