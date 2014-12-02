import org.scalatest.{Matchers, FlatSpec}

class AtbashTest extends FlatSpec with Matchers {
  it should "encode no" in {
    Atbash().encode("no") should equal("ml")
  }

  it should "encode yes" in {
    Atbash().encode("yes") should equal("bvh")
  }

  it should "encode OMG" in {
    Atbash().encode("OMG") should equal("lnt")
  }

  it should "encode lowercase omg" in {
    Atbash().encode("omg") should equal("lnt")
  }

  it should "encode O M G" in {
    Atbash().encode("O M G ") should equal("lnt")
  }

  it should "encode and group string " in {
    Atbash().encode("mindblowingly") should equal("nrmwy oldrm tob")
  }

  it should "encode string with digits and punctuation" in {
    Atbash().encode("Testing, 1 2 3, testing. ") should equal("gvhgr mt123 gvhgr mt")
  }

  it should "encode \"Truth is fiction.\"" in {
    Atbash().encode("Truth is fiction.") should equal("gifgs rhurx grlm")
  }

  it should "encode a long string" in {
    Atbash().encode("The quick brown fox jumps over the lazy dog.") should
      equal("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt")
  }
}
