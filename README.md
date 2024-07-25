# Exercism Scala Track

Exercism Exercises in Scala

## Contributing Guide

Please see the [contributing guide](https://github.com/exercism/x-api/blob/master/CONTRIBUTING.md#the-exercise-data)

Scala exercises are run by the [scala-test-runner](https://github.com/exercism/scala-test-runner).
It ignores the dependencies listed in the `sbt` files of the exercises. It's important to add all dependencies necessary to run the exercise to the scala-test-runner's dependencies.

### Generated Test Suites

`testgen` contains a project for generating test suites from [canonical test data](https://github.com/exercism/problem-specifications).

You can run it as follows:
```
sbt testgen / run <exercise-slug> <path-to-canonical-data> <optional-path-to-generated-file>
```

where:
- `exercise-slug` is the slug of one of the exercises listed in the [config.json](config.json) file.
- `path-to-canonical-data` is a local path to the canonical data, which could be obtained by running `bin/fetch-configlet` and `bin/configlet info -v d`
- `optional-path-to-generated-file` an optional path for the generated file like `./TestSuite.scala`. 

Note, that existing iteration of the `testgen` is not _yet_ used.

## Pull Requests

We welcome pull requests that provide fixes to existing test suites (missing
tests, interesting edge cases, improved APIs), as well as new problems.

If you're unsure, then go ahead and open a GitHub issue, and we'll discuss the
change.

Please submit changes to a single problem per pull request unless you're
submitting a general change across many of the problems (e.g. formatting).



## Scala icon
The Scala icon used on Exercism is inspired by the official Scala logo, which is copyright École Polytechnique Fédérale de Lausanne.
