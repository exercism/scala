# Instructions append

## Track specific instructions

The stub gives you more types than the tests use, so here is what the tests expect:

- `eval` evaluates a whole program and returns the final state in a `Right`, or an error in a `Left`.
- The `toString` of the state is the stack as text: the values separated by single spaces, with the top of the stack last.
  An empty stack is an empty string.
- The tests only check that an error is a `Left`, but do choose the `ForthError` that describes it.
- `Definition` is a suggestion for modelling words.
  You may use it, change it or delete it.

Most tests share a single `Forth` instance, and every call to `eval` is a program of its own.
Start each call with an empty stack and no user-defined words: a word defined in one call must not be visible in the next.

Splitting the text on whitespace is enough to read a program.
If you would like to try [parser combinators][parser-combinators] instead, the `scala-parser-combinators` library is already listed in the `build.sbt` of this exercise.

[parser-combinators]: https://github.com/scala/scala-parser-combinators
