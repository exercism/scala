object PigLatin:

  private val VowelSound     = "(?:[aeiou]|xr|yt).*".r
  private val ConsonantSound = "([^aeiouy]*qu|[^aeiou][^aeiouy]*)(.*)".r

  def translate(phrase: String): String = phrase.split(" ").map(translateWord).mkString(" ")

  private def translateWord(word: String): String = word match
    case VowelSound()                  => word + "ay"
    case ConsonantSound(cluster, rest) => rest + cluster + "ay"
    case _                             => word
