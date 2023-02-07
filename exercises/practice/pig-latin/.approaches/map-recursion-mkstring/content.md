# `map()` with recursion and `mkString()`

```scala
object PigLatin {
  private val vowels = Set('a', 'e', 'i', 'o', 'u')
  private val vowels_y = Set('a', 'e', 'i', 'o', 'u', 'y')
  private val specials = Set("xr", "yt")

  def translate(phrase: String): String = {

    phrase
      .split(" ")
      .map(word =>
        if (vowels.contains(word.head) || specials.contains(word.slice(0, 2)))
          word + "ay"
        else findVowel(word, 1)
      )
      .mkString(" ")
  }

  @scala.annotation.tailrec
  private def findVowel(word: String, pos: Int): String = {
    word.charAt(pos) match {
      case ltr if vowels_y.contains(ltr) => {
        val posFix = if (word.slice(pos - 1, pos + 1) == "qu") pos + 1 else pos
        word.slice(posFix, word.length()) + word.slice(0, posFix) + "ay"
      }
      case _ => findVowel(word, pos + 1)
    }
  }
}
```

This approach starts by defining some [Set][set]s to to hold the vowels, vowels with `y`, and the special groups of letters.

The input phrase is split on a space character and the word or words are chained to the [`map()`][map] method.
Each word is passed to the [lambda][lambda] which consists of an `if/else` expression.
If the `Set` of vowels includes the first character of the word (also called the [`head`][head]), or if a [`slice()`][slice]
of the first two characters is one of the special groups of letters, then `map()` outputs the word appended with `ay`.

Otherwise, the `findVowel()` method is passed the word and a starting position of `1`.

The `findVowel()` method is annotated with the [`@tailrec`][tailrec-annotation] annotation to verify that the method can be compiled
with [tail call optimization][tail-opt].

A tail call is a particular form of [recursion][recursion] where the last call in the method is a call to the same method _and nothing else_.

In other words, if the last call in `recurMe()` is `recurMe(arg1, arg2) + 1`, the `+ 1` makes the recursion non-tail recursive.

If the last call in `recurMe()` is `recurMe(arg1, arg2, acc + 1)`, then the recursion is a tail call, because only the method is being called
with no other operation being peformed on it.

A [`match`][match] expression is used to perform [pattern matching][pattern-matching] on the result of passing the position
to the [`charAt()`][charat] method.

A [pattern guard][pattern-guard] is used to check if the character is a vowel (`y` is considered a vowel at this point.)
If so, the positon is adjusted if the previous character and the current character are `qu`, otherwise it stays the same.
The `match` returns a `slice()` from the position until the end of the word, to which is concatenated a `slice()` from the
beginning of the word up to but not including the character at the position,  to which is concatenated `ay`.

If the character is not a vowel, then `findVowel()` calls itself, passing in the same word and adding `1` to the position.

After all of the words have been mapped, they are reassembled into a string by the [`mkString()`][mkstring] method.

[set]: https://www.scala-lang.org/api/2.13.3/scala/collection/immutable/Set.html
[head]: https://www.scala-lang.org/api/2.13.3/scala/collection/StringOps.html#head:Char
[slice]: https://www.scala-lang.org/api/2.13.3/scala/collection/StringOps.html#slice(from:Int,until:Int):String
[map]: https://www.scala-lang.org/api/2.13.3/scala/Array.html#map[B](f:A=%3EB)(implicitct:scala.reflect.ClassTag[B]):Array[B]
[lambda]: https://www.geeksforgeeks.org/lambda-expression-in-scala/
[underscore]: https://www.baeldung.com/scala/underscore
[match]: https://docs.scala-lang.org/tour/pattern-matching.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec-annotation]: https://www.scala-lang.org/api/2.12.1/scala/annotation/tailrec.html
[tail-opt]: https://www.baeldung.com/scala/tail-recursion
[pattern-matching]: https://docs.scala-lang.org/tour/pattern-matching.html
[charat]: https://www.scala-lang.org/api/2.12.8/scala/collection/immutable/StringOps.html#charAt(x$1:Int):Char
[pattern-guard]: https://alvinalexander.com/scala/how-to-use-if-then-expressions-guards-in-case-statements-scala/
[ternary]: https://alvinalexander.com/scala/scala-ternary-operator-syntax/
[mkstring]: https://www.scala-lang.org/api/2.13.3/scala/Array.html#mkString(sep:String):String
[approach-map-recursion-mkstring]: https://exercism.org/tracks/scala/exercises/pig-latin/approaches/map-recursion-mkstring
