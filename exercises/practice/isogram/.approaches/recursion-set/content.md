# Recursion with a `Set`

```scala
import scala.annotation.tailrec

object Isogram:
  def isIsogram(s: String): Boolean =
    @tailrec
    def loop(index: Int, seen: Set[Char]): Boolean =
      if index == s.length then true
      else
        val c = s(index).toLower
        if !c.isLetter then loop(index + 1, seen)
        else if seen(c) then false
        else loop(index + 1, seen + c)

    loop(0, Set.empty)
```

The other approaches read the whole string before they answer.
This one stops at the first letter that is repeated: for `"aa"` followed by a thousand other characters it looks at two of them.

The work is done by `loop()`, a method defined inside `isIsogram()`.
Being defined there, it can use the parameter `s`, and it is not visible from outside.
It takes the index of the character to look at, and the [`Set`][set] of the letters that have been seen so far.
`isIsogram()` starts it at index `0` with an empty set.

`loop()` first checks whether the index has reached the length of the string.
If it has, then every character was looked at and no letter was repeated, so the string is an isogram.

Otherwise it takes the character at the index and lowercases it with [`toLower`][char], so that `A` and `a` count as the same letter.
There are then three cases:

- The character is not a letter.
  It is skipped: `loop()` calls itself for the next index with the same set.
- The character is a letter that is in the set.
  `seen(c)` is a short way to write `seen.contains(c)`.
  The letter is repeated, so the answer is `false`, and the rest of the string is not looked at.
- The character is a letter that is not in the set.
  `loop()` calls itself for the next index, with a set to which the letter has been added.
  `seen + c` does not change `seen`; it gives a new set.

A method that calls itself is [recursive][recursion].
Here every call to `loop()` is the last thing that the method does: its result is returned as it is.
That is a tail call, and Scala compiles such a method into a loop, so a long string does not fill up the call stack.
The [`@tailrec`][tailrec] annotation asks the compiler to check this.
If one of the calls were not a tail call, the code would not compile.

[set]: https://www.scala-lang.org/api/current/scala/collection/immutable/Set.html
[char]: https://www.scala-lang.org/api/current/scala/Char.html
[recursion]: https://www.geeksforgeeks.org/recursion-in-scala/
[tailrec]: https://www.scala-lang.org/api/current/scala/annotation/tailrec.html
