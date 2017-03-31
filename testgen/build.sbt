name := "ExcercismScalaTestGenerator"

scalaVersion := "2.11.8"

lazy val root = (project in file("."))
  .enablePlugins(SbtTwirl)
  .settings(
    sourceDirectories in (Compile, TwirlKeys.compileTemplates) += (baseDirectory.value.getParentFile / "src" / "main" / "twirl"))

libraryDependencies += "com.typesafe.play" % "play-json_2.11" % "2.5.3"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"

libraryDependencies += "com.typesafe.play" %% "twirl-api" % "1.3.0"
