import testgen._
import TestSuiteBuilder._
import java.io.File

object NucleotideCountTestGenerator {
  
  def main(args: Array[String]): Unit = {
    val file = new File("src/main/resources/nucleotide-count.json")

    val code = TestSuiteBuilder.build(file, fromLabeledTest("strand"))
    println(s"-------------")
    println(code)
    println(s"-------------")
  }
}