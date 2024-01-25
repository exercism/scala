import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class ReverseStringTest extends AnyFunSuite with Matchers {
  
  test("an empty string") {
    ReverseString.reverse("") should be ("")
  }

  test("a word") {
    pending
    ReverseString.reverse("robot") should be ("tobor")
  }

  test("a capitalized word") {
    pending
    ReverseString.reverse("Ramen") should be ("nemaR")
  }

  test("a sentence with punctuation") {
    pending
    ReverseString.reverse("I'm hungry!") should be ("!yrgnuh m'I")
  }

  test("a palindrome") {
    pending
    ReverseString.reverse("racecar") should be ("racecar")
  }

  test("an even-sized word") {
    pending
    ReverseString.reverse("drawer") should be ("reward")
  }
}
