import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class BottleSongTest extends AnyFunSuite with Matchers {

  test("first generic verse") {
    BottleSong.recite(10, 1) shouldBe
      """Ten green bottles hanging on the wall,
        |Ten green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be nine green bottles hanging on the wall.
        |""".stripMargin.trim + "\n"
  }

  test("last generic verse") {
    pending
    BottleSong.recite(3, 1) shouldBe
      """Three green bottles hanging on the wall,
        |Three green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be two green bottles hanging on the wall.
        |""".stripMargin.trim + "\n"
  }

  test("verse with two bottles") {
    pending
    BottleSong.recite(2, 1) shouldBe
      """Two green bottles hanging on the wall,
        |Two green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be one green bottle hanging on the wall.
        |""".stripMargin.trim + "\n"
  }

  test("verse with one bottle") {
    pending
    BottleSong.recite(1, 1) shouldBe
      """One green bottle hanging on the wall,
        |One green bottle hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be no green bottles hanging on the wall.
        |""".stripMargin.trim + "\n"
  }

  test("first two verses") {
    pending
    BottleSong.recite(10, 2) shouldBe
      """Ten green bottles hanging on the wall,
        |Ten green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be nine green bottles hanging on the wall.
        |
        |Nine green bottles hanging on the wall,
        |Nine green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be eight green bottles hanging on the wall.
        |""".stripMargin.trim + "\n"
  }

  test("last three verses") {
    pending
    BottleSong.recite(3, 3) shouldBe
      """Three green bottles hanging on the wall,
        |Three green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be two green bottles hanging on the wall.
        |
        |Two green bottles hanging on the wall,
        |Two green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be one green bottle hanging on the wall.
        |
        |One green bottle hanging on the wall,
        |One green bottle hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be no green bottles hanging on the wall.
        |""".stripMargin.trim + "\n"
  }

  test("all verses") {
    pending
    BottleSong.recite(10, 10) shouldBe
      """Ten green bottles hanging on the wall,
        |Ten green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be nine green bottles hanging on the wall.
        |
        |Nine green bottles hanging on the wall,
        |Nine green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be eight green bottles hanging on the wall.
        |
        |Eight green bottles hanging on the wall,
        |Eight green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be seven green bottles hanging on the wall.
        |
        |Seven green bottles hanging on the wall,
        |Seven green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be six green bottles hanging on the wall.
        |
        |Six green bottles hanging on the wall,
        |Six green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be five green bottles hanging on the wall.
        |
        |Five green bottles hanging on the wall,
        |Five green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be four green bottles hanging on the wall.
        |
        |Four green bottles hanging on the wall,
        |Four green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be three green bottles hanging on the wall.
        |
        |Three green bottles hanging on the wall,
        |Three green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be two green bottles hanging on the wall.
        |
        |Two green bottles hanging on the wall,
        |Two green bottles hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be one green bottle hanging on the wall.
        |
        |One green bottle hanging on the wall,
        |One green bottle hanging on the wall,
        |And if one green bottle should accidentally fall,
        |There'll be no green bottles hanging on the wall.
        |""".stripMargin.trim + "\n"
  }
}