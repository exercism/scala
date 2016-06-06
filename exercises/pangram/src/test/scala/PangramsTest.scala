import org.scalatest.{FunSuite, Matchers}

class PangramsTest extends FunSuite with Matchers {
  test("sentence empty") {
    Pangrams.isPangram("") should be (false)
  }

  test("pangram with only lower case") {
    Pangrams.isPangram("the quick brown fox jumps over the lazy dog") should be (true)
  }

  test("missing character 'x'") {
    Pangrams.isPangram("a quick movement of the enemy will jeopardize five gunboats") should be (false)
  }

  test("another missing character 'x'") {
    Pangrams.isPangram("the quick brown fish jumps over the lazy dog") should be (false)
  }

  test("pangram with underscores") {
    Pangrams.isPangram("the_quick_brown_fox_jumps_over_the_lazy_dog") should be (true)
  }

  test("pangram with numbers") {
    Pangrams.isPangram("the 1 quick brown fox jumps over the 2 lazy dogs") should be (true)
  }

  test("missing letters replaced by numbers") {
    Pangrams.isPangram("7h3 qu1ck brown fox jumps ov3r 7h3 lazy dog") should be (false)
  }

  test("pangram with mixed case and punctuation") {
    Pangrams.isPangram("\"Five quacking Zephyrs jolt my wax bed.\"") should be (true)
  }

  test("pangram with non ascii characters") {
    Pangrams.isPangram("Victor jagt zwölf Boxkämpfer quer über den großen Sylter Deich.") should be (true)
  }

}