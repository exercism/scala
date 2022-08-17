# Instructions

A Pythagorean triplet is a set of three natural numbers, {a, b, c}, for
which,

```text
a² + b² = c²
```

and such that,

```text
a < b < c
```

For example,

```text
3² + 4² = 9 + 16 = 25 = 5².
```

Implement the method `isPythagorean((Int, Int, Int)): Boolean` which checks the given triplet. 

Then implement `pythagoreanTriplets(min: Int, max: Int): Seq[(Int, Int, Int)]` which should returns a sequence of all
triplets whose sides are in the range `min` to `max` inclusive

Finally implement `pythagoreanTripletsSum(n: Int): Seq[(Int, Int, Int)]`
Given an input integer N, find all Pythagorean triplets for which `a + b + c = N`.

For example, with N = 1000, there is exactly one Pythagorean triplet for which `a + b + c = 1000`: `{200, 375, 425}`.
