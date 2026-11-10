object Pangrams:
  private val alphabet = ('a' to 'z').toSet

  def isPangram(input: String): Boolean =
    alphabet.subsetOf(input.toLowerCase.toSet)
