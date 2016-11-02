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

For example, take a look at the `all-your-base/canonical-data.json` file in the x-common repository, as well
as the following files in the xscala repository:

1. `testgen/src/main/scala/AllYourBaseTestGenerator.scala` - test suite generator for all-your-base
1. `exercises/all-your-base/src/test/scala/AllYourBase.scala`- generated test suite

Since a generator was used, the`exercises/all-your-base/src/test/scala/AllYourBase.scala` will never be edited directly. 
If there's a missing test case, then additional inputs/outputs should be submitted to the x-common repository.

Note that the the test suite generators do not format the test suite source code. The generated test suite should be 
formatted before being submitted.

When submitting new exercises we encourage that a test suite generator and generated test suite is
included. 

## Pull Requests

We welcome pull requests that provide fixes to existing test suites (missing
tests, interesting edge cases, improved APIs), as well as new problems.

If you're unsure, then go ahead and open a GitHub issue, and we'll discuss the
change.

Please submit changes to a single problem per pull request unless you're
submitting a general change across many of the problems (e.g. formatting).


## License

The MIT License (MIT)

Copyright (c) 2014 Katrina Owen, _@kytrinyx.com

## Scala icon
The Scala icon used on Exercism is inspired by the official Scala logo, which is copyright École Polytechnique Fédérale de Lausanne.
