# Design

## Goal

The goal of this exercise is to teach the student the basics of programming in Scala.

## Learning objectives

- Know how to define an immutable value with `val`.
- Know that a mutable variable is defined with `var`, and that `val` is preferred.
- Know how to use type inference for values.
- Know how to define a method.
- Know how to return a value from a method.
- Know how to call a method.
- Know that methods can be grouped in an `object`.
- Know how to define an integer.
- Know how to use mathematical operators on integers.
- Know how to define single-line and multiline comments.

## Out of scope

- Naming rules for identifiers.
- Generic values.
- Memory and performance characteristics.
- Method overloads.
- Nested methods.
- Lambdas.
- Named parameters.
- Optional parameters.
- Classes and their instances.
- Companion objects.
- Packages and imports.
- Visibility.

## Concepts

- `basics`: know how to define an immutable value with `val`; know that a mutable variable is defined with `var`, and that `val` is preferred; know how to use type inference for values; know how to define a method; know how to return a value from a method; know how to call a method; know that methods can be grouped in an `object`; know how to define an integer; know how to use mathematical operators on integers; know how to define single-line and multiline comments.

## Prerequisites

There are no prerequisites.

## Analyzer

This exercise could benefit from the following rules in the [analyzer]:

- `actionable`: if `remainingMinutesInOven` repeats the literal `40`, instruct the student to call `expectedMinutesInOven` instead.
- `actionable`: if `elapsedTimeInMinutes` repeats the multiplication by 2, instruct the student to call `preparationTimeInMinutes` instead.
- `informative`: if the solution uses the `return` keyword, tell the student that a method returns the value of its last expression.
- `informative`: if the solution uses `var`, tell the student that `val` is enough here.

[analyzer]: https://github.com/exercism/scala-analyzer
