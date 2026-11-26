# About

Scala is a statically typed language that combines functional and object-oriented programming.
Every value has a type, and the compiler checks the types before the program runs.

## Values and variables

A value is defined with `val`, by giving a name to the result of an expression.
A `val` is immutable: once defined, it cannot be assigned again.
A variable is defined with `var` and can be reassigned, but only to a value of the same type.

```scala
val layers = 3 // cannot be changed anymore
var count = 1  // can be changed
count += 1     // count is now 2

count = "two"
// => compile error: Found: ("two" : String), Required: Int
```

Idiomatic Scala prefers `val`, and uses `var` only where mutation is really needed.
Code that does not reassign names is easier to reason about, because a name means the same thing wherever it is used.

## Type inference

The type can be written after the name, or left out, in which case the compiler [infers][type-inference] it from the expression.

```scala
val explicit: Int = 10 // the type is written out
val inferred = 10      // the type is inferred to be Int
```

The types of method parameters always have to be written.
The return type of a method can be inferred, but it is good practice to write it for methods that are called from other places: it documents the method, and it keeps the type from changing by accident when the body changes.

## Integers

Whole numbers have the type `Int`.
They support the usual arithmetic operators, such as `+`, `-`, `*`, `/` and `%`.
Dividing two integers gives an integer:

```scala
7 / 2
// => 3
```

## Methods

A [method][methods] is defined with `def`, followed by its name, its parameters, its return type and, after `=`, its body.
It returns the value of the last expression of its body, so no `return` keyword is needed.

```scala
def add(x: Int, y: Int): Int = x + y

def sumOfSquares(x: Int, y: Int): Int =
  val xSquared = x * x
  val ySquared = y * y
  xSquared + ySquared
```

A method that takes no parameters and only computes a value is usually defined without parentheses, and is then called without them.
Parentheses are kept for methods that have a side effect, such as printing.

```scala
def answer: Int = 42

def printAnswer(): Unit = println(answer)
```

## Objects

Methods and values can be grouped in an [`object`][objects].
There is exactly one instance of an object, and it is referred to by its name.

```scala
object Calculator:
  val Zero = 0

  def add(x: Int, y: Int): Int = x + y

Calculator.add(Calculator.Zero, 7)
// => 7
```

## Indentation

In Scala 3 the body of an object or of a method is marked by [indentation][indentation].
Curly braces, as used in Scala 2 and in Java, are still accepted:

```scala
object Calculator {
  def add(x: Int, y: Int): Int = {
    x + y
  }
}
```

## Comments

Scala has single-line comments and comments that can span several lines.

```scala
// This is a single-line comment

/*
This comment spans
several lines
*/
```

## Naming conventions

By [convention][naming-conventions]:

- the names of objects, classes and other types are written in upper camel case, such as `Calculator`;
- the names of methods, values and variables are written in lower camel case, such as `sumOfSquares`;
- the names of constants are written in upper camel case, such as `Zero`.

[type-inference]: https://docs.scala-lang.org/tour/type-inference.html
[methods]: https://docs.scala-lang.org/scala3/book/taste-methods.html
[objects]: https://docs.scala-lang.org/scala3/book/taste-objects.html
[indentation]: https://docs.scala-lang.org/scala3/reference/other-new-features/indentation.html
[naming-conventions]: https://docs.scala-lang.org/style/naming-conventions.html
