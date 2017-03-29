import testgen._
import TestSuiteBuilder._
import java.io.File

object BeerSongTestGenerator {
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/beer-song.json")

    val code = TestSuiteBuilder.build(file,
        fromLabeledTestAlt("verse" -> Seq("number"), "verses" -> Seq("beginning", "end")))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}
