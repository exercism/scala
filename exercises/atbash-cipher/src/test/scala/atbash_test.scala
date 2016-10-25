import org.scalatest.{Matchers, FlatSpec}

class AtbashTest extends FlatSpec with Matchers {
  it should "encode no" in {
    Atbash().encode("no") should equal("ml")
  }

  it should "encode yes" in {
    pending
    Atbash().encode("yes") should equal("bvh")
  }

  it should "encode OMG" in {
    pending
    Atbash().encode("OMG") should equal("lnt")
  }

  it should "encode lowercase omg" in {
    pending
    Atbash().encode("omg") should equal("lnt")
  }

  it should "encode O M G" in {
    pending
    Atbash().encode("O M G ") should equal("lnt")
  }

  it should "encode and group string " in {
    pending
    Atbash().encode("mindblowingly") should equal("nrmwy oldrm tob")
  }

  it should "encode string with digits and punctuation" in {
    pending
    Atbash().encode("Testing, 1 2 3, testing. ") should equal("gvhgr mt123 gvhgr mt")
  }

  it should "encode \"Truth is fiction.\"" in {
    pending
    Atbash().encode("Truth is fiction.") should equal("gifgs rhurx grlm")
  }

  it should "encode a long string" in {
    pending
    Atbash().encode("The quick brown fox jumps over the lazy dog.") should
      equal("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt")
  }
}
