import org.scalatest.{Matchers, FlatSpec}

/** @version created manually **/
class AccumulateTest extends FlatSpec with Matchers {

  it should "allow empty accumulation" in {
    val accumulate = new Accumulate
    val accumulation = accumulate.accumulate[Int, Int](x => x * x, List.empty)
    accumulation should be (List.empty)
  }

  it should "accumulate squares" in {
    val accumulate = new Accumulate
    val accumulation = accumulate.accumulate[Int, Int](x => x * x, List(1, 2, 3))
    accumulation should be (List(1, 4, 9))
  }

  it should "accumulate upcases" in {
    val accumulate = new Accumulate
    val accumulation = accumulate.accumulate[String, String](_.map(_.toUpper), List("hello", "world"))
    accumulation should be (List("HELLO", "WORLD"))
  }

  it should "accumulate reversed strings" in {
    val accumulate = new Accumulate
    val accumulation = accumulate.accumulate[String, String](_.reverse, List("eht", "kciuq", "nworb", "xof", "cte"))
    accumulation should be (List("the", "quick", "brown", "fox", "etc"))
  }

  it should "allow different return type" in {
    val accumulate = new Accumulate
    val accumulation = accumulate.accumulate[Int, String](_.toString, List(1, 2, 3))
    accumulation should be (List("1", "2", "3"))
  }
}
