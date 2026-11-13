object SecretHandshake:

  private val Gestures = List("wink", "double blink", "close your eyes", "jump")
  private val Reverse  = 16

  def commands(n: Int): List[String] =
    val gestures = Gestures.zipWithIndex.collect { case (gesture, bit) if (n & (1 << bit)) != 0 => gesture }
    if (n & Reverse) != 0 then gestures.reverse else gestures
