import org.scalatest.{Matchers, FlatSpec}

class PigLatinTest extends FlatSpec with Matchers {
  it should "translate beginning with vowels" in {
    PigLatin.translate("apple") should be ("appleay")
    PigLatin.translate("ear") should be ("earay")
  }

  it should "translate beginning with single letter consonant clusters" in {
    PigLatin.translate("pig") should be ("igpay")
    PigLatin.translate("koala") should be ("oalakay")
    PigLatin.translate("qat") should be ("atqay")
  }

  it should "translate beginning with multiple letter consonant clusters" in {
    PigLatin.translate("chair") should be ("airchay")
    PigLatin.translate("therapy") should be ("erapythay")
    PigLatin.translate("thrush") should be ("ushthray")
    PigLatin.translate("school") should be ("oolschay")
  }

  it should "translate beginning with consonant clusters with qu" in {
    PigLatin.translate("queen") should be ("eenquay")
    PigLatin.translate("square") should be ("aresquay")
  }

  it should "translate phrases" in {
    PigLatin.translate("quick fast run") should be ("ickquay astfay unray")
  }
}
