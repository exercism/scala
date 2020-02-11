object Pangrams {
  private val alphaLength = 26

  def isPangram(input: String): Boolean = {
    val numUniqueChars = input.toStream
      .map(_.toLower)
      .filter(c => c >= 'a' && c <='z')
      .toSet
      .size
    numUniqueChars == alphaLength
  }
}
