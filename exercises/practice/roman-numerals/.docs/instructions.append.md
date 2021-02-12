# Hints
For something a little different you might also try a solution with an `unfold` function.
You are probably already familiar with `foldLeft/Right`: "map" a whole collection into something else (usually a non-collection).
`unfoldLeft/Right` are the "inverse" operations: "map" something (usually a non-collection) into a collection.
So `unfold`ing is a logical addition to and part of the FP standard repertoire.

This exercise can be seen as a case for `unfold`ing: "map" an `Int` into a `String` (which is of course implicitly a `Seq[Char]`).

Unfortunately `unfoldLeft/Right` is not included in Scala's collection library.
But you can take the implementation from [here](http://daily-scala.blogspot.de/2009/09/unfoldleft-and-right.html).
