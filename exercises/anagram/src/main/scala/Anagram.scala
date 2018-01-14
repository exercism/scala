object Anagram {
  def anagrams(word: String, anagram: Seq[String]): List[String] = {
    val wordLower = word.toLowerCase()
    val lookupWordMap = wordToMap(wordLower)
    anagram.filter(testWord => {
      val testWordLower = testWord.toLowerCase()
      testWordLower.toLowerCase != wordLower &&
        wordToMap(testWordLower) == lookupWordMap
    }).toList
  }
  import scala.collection.mutable
  def wordToMap(word: String): Map[Char, Int] =
    word.foldLeft(mutable.Map[Char,Int]())((map, char) =>{
      map += ((char, map.get(char).getOrElse(0) + 1))
    }).toMap
}
