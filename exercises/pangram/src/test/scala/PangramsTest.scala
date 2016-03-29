import org.scalatest.{FlatSpec, Matchers}

class PangramsTest extends FlatSpec with Matchers {
  "isPangram" should "return false for an empty sentence" in {
    Pangrams.isPangram("") should equal (false)
  }

  "isPangram" should "return true for lowercase pangram" in {
    Pangrams.isPangram("the quick brown fox jumps over the lazy dog") should equal (true)
  }

  "isPangram" should "return false when missing X character" in {
    Pangrams.isPangram("a quick movement of the enemy will jeopardize five gunboats") should equal (false)
  }

  "isPangram" should "return true for mixed case pangram with punctuation" in {
    Pangrams.isPangram("\"Five quacking Zephyrs jolt my wax bed.\"") should equal (true)
  }

  "isPangram" should "return true for pangram - ignoring non-ascii characters" in {
    Pangrams.isPangram("Victor jagt zwölf Boxkämpfer quer über den großen Sylter Deich.") should equal (true)
  }
}
