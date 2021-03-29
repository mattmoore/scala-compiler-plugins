ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .aggregate(scala2, scala3)
  .settings(
    name := "Using the divison-by-zero plugin.",
    crossScalaVersions := Nil,
    publish / skip := true,
  )

lazy val scala2 = project
  .settings(
    organization := "io.mattmoore.scala3.compiler.plugins",
    name := "Using the division-by-zero plugin for Scala 2.",
    scalaVersion := "2.13.5",
    autoCompilerPlugins := true,
    addCompilerPlugin("io.mattmoore.scala2.compiler.plugins" %% "division-by-zero" % "0.0.1-SNAPSHOT")
  )

lazy val scala3 = project
  .settings(
    organization := "io.mattmoore.scala2.compiler.plugins",
    name := "Using the division-by-zero plugin for Scala 3.",
    scalaVersion := "3.0.0-RC1",
    autoCompilerPlugins := true,
    addCompilerPlugin("io.mattmoore.scala3.compiler.plugins" %% "division-by-zero" % "0.0.1-SNAPSHOT")
  )

resolvers += Resolver.mavenLocal
