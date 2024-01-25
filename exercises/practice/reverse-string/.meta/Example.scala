object ReverseString {
  def reverse(str: String): String = str.foldRight("") ( (a, b) => b + a)
}
