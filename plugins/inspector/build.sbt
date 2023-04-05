import Dependencies.*

lazy val inspectorPlugin = (project in file("."))
  .settings(
    name := "inspector",
    scalaVersion := scala3Version,
    libraryDependencies ++= List(scala3Compiler)
  )
