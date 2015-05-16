name := """Dembol Homepage"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  ws,
  filters,
  "com.google.code.gson" % "gson" % "2.2.4",
  "com.netaporter" %% "scala-uri" % "0.4.6"
)

