import org.scalatest.{EitherValues, Matchers, FlatSpec}

class ForthTest extends FlatSpec with Matchers with EitherValues {
  private val forth = new Forth

  "no input" should "result in no stack" in {
    forth.eval("") match {
      case Right(state) => state.toString should equal("")
      case _ => fail("error handling no input")
    }
  }

  "numbers" should "just get pushed onto the stack" in {
    pending
    forth.eval("1 2 3 4 5") match {
      case Right(state) => state.toString should equal("1 2 3 4 5")
      case Left(error) => fail("error pushing numbers on stack - " + error)
    }
  }

  "non-word characters" should "be handled as separators" in {
    pending
    // Note the Ogham Space Mark ( ), this is a spacing character.
    // Also note that if Regex is used for your solution then handling
    // Unicode requires and additional flag in the Regex string "(?U)".
    forth.eval("1\u00002\u00013\n4\r5 6\t7") match {
      case Right(state) => state.toString should equal("1 2 3 4 5 6 7")
      case Left(error) => fail("error handling non-word chars - " + error)
    }
  }

  "basic arithmetic" should "evaluate" in {
    pending
    forth.eval("1 2 + 4 -") match {
      case Right(state) => state.toString should equal("-1")
      case Left(error) => fail("error handling basic arithmetic - " + error)
    }
  }

  "basic mul/div" should "evaluate" in {
    pending
    forth.eval("2 4 * 3 /") match {
      case Right(state) => state.toString should equal("2")
      case Left(error) => fail("error handling basic mul/div - " + error)
    }
  }

  "division by zero" should "return error" in {
    pending
    forth.eval("4 2 2 - /") match {
      case Right(state) => fail("division by zero should return error")
      case Left(error) => error should equal(ForthError.DivisionByZero)
    }
  }

  "dup" should "dupe the top of the stack" in {
    pending
    forth.eval("1 DUP") match {
      case Right(state) => state.toString should equal("1 1")
      case Left(error) => fail("error handling dup - " + error)
    }

    forth.eval("1 2 DUP") match {
      case Right(state) => state.toString should equal("1 2 2")
      case Left(error) => fail("error handling dup of multi element stack - " + error)
    }
  }

  "dup on empty stack" should "result in error" in {
    pending
    forth.eval("dup") match {
      case Right(state) => fail("dup on empty stack should return error")
      case Left(error) => error should equal(ForthError.StackUnderflow)
    }
  }

  "drop" should "remove an item from the stack" in {
    pending
    forth.eval("1 2 drop") match {
      case Right(state) => state.toString should equal("1")
      case Left(error) => fail("error handling drop - " + error)
    }
  }

  "drop on 1 item stack" should "result in empty" in {
    pending
    forth.eval("1 drop") match {
      case Right(state) => state.toString should equal("")
      case Left(error) => fail("error handling drop on 1 item stack - " + error)
    }
  }

  "drop on empty stack" should "result in error" in {
    pending
    forth.eval("drop") match {
      case Right(state) => fail("drop on empty stack should return error")
      case Left(error) => error should equal(ForthError.StackUnderflow)
    }
  }

  "swap" should "swap the top of the stack" in {
    pending
    forth.eval("1 2 swap") match {
      case Right(state) => state.toString should equal("2 1")
      case Left(error) => fail("error handling swap - " + error)
    }

    forth.eval("1 2 3 swap") match {
      case Right(state) => state.toString should equal("1 3 2")
      case Left(error) => fail("error handling swap - " + error)
    }
  }

  "swap on empty" should "result in error" in {
    pending
    forth.eval("swap") match {
      case Right(state) => fail("swap on empty stack should return error")
      case Left(error) => error should equal(ForthError.StackUnderflow)
    }
  }

  "swap on single item stack" should "result in error" in {
    pending
    forth.eval("1 swap") match {
      case Right(state) => fail("swap on single item stack should return error")
      case Left(error) => error should equal(ForthError.StackUnderflow)
    }
  }

  "over" should "dupe second item in stack" in {
    pending
    forth.eval("1 2 over") match {
      case Right(state) => state.toString should equal("1 2 1")
      case Left(error) => fail("error handling over - " + error)
    }

    forth.eval("1 2 3 over") match {
      case Right(state) => state.toString should equal("1 2 3 2")
      case Left(error) => fail("error handling over - " + error)
    }
  }

  "over on empty" should "result in error" in {
    pending
    forth.eval("over") match {
      case Right(state) => fail("over on empty stack should return error")
      case Left(error) => error should equal(ForthError.StackUnderflow)
    }
  }

  "over on single item stack" should "result in error" in {
    pending
    forth.eval("1 over") match {
      case Right(state) => fail("over on single item  stack should return error")
      case Left(error) => error should equal(ForthError.StackUnderflow)
    }
  }

  "define a new word and usage" should "result in stack update" in {
    pending
    forth.eval(": dupe-twice dup dup ;\r 1 dupe-twice") match {
      case Right(state) => state.toString should equal("1 1 1")
      case Left(error) => fail("error handling define new word and use - " + error)
    }
  }

  "redefine an existing word and usage" should "result in stack update" in {
    pending
    forth.eval(": foo dup ;\r: foo dup dup ;\n1 foo") match {
      case Right(state) => state.toString should equal("1 1 1")
      case Left(error) => fail("error handling redefine word and use - " + error)
    }
  }

  "define a built in word" should "result in stack update using redefinition" in {
    pending
    forth.eval(": swap dup ;\r 1 swap") match {
      case Right(state) => state.toString should equal("1 1")
      case Left(error) => fail("error handling redefining built in word and use - " + error)
    }
  }

  "defining a word with odd chars" should "update the stack" in {
    pending
    forth.eval(": €A 220371 ; €A") match {
      case Right(state) => state.toString should equal("220371")
      case Left(error) => fail("error handling word definition with odd chars - " + error)
    }
  }

  "defining a number" should "result in an error" in {
    pending
    forth.eval(": 1 2 ;") match {
      case Right(state) => fail("defining a new word using a number should result in an error")
      case Left(error) => error should equal(ForthError.InvalidWord)
    }
  }

  "calling a non-existent word" should "result in an error" in {
    pending
    forth.eval("1 foo") match {
      case Right(state) => fail("calling a non-existent word should result in an error")
      case Left(error) => error should equal(ForthError.InvalidWord)
    }
  }
}
