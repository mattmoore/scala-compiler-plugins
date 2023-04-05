import Dependencies._

lazy val useDivisionByZero = (project in file("."))
  .aggregate(scala2, scala3)
  .settings(
    name := "Using the divison-by-zero plugin.",
    crossScalaVersions := List(scala2Version, scala3Version),
    publish / skip := true,
  )

lazy val scala2 = project
  .settings(
    name := "Using the division-by-zero plugin for Scala 2.",
    scalaVersion := scala2Version,
    autoCompilerPlugins := true,
    addCompilerPlugin("io.mattmoore.scala.compiler.plugins" %% "division-by-zero" % "0.0.1-SNAPSHOT")
  )

lazy val scala3 = project
  .settings(
    name := "Using the division-by-zero plugin for Scala 3.",
    scalaVersion := scala3Version,
    autoCompilerPlugins := true,
    addCompilerPlugin("io.mattmoore.scala.compiler.plugins" %% "division-by-zero" % "0.0.1-SNAPSHOT")
  )

resolvers += Resolver.mavenLocal
