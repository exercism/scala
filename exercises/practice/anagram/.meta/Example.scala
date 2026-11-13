object Anagram:
  def findAnagrams(word: String, candidates: Seq[String]): Seq[String] =
    val lower   = word.toLowerCase
    val letters = lower.sorted
    candidates.filter: candidate =>
      val other = candidate.toLowerCase
      other != lower && other.sorted == letters
