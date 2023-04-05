import Dependencies.*

lazy val divisionByZeroScala3Plugin = (project in file("."))
  .settings(
    name := "division-by-zero",
    scalaVersion := scala3Version,
    libraryDependencies ++= List(scala3Compiler)
  )
