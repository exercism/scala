import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class BottleSongTest extends AnyFunSuite with Matchers:

  test("verse - single verse - first generic verse"):
    val lines = List(
      "Ten green bottles hanging on the wall,",
      "Ten green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be nine green bottles hanging on the wall.",
    )
    BottleSong.recite(10, 1) should be(lines.mkString("", "\n", "\n"))

  test("verse - single verse - last generic verse"):
    pending
    val lines = List(
      "Three green bottles hanging on the wall,",
      "Three green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be two green bottles hanging on the wall.",
    )
    BottleSong.recite(3, 1) should be(lines.mkString("", "\n", "\n"))

  test("verse - single verse - verse with 2 bottles"):
    pending
    val lines = List(
      "Two green bottles hanging on the wall,",
      "Two green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be one green bottle hanging on the wall.",
    )
    BottleSong.recite(2, 1) should be(lines.mkString("", "\n", "\n"))

  test("verse - single verse - verse with 1 bottle"):
    pending
    val lines = List(
      "One green bottle hanging on the wall,",
      "One green bottle hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be no green bottles hanging on the wall.",
    )
    BottleSong.recite(1, 1) should be(lines.mkString("", "\n", "\n"))

  test("lyrics - multiple verses - first two verses"):
    pending
    val lines = List(
      "Ten green bottles hanging on the wall,",
      "Ten green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be nine green bottles hanging on the wall.",
      "",
      "Nine green bottles hanging on the wall,",
      "Nine green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be eight green bottles hanging on the wall.",
    )
    BottleSong.recite(10, 2) should be(lines.mkString("", "\n", "\n"))

  test("lyrics - multiple verses - last three verses"):
    pending
    val lines = List(
      "Three green bottles hanging on the wall,",
      "Three green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be two green bottles hanging on the wall.",
      "",
      "Two green bottles hanging on the wall,",
      "Two green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be one green bottle hanging on the wall.",
      "",
      "One green bottle hanging on the wall,",
      "One green bottle hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be no green bottles hanging on the wall.",
    )
    BottleSong.recite(3, 3) should be(lines.mkString("", "\n", "\n"))

  test("lyrics - multiple verses - all verses"):
    pending
    val lines = List(
      "Ten green bottles hanging on the wall,",
      "Ten green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be nine green bottles hanging on the wall.",
      "",
      "Nine green bottles hanging on the wall,",
      "Nine green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be eight green bottles hanging on the wall.",
      "",
      "Eight green bottles hanging on the wall,",
      "Eight green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be seven green bottles hanging on the wall.",
      "",
      "Seven green bottles hanging on the wall,",
      "Seven green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be six green bottles hanging on the wall.",
      "",
      "Six green bottles hanging on the wall,",
      "Six green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be five green bottles hanging on the wall.",
      "",
      "Five green bottles hanging on the wall,",
      "Five green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be four green bottles hanging on the wall.",
      "",
      "Four green bottles hanging on the wall,",
      "Four green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be three green bottles hanging on the wall.",
      "",
      "Three green bottles hanging on the wall,",
      "Three green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be two green bottles hanging on the wall.",
      "",
      "Two green bottles hanging on the wall,",
      "Two green bottles hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be one green bottle hanging on the wall.",
      "",
      "One green bottle hanging on the wall,",
      "One green bottle hanging on the wall,",
      "And if one green bottle should accidentally fall,",
      "There'll be no green bottles hanging on the wall.",
    )
    BottleSong.recite(10, 10) should be(lines.mkString("", "\n", "\n"))
