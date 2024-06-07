name := "ExcercismScalaTestGenerator"

scalaVersion := "3.1.3"

ThisBuild / scalacOptions ++= Seq("-deprecation")

lazy val root = (project in file("."))
  .enablePlugins(SbtTwirl)
  .settings(
    Compile / TwirlKeys.compileTemplates / sourceDirectories  += (baseDirectory.value.getParentFile / "src" / "main" / "twirl"),
      scalacOptions ++= Seq( "-Xcheck-macros" ),
    Test / classLoaderLayeringStrategy := ClassLoaderLayeringStrategy.Flat
  )

libraryDependencies ++= Seq(
    "com.typesafe.play" %% "play-json" % "2.10.0-RC6",
    "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.0",
    "com.typesafe.play" %% "twirl-api" % "1.6.0-M6",
)
