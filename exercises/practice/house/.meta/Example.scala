object House:
  // Each subject with what it did to the one before it.
  private val subjects = Vector(
    "the house that Jack built.",
    "the malt that lay in",
    "the rat that ate",
    "the cat that killed",
    "the dog that worried",
    "the cow with the crumpled horn that tossed",
    "the maiden all forlorn that milked",
    "the man all tattered and torn that kissed",
    "the priest all shaven and shorn that married",
    "the rooster that crowed in the morn that woke",
    "the farmer sowing his corn that kept",
    "the horse and the hound and the horn that belonged to",
  )

  private def verse(n: Int): String = (n - 1 to 0 by -1).map(subjects).mkString("This is ", " ", "")

  // Every verse on its own line, followed by an empty line.
  def recite(start: Int, end: Int): String = (start to end).map(verse).mkString("", "\n", "\n\n")
