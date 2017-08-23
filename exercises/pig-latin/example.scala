object PigLatin {
  def translate(phrase: String): String = {
    phrase.split("\\s+").map(translateWord).mkString(" ")
  }

  def translateWord(str: String): String = {
    val lowercase = str.toLowerCase

    if (Seq("yt", "xr").exists(pre => lowercase.startsWith(pre))) {
      lowercase + "ay"
    } else {
      val before = lowercase.takeWhile(!_.isLetter)
      val w1 = lowercase.drop(before.length)
      val w2 = w1.takeWhile(_.isLetter)
      val after = w1.drop(w2.length)
      val (cs, w) = consonantCluster(w2)

      before ++ w ++ cs ++ "ay" ++ after
    }
  }

  private def consonantCluster(str: String): (String, String) = {
    val isVowel = Set('a', 'e', 'i', 'o', 'u')
    val first = str.takeWhile(!isVowel(_))
    val rest = str.drop(first.length)
    if (rest.startsWith("u") && first.endsWith("q"))
      (first ++ "u", rest.drop(1))
    else
      (first, rest)
  }
}
