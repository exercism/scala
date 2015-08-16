import java.io.File

import sbt.Keys._
import sbt._

import scala.io.Source
import scala.util.parsing.json.{JSONArray, JSONObject, JSON}

object XScalaBuild extends Build {

  scalaVersion := "2.11.7"
  name := "xscala"

  val commonSettings = Seq(
    updateOptions := updateOptions.value.withCachedResolution(true),

    (unmanagedSources in Test) <<= (unmanagedSources in Test, sourceManaged in Test) map {
      case (testSources, managedTestDir) =>
        copyTestsAndStripPending(testSources, managedTestDir)
    }
  )

  override def projects: Seq[Project] = {
    fetchProblemNames("config.json").map {
      problemName => Project(problemName, new File(problemName)).settings(commonSettings:_*)
    }
  }

  def copyTestsAndStripPending(tests: Seq[File], managedSourceDir: File): Seq[File] = {
    tests.map { originalTestFile =>
      val filename = originalTestFile.name
      val newTestFile = managedSourceDir / filename
      IO.writeLines(newTestFile, IO.readLines(originalTestFile).filter(_.trim != "pending"))
      newTestFile
    }
  }

  def fetchProblemNames(configJsonFile: String): Seq[String] = {
    val configStr = Source.fromFile(configJsonFile, "UTF-8").getLines().mkString
    val configObj = JSON
      .parseRaw(configStr)
      .map(_.asInstanceOf[JSONObject].obj)
      .getOrElse {
        throw new IllegalArgumentException(s"Could not parse $configJsonFile as JSON")
      }
    configObj
      .get("problems")
      .map(_.asInstanceOf[JSONArray].list)
      .getOrElse {
        throw new IllegalArgumentException(s"Could not find array 'problems' in $configJsonFile")
      }
      .collect { case problem: String => problem }
  }
}
