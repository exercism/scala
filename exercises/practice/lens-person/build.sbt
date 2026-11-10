scalaVersion := "3.9.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test

val monocleVersion = "3.2.0"

libraryDependencies ++= Seq(
  "dev.optics" %% "monocle-core"  % monocleVersion,
  "dev.optics" %% "monocle-macro" % monocleVersion,
)

