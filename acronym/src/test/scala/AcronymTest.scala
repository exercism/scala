import org.scalatest.{FlatSpec, Matchers}

class AcronymTest extends FlatSpec with Matchers {
  private val acronyms = List(
    ("Portable Network Graphics", "PNG"),
    ("Ruby on Rails", "ROR"),
    ("HyperText Markup Language", "HTML"),
    ("First In, First Out",  "FIFO"),
    ("PHP: Hypertext Preprocessor", "PHP"),
    ("Complementary metal-oxide semiconductor", "CMOS"))

  it should "create acronyms" in {
    acronyms.foreach{case (phrase, acronym) => Acronym(phrase).abbreviate should equal(acronym)}
  }
}
