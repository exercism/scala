name := "ExcercismScalaTestGenerator"

ThisBuild / scalaVersion := "3.4.2"
ThisBuild / scalacOptions ++= Seq("-source:future")

lazy val root = project
  .in(file("."))
  .aggregate(testgen)

lazy val testgen = project
  .enablePlugins(SbtTwirl)
  .settings(
    Compile / TwirlKeys.compileTemplates / sourceDirectories
        += (baseDirectory.value.getParentFile / "src" / "main" / "twirl")
    )
  .settings(
    libraryDependencies += "org.playframework" %% "play-json" % "3.0.4",
    libraryDependencies += "org.playframework.twirl" %% "twirl-api" % "2.0.7",
    libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.4.0",
    libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.15.0"
  )

