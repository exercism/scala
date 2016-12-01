## Hints
For this exercise two Scala features come in handy:
- [Default Parameter Values](http://docs.scala-lang.org/tutorials/tour/default-parameter-values.html)
- [String Interpolation](http://docs.scala-lang.org/overviews/core/string-interpolation.html)

#### Common pitfalls that you should avoid
- `null` is usually not considered a valid value in Scala, and there are no `null` checks needed (if you don't have to interface with Java code, say). Instead there is the [Option](http://danielwestheide.com/blog/2012/12/19/the-neophytes-guide-to-scala-part-5-the-option-type.html) type if you want to express the possible absence of a value. But for this exercise just assume a normal non-`null` String parameter.
- Usually there is no need in Scala to use `return`. For a discussion see [here](http://stackoverflow.com/questions/24856106/return-in-a-scala-function-literal). Or as a quote from that discussion: *Don't use return, it makes Scala cry.*
