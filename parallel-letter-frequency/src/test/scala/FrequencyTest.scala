import org.scalatest.{Matchers, FunSuite}

class FrequencyTest extends FunSuite with Matchers {

  // Poem by Friedrich Schiller. The corresponding music is the European
  // Anthem.
  private val ode_an_die_freude = List(
    "Freude schöner Götterfunken"
    , "Tochter aus Elysium,"
    , "Wir betreten feuertrunken,"
    , "Himmlische, dein Heiligtum!"
    , "Deine Zauber binden wieder"
    , "Was die Mode streng geteilt;"
    , "Alle Menschen werden Brüder,"
    , "Wo dein sanfter Flügel weilt.")

  //Dutch national anthem
  private val wilhelmus = List(
    "Wilhelmus van Nassouwe"
    , "ben ik, van Duitsen bloed,"
    , "den vaderland getrouwe"
    , "blijf ik tot in den dood."
    , "Een Prinse van Oranje"
    , "ben ik, vrij, onverveerd,"
    , "den Koning van Hispanje"
    , "heb ik altijd geëerd.")

  //American national anthem
  private val star_spangled_banner = List(
    "O say can you see by the dawn's early light,"
    , "What so proudly we hailed at the twilight's last gleaming,"
    , "Whose broad stripes and bright stars through the perilous fight,"
    , "O'er the ramparts we watched, were so gallantly streaming?"
    , "And the rockets' red glare, the bombs bursting in air,"
    , "Gave proof through the night that our flag was still there;"
    , "O say does that star-spangled banner yet wave,"
    , "O'er the land of the free and the home of the brave?")

  test("Empty texts") {
    Frequency.frequency(1, Seq()) should be (Map())
  }

  test("Single letter") {
    Frequency.frequency(10, Seq("a")) should be (Map('a' -> 1))
  }

  test("Case insensitivity") {
    Frequency.frequency(1000, Seq("aA")) should be (Map('a' -> 2))
  }

  test("Many empty texts") {
    Frequency.frequency(1, Iterator.fill(10000)("  ").toSeq) should be (Map())
  }

  test("many times the same text gives a predictable result") {
    Frequency.frequency(1, Iterator.fill(1000)("abc").toSeq) should
      be (Map('a' -> 1000, 'b' -> 1000, 'c' -> 1000))
  }

  test("Ignore punctuation") {
    Frequency.frequency(1, ode_an_die_freude).get(',') should be (None)
  }

  test("Ignore numbers") {
    Frequency.frequency(1, Seq("Testing 1, 2, 3")).get('1') should be (None)
  }

  test("All three anthems - 1 worker") {
    val freqs = Frequency.frequency(1, ode_an_die_freude ++ wilhelmus ++ star_spangled_banner)
    freqs.get('a') should be (Some(49))
    freqs.get('t') should be (Some(56))
    freqs.get('ü') should be (Some(2))
  }

  test("All three anthems - 4 workers") {
    val freqs = Frequency.frequency(4, ode_an_die_freude ++ wilhelmus ++ star_spangled_banner)
    freqs.get('a') should be (Some(49))
    freqs.get('t') should be (Some(56))
    freqs.get('ü') should be (Some(2))
  }
}
