import Dependencies._

lazy val divisionByZeroPlugin = (project in file("."))
  .aggregate(
    divisionByZeroScala2Plugin,
    divisionByZeroScala3Plugin
  )
  .settings(
    crossScalaVersions := List(scala2Version, scala3Version),
    publish / skip := true
  )

lazy val divisionByZeroScala2Plugin = project in file("scala2")
lazy val divisionByZeroScala3Plugin = project in file("scala3")
