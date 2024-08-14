# Running Tests

With `sbt` installed, the tests can be executed from the command line with:

```bash
$ sbt test
```

Note that executing the `sbt test` every time will be slow, as it'll re-initializes the `sbt` on each invocation.

`sbt` works like a shell, once it's started you can run commands inside it. 

For example, you can use `sbt` to re-run your tests whenever the source files change:
```bash
$ sbt
>~ test
```

Note that all tests have been disabled except the first one for you to work on. 
To continue, just remove the `pending` keyword from the beginning of each test case.

Go [here](https://www.scala-sbt.org/) to learn more about the `sbt`.


Tests can also be run within the following IDEs

* [Metals](https://scalameta.org/metals/docs/#running-tests)
* [IntelliJ IDEA with Scala Plugin](https://www.jetbrains.com/idea/)


