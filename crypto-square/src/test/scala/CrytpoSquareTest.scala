import org.scalatest.{Matchers, FlatSpec}

class CrytpoSquareTest extends FlatSpec with Matchers {
  it should "normalize away special characters" in {
    CryptoSquare().normalizePlaintext("s#!@$%pl\t\r\nunk") should equal("splunk")
  }

  it should "normalize uppercase to lowercase" in {
    CryptoSquare().normalizePlaintext("1, 2, 3 GO!") should equal("123go")
  }


  it should "calc a square size for a perfect square" in {
    CryptoSquare().squareSize("1234") should equal(2)
    CryptoSquare().squareSize("123456789") should equal(3)
  }

  it should "calc a square size when not a perfect square" in {
    CryptoSquare().squareSize("123456789abc") should equal(4)
    CryptoSquare().squareSize("123456789abcd") should equal(4)
  }

  it should "not generate calc error when empty string" in {
    CryptoSquare().squareSize("") should equal(0)
  }


  it should "build plaintext segments - all equal segment lengths" in {
    CryptoSquare().plaintextSegments("Never vex thine heart with idle woes.") should
      equal(List("neverv", "exthin", "eheart", "withid", "lewoes"))
  }

  it should "build plaintext segments - last segment short" in {
    CryptoSquare().plaintextSegments("ZOMG! ZOMBIES!!!") should
      equal(List("zomg", "zomb", "ies"))
  }

  it should "build cipher text" in {
    CryptoSquare().ciphertext("Time is an illusion. Lunchtime doubly so.") should
      equal("tasneyinicdsmiohooelntuillibsuuml")
    CryptoSquare().ciphertext("We all know interspecies romance is weird.") should
      equal("wneiaweoreneawssciliprerlneoidktcms")
  }

  it should "build normalized cipher text" in {
    CryptoSquare().normalizedCiphertext("Madness, and then illumination.") should
      equal("msemo aanin dnin ndla etlt shui")
    CryptoSquare().normalizedCiphertext("If man was meant to stay on the ground " +
      "god would have given us roots") should
      equal("imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau")
  }

  it should "not error on blank strings" in {
    CryptoSquare().ciphertext("") should
      equal("")
    CryptoSquare().ciphertext("    ") should
      equal("")
    CryptoSquare().normalizedCiphertext("") should
      equal("")
    CryptoSquare().normalizedCiphertext("   ") should
      equal("")
  }
}
