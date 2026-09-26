# Exercism Scala Track

Exercism Exercises in Scala

## Contributing Guide

Please see the [contributing guide](https://github.com/exercism/x-api/blob/master/CONTRIBUTING.md#the-exercise-data)

Scala exercises are run by the [scala-test-runner](https://github.com/exercism/scala-test-runner).
It ignores the dependencies listed in the `sbt` files of the exercises. It's important to add all dependencies necessary to run the exercise to the scala-test-runner's dependencies.

### Verifying the Exercises

`bin/test` runs each exercise's tests against its example/exemplar solution:

```
bin/test              # every exercise
bin/test two-fer      # a single exercise
bin/test two-fer bob  # a few of them
```

Every exercise is a subproject of this build (see [`project/Exercises.scala`](project/Exercises.scala)), so all of them are verified in a single `sbt` session.
Inside the `sbt` shell the same is available as:

```
sbt
> exercises/test   # every exercise
> two-fer/test     # a single exercise
> ~two-fer/test    # ... and again on every change
```

The exercises themselves are never modified: a subproject compiles the exemplar/example solution instead of the stub, and a copy of the tests with the `pending` markers blanked out.
Both are assembled under `target/`.

To verify the exercises the way the website does, against the test runner's Docker image, use `bin/verify-exercises-in-docker` instead.

### Generated Test Suites

An exercise's test suite can be generated from the [canonical data][probspecs].
A generated suite cannot silently drift away from the spec: CI regenerates it and fails if the committed file differs.

An exercise opts in by adding a Jinja2 template at `.meta/template.j2`.
`bin/generate-tests` renders the cases `.meta/tests.toml` records, skipping those marked `include = false` and those a later case `reimplements`.

```
pip install -r bin/generator/requirements.txt
bin/fetch-configlet
bin/configlet sync --tests      # clones problem-specifications; see the note below

bin/generate-tests              # regenerate every exercise that has a template
bin/generate-tests darts        # just one
bin/generate-tests --check      # what CI runs
bin/generate-tests --no-pull    # skip refreshing problem-specifications
```

`bin/fetch-configlet` only downloads configlet itself.
The problem-specifications clone that `bin/generate-tests` reads is made by running a configlet command that needs it, such as `configlet sync`.
Afterwards `bin/generate-tests` keeps that clone current with `git pull` unless `--no-pull` is passed.

Generated suites must not be edited by hand - change the template and regenerate.

To convert an exercise, write `.meta/template.j2` and run `bin/generate-tests <slug>`.
See `exercises/practice/leap/.meta/template.j2` for a short one.
Templates get `cases`, each with `input`, `expected`, `descriptions`, `expect_error` and `expect_error_msg`, plus a `scala` filter that renders a canonical value as Scala source.
The exercise must be in sync with problem-specifications first: the generator refuses to run when `.meta/tests.toml` is missing canonical cases, because the `reimplements` links it needs live there.

Note that the canonical data describes behaviour, not an API.
Where the track's signature differs from the spec, the template is where that mapping lives.

[probspecs]: https://github.com/exercism/problem-specifications

## Pull Requests

We welcome pull requests that provide fixes to existing test suites (missing
tests, interesting edge cases, improved APIs), as well as new problems.

If you're unsure, then go ahead and open a GitHub issue, and we'll discuss the
change.

Please submit changes to a single problem per pull request unless you're
submitting a general change across many of the problems (e.g. formatting).



## Scala icon
The Scala icon used on Exercism is inspired by the official Scala logo, which is copyright École Polytechnique Fédérale de Lausanne.
