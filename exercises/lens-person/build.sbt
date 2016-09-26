scalaVersion := "2.11.8"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.5" % "test"

libraryDependencies ++= Seq(
  "com.github.julien-truffaut" % "monocle-core_2.11" % "1.2.2",
  "com.github.julien-truffaut" % "monocle-macro_2.11" % "1.2.2"
)

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.6"

