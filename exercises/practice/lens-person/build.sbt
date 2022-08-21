scalaVersion := "3.1.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test"

val monocleVersion = "3.1.0"

libraryDependencies ++= Seq(
  "dev.optics" %%  "monocle-core"  % monocleVersion,
  "dev.optics" %%  "monocle-macro" % monocleVersion)

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.3.6"

