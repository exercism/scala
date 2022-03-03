import scala.concurrent._
import scala.concurrent.duration.Duration
import ExecutionContext.Implicits.global

object Frequency {
  // get the char counts for one String
  private def countChars(text: String): Map[Char, Int] =
    text.filter(_.isLetter).groupBy(_.toLower).map{case (c, s) => (c, s.length)}

  // get the char counts for a Seq of String
  private def countChars(texts: Seq[String]): Map[Char, Int] =
    texts.map(s => countChars(s)).foldLeft(Map[Char, Int]())((textMap, acc) => merge(acc, textMap))

  // Merges two char count maps into a single map
  private def merge(map1: Map[Char, Int], map2: Map[Char, Int]) =
    map1 ++ map2.map{ case (k,v) => k -> (v + map1.getOrElse(k,0)) }

  def frequency(numWorkers: Int, texts: Seq[String]): Map[Char, Int] = {
    require(numWorkers > 0)

    val chunkSize = Math.ceil(texts.size / (numWorkers * 1.0)).asInstanceOf[Int]
    if (chunkSize == 0) return Map()

    // Create a list of Futures. Each Future is to process a sequence of chunkSize
    // texts.
    val futures: Seq[Future[Map[Char, Int]]] =
      texts.grouped(chunkSize).map(textsChunk => Future {countChars(textsChunk)}).toSeq
    val sequence: Future[Seq[Map[Char, Int]]] = Future.sequence(futures)

    // Wait for the results
    val futuresResult: Seq[Map[Char, Int]] = Await.result(Future.sequence(futures), Duration.Inf)

    // Aggregate the results from each Future
    futuresResult.foldLeft(Map[Char,Int]())((textMap, acc) => merge(acc, textMap))
  }
}
