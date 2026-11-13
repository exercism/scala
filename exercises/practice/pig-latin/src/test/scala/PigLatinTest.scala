import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class PigLatinTest extends AnyFunSuite with Matchers:

  test("ay is added to words that start with vowels - word beginning with a"):
    PigLatin.translate("apple") should be("appleay")

  test("ay is added to words that start with vowels - word beginning with e"):
    pending
    PigLatin.translate("ear") should be("earay")

  test("ay is added to words that start with vowels - word beginning with i"):
    pending
    PigLatin.translate("igloo") should be("iglooay")

  test("ay is added to words that start with vowels - word beginning with o"):
    pending
    PigLatin.translate("object") should be("objectay")

  test("ay is added to words that start with vowels - word beginning with u"):
    pending
    PigLatin.translate("under") should be("underay")

  test("ay is added to words that start with vowels - word beginning with a vowel and followed by a qu"):
    pending
    PigLatin.translate("equal") should be("equalay")

  test("first letter and ay are moved to the end of words that start with consonants - word beginning with p"):
    pending
    PigLatin.translate("pig") should be("igpay")

  test("first letter and ay are moved to the end of words that start with consonants - word beginning with k"):
    pending
    PigLatin.translate("koala") should be("oalakay")

  test("first letter and ay are moved to the end of words that start with consonants - word beginning with x"):
    pending
    PigLatin.translate("xenon") should be("enonxay")

  test("first letter and ay are moved to the end of words that start with consonants - word beginning with q without a following u"):
    pending
    PigLatin.translate("qat") should be("atqay")

  test("first letter and ay are moved to the end of words that start with consonants - word beginning with consonant and vowel containing qu"):
    pending
    PigLatin.translate("liquid") should be("iquidlay")

  test("some letter clusters are treated like a single consonant - word beginning with ch"):
    pending
    PigLatin.translate("chair") should be("airchay")

  test("some letter clusters are treated like a single consonant - word beginning with qu"):
    pending
    PigLatin.translate("queen") should be("eenquay")

  test("some letter clusters are treated like a single consonant - word beginning with qu and a preceding consonant"):
    pending
    PigLatin.translate("square") should be("aresquay")

  test("some letter clusters are treated like a single consonant - word beginning with th"):
    pending
    PigLatin.translate("therapy") should be("erapythay")

  test("some letter clusters are treated like a single consonant - word beginning with thr"):
    pending
    PigLatin.translate("thrush") should be("ushthray")

  test("some letter clusters are treated like a single consonant - word beginning with sch"):
    pending
    PigLatin.translate("school") should be("oolschay")

  test("some letter clusters are treated like a single vowel - word beginning with yt"):
    pending
    PigLatin.translate("yttria") should be("yttriaay")

  test("some letter clusters are treated like a single vowel - word beginning with xr"):
    pending
    PigLatin.translate("xray") should be("xrayay")

  test("position of y in a word determines if it is a consonant or a vowel - y is treated like a consonant at the beginning of a word"):
    pending
    PigLatin.translate("yellow") should be("ellowyay")

  test("position of y in a word determines if it is a consonant or a vowel - y is treated like a vowel at the end of a consonant cluster"):
    pending
    PigLatin.translate("rhythm") should be("ythmrhay")

  test("position of y in a word determines if it is a consonant or a vowel - y as second letter in two letter word"):
    pending
    PigLatin.translate("my") should be("ymay")

  test("phrases are translated - a whole phrase"):
    pending
    PigLatin.translate("why do squids enjoy xrays") should be("ywhay oday idssquay enjoyay xraysay")
