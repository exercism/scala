scalaVersion := "3.4.2"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test

val monocleVersion = "3.2.0"

libraryDependencies ++= Seq(
  "dev.optics" %%  "monocle-core"  % monocleVersion,
  "dev.optics" %%  "monocle-macro" % monocleVersion
)

// used for solution example only, not provided in the test runner
libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.3.8"

