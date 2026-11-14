import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class HouseTest extends AnyFunSuite with Matchers:

  test("verse one - the house that jack built"):
    val verses = List(
      "This is the house that Jack built.",
    )
    House.recite(1, 1) should be(verses.mkString("", "\n", "\n\n"))

  test("verse two - the malt that lay"):
    pending
    val verses = List(
      "This is the malt that lay in the house that Jack built.",
    )
    House.recite(2, 2) should be(verses.mkString("", "\n", "\n\n"))

  test("verse three - the rat that ate"):
    pending
    val verses = List(
      "This is the rat that ate the malt that lay in the house that Jack built.",
    )
    House.recite(3, 3) should be(verses.mkString("", "\n", "\n\n"))

  test("verse four - the cat that killed"):
    pending
    val verses = List(
      "This is the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    )
    House.recite(4, 4) should be(verses.mkString("", "\n", "\n\n"))

  test("verse five - the dog that worried"):
    pending
    val verses = List(
      "This is the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    )
    House.recite(5, 5) should be(verses.mkString("", "\n", "\n\n"))

  test("verse six - the cow with the crumpled horn"):
    pending
    val verses = List(
      "This is the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    )
    House.recite(6, 6) should be(verses.mkString("", "\n", "\n\n"))

  test("verse seven - the maiden all forlorn"):
    pending
    val verses = List(
      "This is the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    )
    House.recite(7, 7) should be(verses.mkString("", "\n", "\n\n"))

  test("verse eight - the man all tattered and torn"):
    pending
    val verses = List(
      "This is the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    )
    House.recite(8, 8) should be(verses.mkString("", "\n", "\n\n"))

  test("verse nine - the priest all shaven and shorn"):
    pending
    val verses = List(
      "This is the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    )
    House.recite(9, 9) should be(verses.mkString("", "\n", "\n\n"))

  test("verse 10 - the rooster that crowed in the morn"):
    pending
    val verses = List(
      "This is the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    )
    House.recite(10, 10) should be(verses.mkString("", "\n", "\n\n"))

  test("verse 11 - the farmer sowing his corn"):
    pending
    val verses = List(
      "This is the farmer sowing his corn that kept the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    )
    House.recite(11, 11) should be(verses.mkString("", "\n", "\n\n"))

  test("verse 12 - the horse and the hound and the horn"):
    pending
    val verses = List(
      "This is the horse and the hound and the horn that belonged to the farmer sowing his corn that kept the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    )
    House.recite(12, 12) should be(verses.mkString("", "\n", "\n\n"))

  test("multiple verses"):
    pending
    val verses = List(
      "This is the cat that killed the rat that ate the malt that lay in the house that Jack built.",
      "This is the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
      "This is the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
      "This is the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
      "This is the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    )
    House.recite(4, 8) should be(verses.mkString("", "\n", "\n\n"))

  test("full rhyme"):
    pending
    val verses = List(
      "This is the house that Jack built.",
      "This is the malt that lay in the house that Jack built.",
      "This is the rat that ate the malt that lay in the house that Jack built.",
      "This is the cat that killed the rat that ate the malt that lay in the house that Jack built.",
      "This is the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
      "This is the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
      "This is the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
      "This is the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
      "This is the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
      "This is the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
      "This is the farmer sowing his corn that kept the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
      "This is the horse and the hound and the horn that belonged to the farmer sowing his corn that kept the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.",
    )
    House.recite(1, 12) should be(verses.mkString("", "\n", "\n\n"))
