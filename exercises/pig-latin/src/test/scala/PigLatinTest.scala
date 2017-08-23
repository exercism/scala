import org.scalatest.{Matchers, FunSuite}

/** @version 1.0.0 */
class PigLatinTest extends FunSuite with Matchers {

  test("word beginning with a") {
    PigLatin.translate("apple") should be ("appleay")
  }

  test("word beginning with e") {
    pending
    PigLatin.translate("ear") should be ("earay")
  }

  test("word beginning with i") {
    pending
    PigLatin.translate("igloo") should be ("iglooay")
  }

  test("word beginning with o") {
    pending
    PigLatin.translate("object") should be ("objectay")
  }

  test("word beginning with u") {
    pending
    PigLatin.translate("under") should be ("underay")
  }

  test("word beginning with a vowel and followed by a qu") {
    pending
    PigLatin.translate("equal") should be ("equalay")
  }

  test("word beginning with p") {
    pending
    PigLatin.translate("pig") should be ("igpay")
  }

  test("word beginning with k") {
    pending
    PigLatin.translate("koala") should be ("oalakay")
  }

  test("word beginning with y") {
    pending
    PigLatin.translate("yellow") should be ("ellowyay")
  }

  test("word beginning with x") {
    pending
    PigLatin.translate("xenon") should be ("enonxay")
  }

  test("word beginning with q without a following u") {
    pending
    PigLatin.translate("qat") should be ("atqay")
  }

  test("word beginning with ch") {
    pending
    PigLatin.translate("chair") should be ("airchay")
  }

  test("word beginning with qu") {
    pending
    PigLatin.translate("queen") should be ("eenquay")
  }

  test("word beginning with qu and a preceding consonant") {
    pending
    PigLatin.translate("square") should be ("aresquay")
  }

  test("word beginning with th") {
    pending
    PigLatin.translate("therapy") should be ("erapythay")
  }

  test("word beginning with thr") {
    pending
    PigLatin.translate("thrush") should be ("ushthray")
  }

  test("word beginning with sch") {
    pending
    PigLatin.translate("school") should be ("oolschay")
  }

  test("word beginning with yt") {
    pending
    PigLatin.translate("yttria") should be ("yttriaay")
  }

  test("word beginning with xr") {
    pending
    PigLatin.translate("xray") should be ("xrayay")
  }

  test("a whole phrase") {
    pending
    PigLatin.translate("quick fast run") should be ("ickquay astfay unray")
  }
}