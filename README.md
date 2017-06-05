# xScala

Exercism Exercises in Scala

## Contributing Guide

Please see the [contributing guide](https://github.com/exercism/x-api/blob/master/CONTRIBUTING.md#the-exercise-data)

### Generated Test Suites

Some of the test suites have been generated from shared test data. If a test suite was
generated from test data, then the test suite should not be modified by hand. Instead
the shared test data should be modified, and the generator rerun. To determine if
a test suite generator was used, look inside the `testgen/src/main/scala` directory. The test
suite generators are named in the form `ProblemNameTestGenerator.scala`. Where
`ProblemName` is a close match for the Exercism problem name.

[the shared problem metadata](https://github.com/exercism/x-common).

For example, take a look at the `bob/canonical-data.json` file in the x-common repository, as well
as the following files in the xscala repository:

1. `testgen/src/main/scala/BobTestGenerator.scala` - test suite generator for bob
1. `exercises/bob/src/test/scala/BobTest.scala`- generated test suite

Since a generator was used, the`exercises/bob/src/test/scala/BobTest.scala` will never be edited directly. 
If there's a missing test case, then additional inputs/outputs should be submitted to the x-common repository.

When submitting new exercises we encourage that a test suite generator and generated test suite is
included. 

## Pull Requests

We welcome pull requests that provide fixes to existing test suites (missing
tests, interesting edge cases, improved APIs), as well as new problems.

If you're unsure, then go ahead and open a GitHub issue, and we'll discuss the
change.

Please submit changes to a single problem per pull request unless you're
submitting a general change across many of the problems (e.g. formatting).



## Scala icon
The Scala icon used on Exercism is inspired by the official Scala logo, which is copyright École Polytechnique Fédérale de Lausanne.
