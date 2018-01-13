object Anagram {
  def anagrams(word: String, anagrams: Seq[String]): List[String] = {
    val lookupWordMap = wordToMap(word)
    anagrams.filter(testWord => wordToMap(testWord) == lookupWordMap).toList
  }
  import scala.collection.mutable
  def wordToMap(word: String): Map[Char, Int] =
    word.foldLeft(mutable.Map[Char,Int]())((map, char) =>{
      map += ((char, map.get(char).getOrElse(0) + 1))
    }).toMap
}
