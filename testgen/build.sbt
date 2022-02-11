name := "ExcercismScalaTestGenerator"

scalaVersion := "2.13.6"

ThisBuild / scalacOptions ++= Seq("-deprecation")

lazy val root = (project in file("."))
  .enablePlugins(SbtTwirl)
  .settings(
    Compile / TwirlKeys.compileTemplates / sourceDirectories
        += (baseDirectory.value.getParentFile / "src" / "main" / "twirl"))

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.2"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.0"

libraryDependencies += "com.typesafe.play" %% "twirl-api" % "1.5.0"
