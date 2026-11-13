import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*
import scala.concurrent.{Await, Future}

object Frequency:

  def frequency(numWorkers: Int, texts: Seq[String]): Map[Char, Int] =
    require(numWorkers > 0)
    if texts.isEmpty then Map.empty
    else
      // One Future per chunk of texts, and a single blocking wait, with a timeout, at the end.
      val chunkSize = (texts.size + numWorkers - 1) / numWorkers
      val counts    = texts.grouped(chunkSize).map(chunk => Future(count(chunk))).toSeq
      Await.result(Future.sequence(counts), 1.minute).foldLeft(Map.empty[Char, Int])(merge)

  private def count(texts: Seq[String]): Map[Char, Int] =
    texts.flatMap(_.filter(_.isLetter).map(_.toLower)).groupMapReduce(identity)(_ => 1)(_ + _)

  private def merge(counts: Map[Char, Int], more: Map[Char, Int]): Map[Char, Int] =
    more.foldLeft(counts) { case (total, (letter, n)) => total.updated(letter, total.getOrElse(letter, 0) + n) }
