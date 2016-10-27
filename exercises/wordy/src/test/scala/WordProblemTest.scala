import org.scalatest.{Matchers, FlatSpec}

class WordProblemTest extends FlatSpec with Matchers {

  it should "handle simple addition" in {
    WordProblem("What is 1 plus 1?") should equal(Some(2))
  }

  it should "handle addition with negative numbers" in { 
    pending
    WordProblem("What is -1 plus -10?") should equal(Some(-11))
  }

  it should "handle repeated addition" in { 
    pending
    WordProblem("What is 1 plus 1 plus 1?") should equal(Some(3))
  }

  it should "handle simple subtraction" in { 
    pending
    WordProblem("What is 1 minus 1?") should equal(Some(0))
  }

  it should "handle subtraction with negative numbers" in { 
    pending
    WordProblem("What is 4 minus -12?") should equal(Some(16))
  }

  it should "handle repeated subtraction" in { 
    pending
    WordProblem("What is 20 minus 4 minus 13?") should equal(Some(3))
  }

  it should "handle simple multiplication" in { 
    pending
    WordProblem("What is 10 multiplied by 10?") should equal(Some(100))
  }

  it should "handle multiplication with negative numbers" in { 
    pending
    WordProblem("What is -10 multiplied by -10?") should equal(Some(100))
  }

  it should "handle repeated multiplication" in { 
    pending
    WordProblem("What is 2 multiplied by -2 multiplied by 3?") should equal(Some(-12))
  }

  it should "handle simple division" in { 
    pending
    WordProblem("What is 10 divided by 10?") should equal(Some(1))
  }

  it should "handle division with negative numbers" in { 
    pending
    WordProblem("What is 33 divided by -3?") should equal(Some(-11))
  }

  it should "handle repeated division" in { 
    pending
    WordProblem("What is -12 divided by 2 divided by -3?") should equal(Some(2))
  }

  it should "handle mixed operations" in { 
    pending
    WordProblem("What is 1 plus 5 minus -2?") should equal(Some(8))
    WordProblem("What is 17 minus 6 plus 3?") should equal(Some(14))
    WordProblem("What is -3 plus 7 multiplied by -2?") should equal(Some(-8))
  }

  it should "handle invalid word problem" in { 
    pending
    WordProblem("What is 1 plus 5 minus -2?") should equal(Some(8))
  }

  // Test for "Extension"
  ignore should "handle powers" in { 
    pending
    WordProblem("What is 2 raised to the 5?") should equal(Some(32))
  }
}
