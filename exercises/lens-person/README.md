# Lens Person

Use lenses to manipulate (get, set, modify) immutable objects.

Updating fields of nested (immutable) objects is kind of annoying in Scala. One solution
is to use [lenses](https://wiki.haskell.org/Lens).
Implement several class attribute accessing functions using lenses, you may use any library you want. One good choice could be [Monocle](https://julien-truffaut.github.io/Monocle/].
The test suite also allows you to avoid lenses alltogether so you can experiment with
different approaches.


The Scala exercises assume an SBT project scheme. The exercise solution source
should be placed within the exercise directory/src/main/scala. The exercise
unit tests can be found within the exercise directory/src/test/scala.

To run the tests simply run the command `sbt test` in the exercise directory.

For more detailed info about the Scala track see the [help
page](http://help.exercism.io/getting-started-with-scala.html).
