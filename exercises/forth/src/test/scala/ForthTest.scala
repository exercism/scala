import org.scalatest.{Matchers, FunSuite}

/** @version 1.2.0 */
class ForthTest extends FunSuite with Matchers {

  private val forth = new Forth

  test("parsing and numbers - empty input results in empty stack") {
    forth.eval("").fold(_ => "", _.toString) should be ("")
  }

  test("parsing and numbers - numbers just get pushed onto the stack") {
    pending
    forth.eval("1 2 3 4 5").fold(_ => "", _.toString) should be ("1 2 3 4 5")
  }

  test("addition - can add two numbers") {
    pending
    forth.eval("1 2 +").fold(_ => "", _.toString) should be ("3")
  }

  test("addition - errors if there is nothing on the stack") {
    pending
    forth.eval("+").isLeft should be (true)
  }

  test("addition - errors if there is only one value on the stack") {
    pending
    forth.eval("1 +").isLeft should be (true)
  }

  test("subtraction - can subtract two numbers") {
    pending
    forth.eval("3 4 -").fold(_ => "", _.toString) should be ("-1")
  }

  test("subtraction - errors if there is nothing on the stack") {
    pending
    forth.eval("-").isLeft should be (true)
  }

  test("subtraction - errors if there is only one value on the stack") {
    pending
    forth.eval("1 -").isLeft should be (true)
  }

  test("multiplication - can multiply two numbers") {
    pending
    forth.eval("2 4 *").fold(_ => "", _.toString) should be ("8")
  }

  test("multiplication - errors if there is nothing on the stack") {
    pending
    forth.eval("*").isLeft should be (true)
  }

  test("multiplication - errors if there is only one value on the stack") {
    pending
    forth.eval("1 *").isLeft should be (true)
  }

  test("division - can divide two numbers") {
    pending
    forth.eval("12 3 /").fold(_ => "", _.toString) should be ("4")
  }

  test("division - performs integer division") {
    pending
    forth.eval("8 3 /").fold(_ => "", _.toString) should be ("2")
  }

  test("division - errors if dividing by zero") {
    pending
    forth.eval("4 0 /").isLeft should be (true)
  }

  test("division - errors if there is nothing on the stack") {
    pending
    forth.eval("/").isLeft should be (true)
  }

  test("division - errors if there is only one value on the stack") {
    pending
    forth.eval("1 /").isLeft should be (true)
  }

  test("combined arithmetic - addition and subtraction") {
    pending
    forth.eval("1 2 + 4 -").fold(_ => "", _.toString) should be ("-1")
  }

  test("combined arithmetic - multiplication and division") {
    pending
    forth.eval("2 4 * 3 /").fold(_ => "", _.toString) should be ("2")
  }

  test("dup - copies the top value on the stack") {
    pending
    forth.eval("1 DUP").fold(_ => "", _.toString) should be ("1 1")
  }

  test("dup - is case-insensitive") {
    pending
    forth.eval("1 2 Dup").fold(_ => "", _.toString) should be ("1 2 2")
  }

  test("dup - errors if there is nothing on the stack") {
    pending
    forth.eval("dup").isLeft should be (true)
  }

  test("drop - removes the top value on the stack if it is the only one") {
    pending
    forth.eval("1 drop").fold(_ => "", _.toString) should be ("")
  }

  test("drop - removes the top value on the stack if it is not the only one") {
    pending
    forth.eval("1 2 drop").fold(_ => "", _.toString) should be ("1")
  }

  test("drop - errors if there is nothing on the stack") {
    pending
    forth.eval("drop").isLeft should be (true)
  }

  test("swap - swaps the top two values on the stack if they are the only ones") {
    pending
    forth.eval("1 2 swap").fold(_ => "", _.toString) should be ("2 1")
  }

  test("swap - swaps the top two values on the stack if they are not the only ones") {
    pending
    forth.eval("1 2 3 swap").fold(_ => "", _.toString) should be ("1 3 2")
  }

  test("swap - errors if there is nothing on the stack") {
    pending
    forth.eval("swap").isLeft should be (true)
  }

  test("swap - errors if there is only one value on the stack") {
    pending
    forth.eval("1 swap").isLeft should be (true)
  }

  test("over - copies the second element if there are only two") {
    pending
    forth.eval("1 2 over").fold(_ => "", _.toString) should be ("1 2 1")
  }

  test("over - copies the second element if there are more than two") {
    pending
    forth.eval("1 2 3 over").fold(_ => "", _.toString) should be ("1 2 3 2")
  }

  test("over - errors if there is nothing on the stack") {
    pending
    forth.eval("over").isLeft should be (true)
  }

  test("over - errors if there is only one value on the stack") {
    pending
    forth.eval("1 over").isLeft should be (true)
  }

  test("user-defined words - can consist of built-in words") {
    pending
    forth.eval(": dup-twice dup dup ; 1 dup-twice").fold(_ => "", _.toString) should be ("1 1 1")
  }

  test("user-defined words - execute in the right order") {
    pending
    forth.eval(": countup 1 2 3 ; countup").fold(_ => "", _.toString) should be ("1 2 3")
  }

  test("user-defined words - can override other user-defined words") {
    pending
    forth.eval(": foo dup ; : foo dup dup ; 1 foo").fold(_ => "", _.toString) should be ("1 1 1")
  }

  test("user-defined words - can override built-in words") {
    pending
    forth.eval(": swap dup ; 1 swap").fold(_ => "", _.toString) should be ("1 1")
  }

  test("user-defined words - can override built-in operators") {
    pending
    forth.eval(": + * ; 3 4 +").fold(_ => "", _.toString) should be ("12")
  }

  test("user-defined words - cannot redefine numbers") {
    pending
    forth.eval(": 1 2 ;").isLeft should be (true)
  }

  test("user-defined words - errors if executing a non-existent word") {
    pending
    forth.eval("foo").isLeft should be (true)
  }
}