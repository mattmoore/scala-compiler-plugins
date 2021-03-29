ThisBuild / version := "0.0.1-SNAPSHOT"
ThisBuild / sbtPlugin := false

lazy val root = (project in file("."))
  .aggregate(scala2, scala3)
  .settings(
    crossScalaVersions := Nil,
    publish / skip := true
  )

lazy val scala2 = project
  .settings(
    organization := "io.mattmoore.scala2.compiler.plugins",
    name := "division-by-zero",
    scalaVersion := "2.13.5",
    libraryDependencies ++= List(
      "org.scala-lang" % "scala-compiler" % "2.13.5"
    )
  )

lazy val scala3 = project
  .settings(
    organization := "io.mattmoore.scala3.compiler.plugins",
    name := "division-by-zero",
    scalaVersion := "3.0.0-RC1",
    libraryDependencies ++= List(
      "org.scala-lang" % "scala3-compiler_3.0.0-RC1" % "3.0.0-RC1"
    )
  )
