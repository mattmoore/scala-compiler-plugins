import Dependencies.*

lazy val divisionByZeroScala2Plugin = (project in file("."))
  .settings(
    name := "division-by-zero",
    scalaVersion := scala2Version,
    libraryDependencies ++= List(scala2Compiler)
  )
