import scala.util.Random

object UniqueNames {
  val names =
    Random.shuffle(1 to 26 * 26 * 1000).iterator.map(n => {
      val lettersPart = n / 1000
      val firstLetter = 'A' + (lettersPart / 26)
      val secondLetter = 'A' + (lettersPart % 26)
      f"$firstLetter%c$secondLetter%c${n % 1000}%03d"
    })
}

class Robot {
  private var storedName = UniqueNames.names.next()

  def name: String = storedName
  def reset(): Unit = storedName = UniqueNames.names.next()
}