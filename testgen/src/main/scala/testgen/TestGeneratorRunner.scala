package testgen

import scala.io.Source

import java.nio.file.*
import java.nio.charset.StandardCharsets

lazy val allGenerators: Vector[TestGenerator[?, ?]] =
  Vector(
    generator.WordCountTestGenerator,
  )

/** Generates test code for a given exercise.
  *
  * @param exerciseSlug
  *   The slug of the exercise for which to generate test code like `word-count`
  * @param problemSpecsPath
  *   The path to the problem-specifications repository
  * @param otherParams
  *   Additional parameters like the `targetPath` for the generated test code
  */
@main
def generateTest(exerciseSlug: String, problemSpecsPath: String, otherParams: String*): Unit =
  lazy val targetPath = otherParams.headOption

  allGenerators.find(_.slug == exerciseSlug) match
    case Some(generator) =>
      val canonicalDataPath = s"$problemSpecsPath/exercises/$exerciseSlug/canonical-data.json"
      val fileContents      = Source.fromFile(canonicalDataPath).mkString

      generator.generate(fileContents) match
        case Left(value)     => println("Error generating test code: " + value)
        case Right(testCode) =>
          targetPath match
            case Some(path) =>
              Files.write(Paths.get(path), testCode.getBytes(StandardCharsets.UTF_8))
              println("Test code generated and saved to: " + path)

            case None =>
              println("Generated test code:")
              println("--------------------")
              println(testCode)
              println("--------------------")

    case None =>
      println(s"No test generator found for exercise: $exerciseSlug")
