name := "ExcercismScalaTestGenerator"

ThisBuild / scalaVersion := "3.4.2"
ThisBuild / scalacOptions ++= Seq("-deprecation")

lazy val root = project
  .in(file("."))
  .aggregate(testgen)

lazy val testgen = project
  .enablePlugins(SbtTwirl)
  .settings(
    Compile / TwirlKeys.compileTemplates / sourceDirectories
        += (baseDirectory.value.getParentFile / "src" / "main" / "twirl")
    )

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.6"
libraryDependencies += "com.typesafe.play" %% "twirl-api" % "1.6.8"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.4.0"
