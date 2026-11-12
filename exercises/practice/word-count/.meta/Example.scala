case class WordCount(phrase: String):
  private val word = """\w+('\w+)*""".r

  def countWords: Map[String, Int] =
    word.findAllIn(phrase.toLowerCase).toSeq.groupMapReduce(identity)(_ => 1)(_ + _)
