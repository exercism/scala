import org.scalatest.{Matchers, FunSuite}

/** @version 1.2.0 */
class AnagramTest extends FunSuite with Matchers {

  test("no matches") {
    Anagram.anagrams("diaper", List("hello", "world", "zombies", "pants")) should be(
      List())
  }

  test("detects two anagrams") {

    Anagram.anagrams("master", List("stream", "pigeon", "maters")) should be(
      List("stream", "maters"))
  }

  test("does not detect anagram subsets") {

    Anagram.anagrams("good", List("dog", "goody")) should be(List())
  }

  test("detects anagram") {

    Anagram.anagrams("listen", List("enlists", "google", "inlets", "banana")) should be(
      List("inlets"))
  }

  test("detects three anagrams") {

    Anagram.anagrams("allergy",
                     List("gallery",
                          "ballerina",
                          "regally",
                          "clergy",
                          "largely",
                          "leading")) should be(
      List("gallery", "regally", "largely"))
  }

  test("does not detect non-anagrams with identical checksum") {

    Anagram.anagrams("mass", List("last")) should be(List())
  }

  test("detects anagrams case-insensitively") {

    Anagram.anagrams("Orchestra", List("cashregister", "Carthorse", "radishes")) should be(
      List("Carthorse"))
  }

  test("detects anagrams using case-insensitive subject") {

    Anagram.anagrams("Orchestra", List("cashregister", "carthorse", "radishes")) should be(
      List("carthorse"))
  }

  test("detects anagrams using case-insensitive possible matches") {

    Anagram.anagrams("orchestra", List("cashregister", "Carthorse", "radishes")) should be(
      List("Carthorse"))
  }

  test("does not detect a anagram if the original word is repeated") {

    Anagram.anagrams("go", List("go Go GO")) should be(List())
  }

  test("anagrams must use all letters exactly once") {

    Anagram.anagrams("tapper", List("patter")) should be(List())
  }

  test("capital word is not own anagram") {

    Anagram.anagrams("BANANA", List("Banana")) should be(List())
  }
}
