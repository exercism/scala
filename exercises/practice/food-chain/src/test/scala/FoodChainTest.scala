import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** Generated from the canonical data in problem-specifications.
  *
  * Do not edit by hand: change .meta/template.j2 and run bin/generate-tests.
  */
class FoodChainTest extends AnyFunSuite with Matchers:

  test("fly"):
    val expected = List(
      "I know an old lady who swallowed a fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
    ).mkString("", "\n", "\n\n")
    FoodChain.recite(1, 1) should be(expected)

  test("spider"):
    pending
    val expected = List(
      "I know an old lady who swallowed a spider.",
      "It wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
    ).mkString("", "\n", "\n\n")
    FoodChain.recite(2, 2) should be(expected)

  test("bird"):
    pending
    val expected = List(
      "I know an old lady who swallowed a bird.",
      "How absurd to swallow a bird!",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
    ).mkString("", "\n", "\n\n")
    FoodChain.recite(3, 3) should be(expected)

  test("cat"):
    pending
    val expected = List(
      "I know an old lady who swallowed a cat.",
      "Imagine that, to swallow a cat!",
      "She swallowed the cat to catch the bird.",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
    ).mkString("", "\n", "\n\n")
    FoodChain.recite(4, 4) should be(expected)

  test("dog"):
    pending
    val expected = List(
      "I know an old lady who swallowed a dog.",
      "What a hog, to swallow a dog!",
      "She swallowed the dog to catch the cat.",
      "She swallowed the cat to catch the bird.",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
    ).mkString("", "\n", "\n\n")
    FoodChain.recite(5, 5) should be(expected)

  test("goat"):
    pending
    val expected = List(
      "I know an old lady who swallowed a goat.",
      "Just opened her throat and swallowed a goat!",
      "She swallowed the goat to catch the dog.",
      "She swallowed the dog to catch the cat.",
      "She swallowed the cat to catch the bird.",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
    ).mkString("", "\n", "\n\n")
    FoodChain.recite(6, 6) should be(expected)

  test("cow"):
    pending
    val expected = List(
      "I know an old lady who swallowed a cow.",
      "I don't know how she swallowed a cow!",
      "She swallowed the cow to catch the goat.",
      "She swallowed the goat to catch the dog.",
      "She swallowed the dog to catch the cat.",
      "She swallowed the cat to catch the bird.",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
    ).mkString("", "\n", "\n\n")
    FoodChain.recite(7, 7) should be(expected)

  test("horse"):
    pending
    val expected = List(
      "I know an old lady who swallowed a horse.",
      "She's dead, of course!",
    ).mkString("", "\n", "\n\n")
    FoodChain.recite(8, 8) should be(expected)

  test("multiple verses"):
    pending
    val expected = List(
      "I know an old lady who swallowed a fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
      "",
      "I know an old lady who swallowed a spider.",
      "It wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
      "",
      "I know an old lady who swallowed a bird.",
      "How absurd to swallow a bird!",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
    ).mkString("", "\n", "\n\n")
    FoodChain.recite(1, 3) should be(expected)

  test("full song"):
    pending
    val expected = List(
      "I know an old lady who swallowed a fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
      "",
      "I know an old lady who swallowed a spider.",
      "It wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
      "",
      "I know an old lady who swallowed a bird.",
      "How absurd to swallow a bird!",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
      "",
      "I know an old lady who swallowed a cat.",
      "Imagine that, to swallow a cat!",
      "She swallowed the cat to catch the bird.",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
      "",
      "I know an old lady who swallowed a dog.",
      "What a hog, to swallow a dog!",
      "She swallowed the dog to catch the cat.",
      "She swallowed the cat to catch the bird.",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
      "",
      "I know an old lady who swallowed a goat.",
      "Just opened her throat and swallowed a goat!",
      "She swallowed the goat to catch the dog.",
      "She swallowed the dog to catch the cat.",
      "She swallowed the cat to catch the bird.",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
      "",
      "I know an old lady who swallowed a cow.",
      "I don't know how she swallowed a cow!",
      "She swallowed the cow to catch the goat.",
      "She swallowed the goat to catch the dog.",
      "She swallowed the dog to catch the cat.",
      "She swallowed the cat to catch the bird.",
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
      "She swallowed the spider to catch the fly.",
      "I don't know why she swallowed the fly. Perhaps she'll die.",
      "",
      "I know an old lady who swallowed a horse.",
      "She's dead, of course!",
    ).mkString("", "\n", "\n\n")
    FoodChain.recite(1, 8) should be(expected)
