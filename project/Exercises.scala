import sbt._
import sbt.Keys._
import sjsonnew.shaded.scalajson.ast.unsafe.{JArray, JObject, JString, JValue}
import sjsonnew.support.scalajson.unsafe.Parser

/** Adds one subproject per exercise to the build.
  *
  * sbt only picks up projects that are `lazy val`s of a `.sbt` file, so the generated
  * ones are contributed by a plugin instead.
  */
object ExercisesPlugin extends AutoPlugin {
  override def trigger = allRequirements
  override def requires = empty
  override lazy val extraProjects: Seq[Project] = Exercises.projects
}

/** The track's exercises, as sbt subprojects.
  *
  * Every exercise in `exercises/{concept,practice}` becomes a subproject whose base
  * directory is the exercise itself, so that a single sbt session can verify all of
  * them (see the `exercises` project in build.sbt and `bin/test`).
  *
  * A subproject differs from what a student gets in two ways:
  *
  *   - it compiles the exemplar/example solution instead of the stub;
  *   - it compiles the tests with the `pending` markers blanked out, so that every test
  *     runs, not just the first one.
  *
  * Neither is done by editing the exercise: the sources handed to the compiler are
  * assembled under `target/`, leaving the exercise directories untouched.
  */
object Exercises {

  /** The directories holding the exercises, in the order they are verified. */
  private val kinds = Seq("concept", "practice")

  /** A `pending` on a line of its own: the marker a student deletes to enable a test. */
  private val pendingMarker = """\s*pending\s*""".r

  /** sbt loads a build with the build's root directory as the working directory. */
  private lazy val repoRoot: File = file(".").getCanonicalFile

  final case class Exercise(kind: String, slug: String, dir: File)

  /** Every exercise of the track. Slugs are unique per track, so they double as project ids. */
  lazy val all: Seq[Exercise] =
    for {
      kind <- kinds
      dir <- ((repoRoot / "exercises" / kind) * DirectoryFilter).get().sortBy(_.getName)
    } yield Exercise(kind, dir.getName, dir)

  lazy val projects: Seq[Project] = all.map(project)

  /** Tests every exercise, reporting all failures instead of stopping at the first one. */
  def testAll: Def.Initialize[Task[Unit]] = {
    val each = all.map(e => Def.task(e.slug -> (LocalProject(e.slug) / Test / test).result.value))
    val results = each.joinWith(_.join)
    Def.task {
      val log = streams.value.log
      val failed = results.value.collect { case (slug, Inc(_)) => slug }
      if (failed.nonEmpty) {
        failed.foreach(slug => log.error(s"$slug failed, re-run it with: bin/test $slug"))
        throw new MessageOnlyException(s"${failed.size} of ${all.size} exercises failed")
      }
    }
  }

  private def project(exercise: Exercise): Project =
    Project(id = exercise.slug, base = exercise.dir)
      .settings(
        // Exercise directories are shipped to students and copied around by
        // bin/verify-exercises-in-docker, so keep sbt's output out of them.
        target := repoRoot / "target" / exercise.kind / exercise.slug,
        // Compile the exemplar/example solution rather than the stub a student starts from,
        Compile / unmanagedSourceDirectories := Nil,
        Compile / unmanagedSources := solution(exercise),
        // and a copy of the tests that has the `pending` markers blanked out.
        Test / unmanagedSourceDirectories := Nil,
        Test / sourceGenerators += enabledTests(exercise).taskValue
      )

  /** The exemplar/example solution, plus whatever else the stub is accompanied by. */
  private def solution(exercise: Exercise): Seq[File] = {
    val config = ExerciseConfig(exercise.dir)
    val stubs = config.files("solution").toSet
    val example = config.files("exemplar") ++ config.files("example")
    scalaSourcesIn(exercise.dir / "src" / "main" / "scala").filterNot(stubs) ++ example
  }

  /** The exercise's tests, with the `pending` markers blanked out. */
  private def enabledTests(exercise: Exercise): Def.Initialize[Task[Seq[File]]] = Def.task {
    val tests = exercise.dir / "src" / "test" / "scala"
    val generatedDir = (Test / sourceManaged).value
    scalaSourcesIn(tests).map { test =>
      // Blanked rather than deleted so that line numbers still match the original test file.
      val enabled = IO.readLines(test).map {
        case pendingMarker() => ""
        case line            => line
      }
      val generated = generatedDir / IO.relativize(tests, test).getOrElse(test.getName)
      // Rewriting an unchanged file would recompile the tests on every run.
      if (!generated.exists || IO.readLines(generated) != enabled) {
        IO.createDirectory(generated.getParentFile)
        IO.writeLines(generated, enabled)
      }
      generated
    }
  }

  private def scalaSourcesIn(dir: File): Seq[File] = (dir ** "*.scala").get().sorted
}

/** The parts of an exercise's `.meta/config.json` that this build needs. */
private final class ExerciseConfig(exerciseDir: File, json: JValue) {

  /** The paths listed under `files.<key>`, resolved against the exercise directory. */
  def files(key: String): Seq[File] =
    field(json, "files").flatMap(field(_, key)).toSeq.flatMap {
      case JArray(paths) => paths.toSeq.collect { case JString(path) => exerciseDir / path }
      case _             => Nil
    }

  private def field(json: JValue, name: String): Option[JValue] = json match {
    case obj: JObject => obj.value.collectFirst { case f if f.field == name => f.value }
    case _            => None
  }
}

private object ExerciseConfig {
  def apply(exerciseDir: File): ExerciseConfig =
    new ExerciseConfig(exerciseDir, Parser.parseFromFile(exerciseDir / ".meta" / "config.json").get)
}
