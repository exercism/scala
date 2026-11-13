import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class BobTest extends AnyFunSuite with Matchers:

  test("asking a question"):
    Bob.response("Does this cryogenic chamber make me look fat?") should be("Sure.")

  test("shouting"):
    pending
    Bob.response("WATCH OUT!") should be("Whoa, chill out!")

  test("forceful question"):
    pending
    Bob.response("WHAT'S GOING ON?") should be("Calm down, I know what I'm doing!")

  test("silence"):
    pending
    Bob.response("") should be("Fine. Be that way!")

  test("stating something"):
    pending
    Bob.response("Tom-ay-to, tom-aaaah-to.") should be("Whatever.")

  test("asking a numeric question"):
    pending
    Bob.response("You are, what, like 15?") should be("Sure.")

  test("asking gibberish"):
    pending
    Bob.response("fffbbcbeab?") should be("Sure.")

  test("question with no letters"):
    pending
    Bob.response("4?") should be("Sure.")

  test("non-letters with question"):
    pending
    Bob.response(":) ?") should be("Sure.")

  test("prattling on"):
    pending
    Bob.response("Wait! Hang on. Are you going to be OK?") should be("Sure.")

  test("ending with whitespace"):
    pending
    Bob.response("Okay if like my  spacebar  quite a bit?   ") should be("Sure.")

  test("multiple line question"):
    pending
    Bob.response("\nDoes this cryogenic chamber make\n me look fat?") should be("Sure.")

  test("shouting gibberish"):
    pending
    Bob.response("FCECDFCAAB") should be("Whoa, chill out!")

  test("shouting a statement containing a question mark"):
    pending
    Bob.response("DO LIONS EAT PEOPLE? AHHHHH.") should be("Whoa, chill out!")

  test("shouting numbers"):
    pending
    Bob.response("1, 2, 3 GO!") should be("Whoa, chill out!")

  test("shouting with special characters"):
    pending
    Bob.response("ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!") should be("Whoa, chill out!")

  test("shouting with no exclamation mark"):
    pending
    Bob.response("I HATE THE DENTIST") should be("Whoa, chill out!")

  test("prolonged silence"):
    pending
    Bob.response("          ") should be("Fine. Be that way!")

  test("alternate silence"):
    pending
    Bob.response("\t\t\t\t\t\t\t\t\t\t") should be("Fine. Be that way!")

  test("other whitespace"):
    pending
    Bob.response("\n\r \t") should be("Fine. Be that way!")

  test("talking forcefully"):
    pending
    Bob.response("Hi there!") should be("Whatever.")

  test("using acronyms in regular speech"):
    pending
    Bob.response("It's OK if you don't want to go work for NASA.") should be("Whatever.")

  test("no letters"):
    pending
    Bob.response("1, 2, 3") should be("Whatever.")

  test("statement containing question mark"):
    pending
    Bob.response("Ending with ? means a question.") should be("Whatever.")

  test("starting with whitespace"):
    pending
    Bob.response("         hmmmmmmm...") should be("Whatever.")

  test("non-question ending with whitespace"):
    pending
    Bob.response("This is a statement ending with whitespace      ") should be("Whatever.")
