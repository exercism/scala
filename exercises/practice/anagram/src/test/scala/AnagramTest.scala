import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class AnagramTest extends AnyFunSuite with Matchers:

  test("no matches"):
    Anagram.findAnagrams("diaper", List("hello", "world", "zombies", "pants")) should be(List())

  test("detects two anagrams"):
    pending
    Anagram.findAnagrams("solemn", List("lemons", "cherry", "melons")) should be(List("lemons", "melons"))

  test("does not detect anagram subsets"):
    pending
    Anagram.findAnagrams("good", List("dog", "goody")) should be(List())

  test("detects anagram"):
    pending
    Anagram.findAnagrams("listen", List("enlists", "google", "inlets", "banana")) should be(List("inlets"))

  test("detects three anagrams"):
    pending
    Anagram.findAnagrams("allergy", List("gallery", "ballerina", "regally", "clergy", "largely", "leading")) should be(List("gallery", "regally", "largely"))

  test("detects multiple anagrams with different case"):
    pending
    Anagram.findAnagrams("nose", List("Eons", "ONES")) should be(List("Eons", "ONES"))

  test("does not detect non-anagrams with identical checksum"):
    pending
    Anagram.findAnagrams("mass", List("last")) should be(List())

  test("detects anagrams case-insensitively"):
    pending
    Anagram.findAnagrams("Orchestra", List("cashregister", "Carthorse", "radishes")) should be(List("Carthorse"))

  test("detects anagrams using case-insensitive subject"):
    pending
    Anagram.findAnagrams("Orchestra", List("cashregister", "carthorse", "radishes")) should be(List("carthorse"))

  test("detects anagrams using case-insensitive possible matches"):
    pending
    Anagram.findAnagrams("orchestra", List("cashregister", "Carthorse", "radishes")) should be(List("Carthorse"))

  test("does not detect an anagram if the original word is repeated"):
    pending
    Anagram.findAnagrams("go", List("goGoGO")) should be(List())

  test("anagrams must use all letters exactly once"):
    pending
    Anagram.findAnagrams("tapper", List("patter")) should be(List())

  test("words are not anagrams of themselves"):
    pending
    Anagram.findAnagrams("BANANA", List("BANANA")) should be(List())

  test("words are not anagrams of themselves even if letter case is partially different"):
    pending
    Anagram.findAnagrams("BANANA", List("Banana")) should be(List())

  test("words are not anagrams of themselves even if letter case is completely different"):
    pending
    Anagram.findAnagrams("BANANA", List("banana")) should be(List())

  test("words other than themselves can be anagrams"):
    pending
    Anagram.findAnagrams("LISTEN", List("LISTEN", "Silent")) should be(List("Silent"))

  test("handles case of greek letters"):
    pending
    Anagram.findAnagrams("ΑΒΓ", List("ΒΓΑ", "ΒΓΔ", "γβα", "αβγ")) should be(List("ΒΓΑ", "γβα"))

  test("different characters may have the same bytes"):
    pending
    Anagram.findAnagrams("a⬂", List("€a")) should be(List())
