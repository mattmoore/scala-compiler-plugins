ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val scala2Version = "2.13.10"
lazy val scala3Version = "3.2.2"

lazy val root = (project in file("."))
  .aggregate(scala2, scala3)
  .settings(
    name := "Using the divison-by-zero plugin.",
    crossScalaVersions := Nil,
    publish / skip := true,
  )

lazy val scala2 = project
  .settings(
    organization := "io.mattmoore.scala.compiler.plugins",
    name := "Using the division-by-zero plugin for Scala 2.",
    scalaVersion := scala2Version,
    autoCompilerPlugins := true,
    addCompilerPlugin("io.mattmoore.scala.compiler.plugins" %% "division-by-zero" % "0.0.1-SNAPSHOT")
  )

lazy val scala3 = project
  .settings(
    organization := "io.mattmoore.scala.compiler.plugins",
    name := "Using the division-by-zero plugin for Scala 3.",
    scalaVersion := scala3Version,
    autoCompilerPlugins := true,
    addCompilerPlugin("io.mattmoore.scala.compiler.plugins" %% "division-by-zero" % "0.0.1-SNAPSHOT")
  )

resolvers += Resolver.mavenLocal
