object SecretHandshake {
  private val reverseGestures = 16
  private def gestures = List((1, "wink"), (2, "double blink"),
    (4, "close your eyes"), (8, "jump"))

  def handshake(s: String): List[String] = {
    def isValid = s.count(c => c != '0' && c != '1') == 0

    if (!isValid) List()
    else handshake(Integer.parseInt(s, 2))
  }

  def handshake(n: Int): List[String] = {
    val result = gestures.filter {case (cmd, gesture) => (cmd & n) != 0} map
      {case (_, gesture) => gesture}
    if (shouldReverse(n)) result.reverse
    else result
  }

  private def shouldReverse(n: Int): Boolean = {
    (n & reverseGestures) != 0
  }
}
