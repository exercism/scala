scalaVersion := "2.12.8"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

val monocleVersion = "2.0.0"

libraryDependencies ++= Seq(
  "com.github.julien-truffaut" %%  "monocle-core"  % monocleVersion,
  "com.github.julien-truffaut" %%  "monocle-macro" % monocleVersion)

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.28"

