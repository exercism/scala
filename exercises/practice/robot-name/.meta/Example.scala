import scala.util.Random

object UniqueNames:
  /** Every possible name, shuffled once, so that no name is handed out twice. */
  val names: Iterator[String] =
    Random
      .shuffle(0 until 26 * 26 * 1000)
      .iterator
      .map: n =>
        val letters = n / 1000
        f"${('A' + letters / 26).toChar}%c${('A' + letters % 26).toChar}%c${n % 1000}%03d"

class Robot:
  private var storedName = UniqueNames.names.next()

  def name: String = storedName

  def reset(): Unit = storedName = UniqueNames.names.next()
