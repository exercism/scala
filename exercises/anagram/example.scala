object Anagram {
  def findAnagrams(word: String, anagrams: Seq[String]) =
    anagrams.filter(w => doesMatch(word, w)).filterNot(w => isIdentical(word, w))

  private def doesMatch(word1: String, word2: String) =
    word2.toLowerCase.sorted == word1.toLowerCase.sorted

  private def isIdentical(word1: String, word2: String) =
    word2.toLowerCase == word1.toLowerCase
}
