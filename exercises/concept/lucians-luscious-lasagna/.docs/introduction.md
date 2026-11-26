# Introduction

## Basics

Scala is a statically typed language: every value has a type, and the compiler checks the types before the program runs.

### Values and variables

A value is defined with `val`, by giving a name to the result of an expression.
A `val` is immutable: once defined, it cannot be assigned again.

```scala
val layers = 3

layers = 4
// => compile error: Reassignment to val layers
```

A variable is defined with `var` and can be reassigned.
Idiomatic Scala prefers `val`, and uses `var` only where mutation is really needed.

```scala
var count = 1
count = 2
```

The type can be written after the name, or left out, in which case the compiler infers it from the expression.
This is known as type inference.

```scala
val explicit: Int = 10 // the type is written out
val inferred = 10      // the type is inferred to be Int
```

### Integers

Whole numbers have the type `Int`.
They support the usual arithmetic operators, such as `+`, `-` and `*`.

```scala
val minutes = 2 * 20 + 5
// => 45
```

### Methods

A method is defined with `def`, followed by its name, its parameters, its return type and, after `=`, its body.
The type of each parameter has to be written; the return type can be inferred, but it is good practice to write it for methods that others call.

```scala
def add(x: Int, y: Int): Int = x + y
```

A method returns the value of the last expression of its body, so no `return` keyword is needed.
A longer body is written on the following lines, indented:

```scala
def add(x: Int, y: Int): Int =
  val sum = x + y
  sum
```

A method that takes no parameters and only computes a value is usually defined without parentheses.
It is then called without parentheses too.

```scala
def answer: Int = 42
```

### Objects

Methods and values can be grouped in an `object`.
The body of the object follows a colon and is indented.

```scala
object Calculator:
  val Zero = 0

  def add(x: Int, y: Int): Int = x + y
```

A method is called on the object it is defined in, with the arguments in parentheses.
Inside the object, the name of the method is enough.

```scala
Calculator.add(5, 7)
// => 12
```

### Comments

Scala has single-line comments and comments that can span several lines.

```scala
// This is a single-line comment

/*
This comment spans
several lines
*/
```
