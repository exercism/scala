package testgen
package generator

import java.io.File

import scala.io.Source

/** Tool to verify implemented version of exercises vs the canonical version. The scala project and the problem-specifications project should be pulled before
  * running this tool.
  */
object VersionCheck:
  private def getImplementedVersion(file: File): String =
    val prefix      = "/** @version "
    val suffix1     = " */"
    val suffix2     = " **/"
    val versionLine = Source
      .fromFile(file)
      .getLines
      .find(l => l.startsWith(prefix))
    versionLine match
      case Some(l) =>
        l.stripPrefix(prefix)
          .stripSuffix(suffix1)
          .stripSuffix(suffix2)
      case None    => "No version in file"

  private def getCanonicalVersion(file: File): String =
    val exercise @ Exercise(name, version, cases, comments) =
      CanonicalDataParser.parse(file)
    version

  private def getCanonicaDataPath(exerciseName: String, problemSpecDir: String): File =
    new File(new File(problemSpecDir, exerciseName), "canonical-data.json")

  private val ignoreExercises: Set[String] =
    Source.fromFile(new File("./src/main/resources/version-check-ignore.txt")).getLines.toSet

  def main(args: Array[String]): Unit =
    if args.length != 1 then
      println("Usage VersionCheck /path/ where path is the filesystem path to the problem-specifications exercises directory.")
      System.exit(-1)

    val problemSpecDir = args.head

    val exerciseDirs = new File("../exercises")
      .listFiles()
      .toList
      .filter(f => f.isDirectory)
      .filter(f => !ignoreExercises.contains(f.getName))
      .sorted

    exerciseDirs.foreach(f =>
      new File(f, "src/test/scala")
        .listFiles()
        .toList
        .filter(testFile => testFile.getName.endsWith("Test.scala")) match
        case x :: _ =>
          val implementedVersion = getImplementedVersion(x)
          val canonicalDataPath  = getCanonicaDataPath(f.getName, problemSpecDir)
          if canonicalDataPath.exists() then
            val canonicalVersion = getCanonicalVersion(canonicalDataPath)
            if !implementedVersion.equals(canonicalVersion) then
              println(s"${f.getName}\timplemented version - $implementedVersion\tcanonical version - $canonicalVersion")
        case _      =>,
    )
