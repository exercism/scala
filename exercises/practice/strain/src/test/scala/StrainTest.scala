import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec


/** @version created manually **/
class StrainTest extends AnyFlatSpec with Matchers {

  it should "handle empty keep" in {
    val result = Strain.keep[Int](List(), x => x < 10)
    result should be(List.empty)
  }

  it should "keep everything" in {
    pending
    val expectedList = List("a", "b", "c")
    val result = Strain.keep[String](expectedList, _ => true)
    result should be(expectedList)
  }

  it should "keep first and last" in {
    pending
    val result = Strain.keep[Int](List(1, 2, 3), x => x % 2 != 0)
    result should be(List(1, 3))
  }

  it should "keep neither first or last" in {
    pending
    val result = Strain.keep[Int](List(1, 2, 3, 4, 5), x => x % 2 == 0)
    result should be(List(2, 4))
  }

  it should "keep strings" in {
    pending
    val words = List("apple",  "zebra", "banana", "zombies", "cherimoya",  "zelot")
    val result = Strain.keep[String](words, s => s.startsWith("z"))
    result should be(List("zebra", "zombies", "zelot"))
  }

  it should "keep sequences" in {
    pending
    val sequences = Seq(Seq(1, 2, 3), Seq(5, 5, 5), Seq(5, 1, 2), Seq(2, 1, 2),
      Seq(1, 5, 2), Seq(2, 2, 1), Seq(1, 2, 5))
    val expected = Seq(Seq(5, 5, 5), Seq(5, 1, 2), Seq(1, 5, 2), Seq(1, 2, 5))
    val result = Strain.keep[Seq[Int]](sequences, seq => seq.contains(5))
    result should be (expected)
  }

  it should "handle empty discard" in {
    pending
    val result = Strain.discard[Int](List(), x => x < 10)
    result should be (List.empty)
  }

  it should "discard first and last" in {
    pending
    val result = Strain.discard[Int](List(1, 2, 3), x => x % 2 != 0)
    result should be (List(2))
  }

  it should "discard neither first or last" in {
    pending
    val result = Strain.discard[Int](List(1, 2, 3, 4, 5), x => x % 2 == 0)
    result should be (List(1, 3, 5))
  }

  it should "discard strings" in {
    pending
    val words = List("apple",  "zebra", "banana", "zombies", "cherimoya",  "zelot")
    val result = Strain.discard[String](words, s => s.startsWith("z"))
    result should be (List("apple", "banana", "cherimoya"))
  }

  it should "discard sequences" in {
    pending
    val sequences = Seq(Seq(1, 2, 3), Seq(5, 5, 5), Seq(5, 1, 2), Seq(2, 1, 2),
      Seq(1, 5, 2), Seq(2, 2, 1), Seq(1, 2, 5))
    val expected = Seq(Seq(1, 2, 3), Seq(2, 1, 2), Seq(2, 2, 1))
    val result = Strain.discard[Seq[Int]](sequences, seq => seq.contains(5))
    result should be (expected)
  }
}
