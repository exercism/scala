name := "ExcercismScalaTestGenerator"

ThisBuild / scalaVersion := "3.9.0"

lazy val root = project
  .in(file("."))
  .aggregate(testgen)

lazy val testgen = project
  .enablePlugins(SbtTwirl)
  .settings(
    scalacOptions ++= Seq("-source:future"),
    Compile / TwirlKeys.compileTemplates / sourceDirectories
        += (baseDirectory.value.getParentFile / "src" / "main" / "twirl")
    )
  .settings(
    libraryDependencies += "org.playframework" %% "play-json" % "3.0.4",
    libraryDependencies += "org.playframework.twirl" %% "twirl-api" % "2.0.7",
    libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.4.0",
    libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.15.0"
  )

// Each exercise is a subproject of this build (see project/Exercises.scala), so all of
// them are verified in a single sbt session: `sbt exercises/test` tests every exercise,
// `sbt <slug>/test` a single one. `bin/test` wraps both.
lazy val exercises = project
  .in(file("exercises"))
  .settings(
    target := file("target") / "exercises",
    Test / test := Exercises.testAll.value
  )
