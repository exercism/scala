# Robot Name

Manage robot factory settings.

When robots come off the factory floor, they have no name.

The first time you boot them up, a random name is generated in the format
of two uppercase letters followed by three digits, such as RX837 or BC811.

Every once in a while we need to reset a robot to its factory settings,
which means that their name gets wiped. The next time you ask, it will
respond with a new random name.

The names must be random: they should not follow a predictable sequence.
Random names means a risk of collisions. Your solution must ensure that
every existing robot has a unique name.

## Hints
Make sure your solution is general enough to be easily scalable for longer names containing more letters and digits. This usually makes for better code quality, too.

Suggestion (this is not explicitly tested):
To make sure you always have a unique name you could implement your own cache or use a `Stream` with its built-in cache.


The Scala exercises assume an SBT project scheme. The exercise solution source
should be placed within the exercise directory/src/main/scala. The exercise
unit tests can be found within the exercise directory/src/test/scala.

To run the tests simply run the command `sbt test` in the exercise directory.

Please see the [learning](https://exercism.io/tracks/scala/learning) and 
[installation](https://exercism.io/tracks/scala/installation) pages if you need any help.


## Source

A debugging session with Paul Blackwell at gSchool.

## Submitting Incomplete Solutions
It's possible to submit an incomplete solution so you can see how others have completed the exercise.
