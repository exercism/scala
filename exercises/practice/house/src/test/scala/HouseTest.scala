import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


/** @version 2.2.0 */
class HouseTest extends AnyFunSuite with Matchers {

  test("verse one - the house that jack built") {
    House.recite(1, 1) should be("""This is the house that Jack built.

""")
  }

  test("verse two - the malt that lay") {
    pending
    House.recite(2, 2) should be(
      """This is the malt that lay in the house that Jack built.

""")
  }

  test("verse three - the rat that ate") {
    pending
    House.recite(3, 3) should be(
      """This is the rat that ate the malt that lay in the house that Jack built.

""")
  }

  test("verse four - the cat that killed") {
    pending
    House.recite(4, 4) should be(
      """This is the cat that killed the rat that ate the malt that lay in the house that Jack built.

""")
  }

  test("verse five - the dog that worried") {
    pending
    House.recite(5, 5) should be(
      """This is the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.

""")
  }

  test("verse six - the cow with the crumpled horn") {
    pending
    House.recite(6, 6) should be(
      """This is the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.

""")
  }

  test("verse seven - the maiden all forlorn") {
    pending
    House.recite(7, 7) should be(
      """This is the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.

""")
  }

  test("verse eight - the man all tattered and torn") {
    pending
    House.recite(8, 8) should be(
      """This is the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.

""")
  }

  test("verse nine - the priest all shaven and shorn") {
    pending
    House.recite(9, 9) should be(
      """This is the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.

""")
  }

  test("verse 10 - the rooster that crowed in the morn") {
    pending
    House.recite(10, 10) should be(
      """This is the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.

""")
  }

  test("verse 11 - the farmer sowing his corn") {
    pending
    House.recite(11, 11) should be(
      """This is the farmer sowing his corn that kept the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.

""")
  }

  test("verse 12 - the horse and the hound and the horn") {
    pending
    House.recite(12, 12) should be(
      """This is the horse and the hound and the horn that belonged to the farmer sowing his corn that kept the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.

""")
  }

  test("multiple verses") {
    pending
    House.recite(4, 8) should be(
      """This is the cat that killed the rat that ate the malt that lay in the house that Jack built.
This is the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.
This is the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.
This is the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.
This is the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.

""")
  }

  test("full rhyme") {
    pending
    House.recite(1, 12) should be(
      """This is the house that Jack built.
This is the malt that lay in the house that Jack built.
This is the rat that ate the malt that lay in the house that Jack built.
This is the cat that killed the rat that ate the malt that lay in the house that Jack built.
This is the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.
This is the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.
This is the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.
This is the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.
This is the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.
This is the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.
This is the farmer sowing his corn that kept the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.
This is the horse and the hound and the horn that belonged to the farmer sowing his corn that kept the rooster that crowed in the morn that woke the priest all shaven and shorn that married the man all tattered and torn that kissed the maiden all forlorn that milked the cow with the crumpled horn that tossed the dog that worried the cat that killed the rat that ate the malt that lay in the house that Jack built.

""")
  }
}
