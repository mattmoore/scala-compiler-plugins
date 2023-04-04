ThisBuild / version := "0.0.1-SNAPSHOT"
ThisBuild / sbtPlugin := false

lazy val scala2Version = "2.13.10"
lazy val scala3Version = "3.2.2"

lazy val root = (project in file("."))
  .aggregate(scala2, scala3)
  .settings(
    crossScalaVersions := Seq(scala2Version, scala3Version),
    publish / skip := true
  )

lazy val scala2 = project
  .settings(
    organization := "io.mattmoore.scala2.compiler.plugins",
    name := "division-by-zero",
    scalaVersion := scala2Version,
    libraryDependencies ++= List(
      "org.scala-lang" % "scala-compiler" % scala2Version
    )
  )

lazy val scala3 = project
  .settings(
    organization := "io.mattmoore.scala3.compiler.plugins",
    name := "division-by-zero",
    scalaVersion := scala3Version,
    libraryDependencies ++= List(
      "org.scala-lang" %% "scala3-compiler" % scala3Version
    )
  )
