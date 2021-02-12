# Hints

This exercise is testing mutable state that can be accessed saftely from multiple threads. Scala provides a variety of ways to protect 
mutable state. For developers familiar with Java concurrency, Scala can utilize the Java concurrency support such as the Java synchronized block.

## Common Pitfalls

In Scala there are two ways to achieve mutable state: Use a "var" or a mutable object.
Two common mistakes here are:
- Do not use a "var" that is also a mutable object. One is enough, but not both together.
- Don't expose the "var" or mutable object to the outside world. So make them "private" and change the mutable object into immutable before you return it as a value.
