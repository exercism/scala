scalaVersion := "3.1.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test"

val monocleVersion = "2.0.0"

libraryDependencies ++= Seq(
  "com.github.julien-truffaut" %%  "monocle-core"  % monocleVersion,
  "com.github.julien-truffaut" %%  "monocle-macro" % monocleVersion)

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.28"

