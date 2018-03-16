object Anagram {
  def anagrams(target: String, values: List[String]) = {
    values.filter(v => target.toLowerCase != v.toLowerCase && target.toLowerCase.sorted == v.toLowerCase.sorted)
  }
}