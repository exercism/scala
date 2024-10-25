# Instructions

The first line of the CSV file contains the headings, the other lines contain the results of the participants.
Each line has the following elements separated by commas: an id, a name, then 10 points for 10 exercises.
A maximum of 10 points can be achieved in each exercise. A value of -1 means that no solution has been
submitted.  
The lines of the files can be read by:  
```scala
val lines = readCSV()
```
For the following calculations should be solved using Scala’s collections with their higher-order
functions. Try to implement each calculation by a single chain of operations. 

## Task 1: Building a list of Results objects
The first task is to build a list of Results objects from the read lines. Implement a case class Results
with an id of type Int, as name of type String and an list of points of type List[Int]. Then compute
the list of Results objects for the participants  
```scala
val resultList : List[Results] = ???
``` 

## Task 2: Number of solved tasks
From the list of Results objects, create a map that contains the number of solved exercises for each
student. An exercise is considered solved if at least 3 points have been achieved.

## Task 3: Sufficient tasks solved
A student must solve at least 8 exercises to pass the course. Determine which students have submitted
enough solutions and which have not enough solutions.
```scala
val sufficientSolved : (Set[String], Set[String]) = ...
```
_Hint: Use method partition._

## Task 4: Grading

Next, the grades should be determined. The grades are calculated based on the points: There must be at least eight solutions with at least 3
points. If this is the case, the grading is done based on the average of the best eight solutions according
to the following scheme:
```
- [0.0 .. 5.0) : INSUFFICIENT
- [5.0 .. 6.25) : SUFFICIENT
- [6.25 .. 7.5) : SATISFACTORY
- [7.5 .. 8.75) : GOOD
- [8.75 .. 1.0] : EXCELLENT
```
```scala
val grades: Map[String, Grade] = ...
 ```

## Task 5: Grade statistics

Compute statistic measurement from the grading. For each grade, compute the number of students with
that grade:
```scala
val nStudentsWithGrade : Map[Grade, Int] = ...
```

## Task 6:  Number solved per assignment
For the 10 exercises, compute how many students have solved them (at least 3 points). Result is a list of
10 numbers, which indicate how many students have solved the exercises:
```scala
val nSolvedPerAssnmt : List[(Int, Int)] = ...
```

## Task 7: Average points per assignment
For the 10 exercises, compute the average of the achieved points. Only the submitted exercises (points
!= -1) should be considered:
```scala
val avrgPointsPerAssnmt : List[(Int, Double)] = ...
```