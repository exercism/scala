# Bank Account

Simulate a bank account supporting opening/closing, withdrawals, and deposits
of money. Watch out for concurrent transactions!

A bank account can be accessed in multiple ways. Clients can make
deposits and withdrawals using the internet, mobile phones, etc. Shops
can charge against the account.

Create an account that can be accessed from multiple threads/processes
(terminology depends on your programming language).

It should be possible to close an account; operations against a closed
account must fail.

## Instructions

Run the test file, and fix each of the errors in turn. When you get the
first test to pass, go to the first pending or skipped test, and make
that pass as well. When all of the tests are passing, feel free to
submit.

Remember that passing code is just the first step. The goal is to work
towards a solution that is as readable and expressive as you can make
it.

Have fun!

## Hints

This exercise is testing mutable state that can be accessed saftely from multiple threads. Scala provides a variety of ways to protect 
mutable state. For developers familiar with Java concurrency, Scala can utilize the Java concurrency support such as the Java synchronized block.

### Common Pitfalls

In Scala there are two ways to achieve mutable state: Use a "var" or a mutable object.
Two common mistakes here are:
- Do not use a "var" that is also a mutable object. One is enough, but not both together.
- Don't expose the "var" or mutable object to the outside world. So make them "private" and change the mutable object into immutable before you return it as a value.


The Scala exercises assume an SBT project scheme. The exercise solution source
should be placed within the exercise directory/src/main/scala. The exercise
unit tests can be found within the exercise directory/src/test/scala.

To run the tests simply run the command `sbt test` in the exercise directory.

For more detailed info about the Scala track see the [help
page](http://exercism.io/languages/scala).


## Submitting Incomplete Solutions
It's possible to submit an incomplete solution so you can see how others have completed the exercise.
