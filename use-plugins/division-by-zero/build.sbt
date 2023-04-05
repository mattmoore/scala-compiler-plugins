import Dependencies._

lazy val useDivisionByZero = (project in file("."))
  .aggregate(useDivisionByZeroScala2, useDivisionByZeroScala3)
  .settings(
    name := "Using the divison-by-zero plugin.",
    crossScalaVersions := List(scala2Version, scala3Version),
    publish / skip := true
  )

lazy val useDivisionByZeroScala2 = (project in file("scala2"))
  .settings(
    name := "Using the division-by-zero plugin for Scala 2.",
    scalaVersion := scala2Version,
    autoCompilerPlugins := true,
    addCompilerPlugin("io.mattmoore.scala.compiler.plugins" %% "division-by-zero" % "0.0.1-SNAPSHOT")
  )

lazy val useDivisionByZeroScala3 = (project in file("scala3"))
  .settings(
    name := "Using the division-by-zero plugin for Scala 3.",
    scalaVersion := scala3Version,
    autoCompilerPlugins := true,
    addCompilerPlugin("io.mattmoore.scala.compiler.plugins" %% "division-by-zero" % "0.0.1-SNAPSHOT")
  )

resolvers += Resolver.mavenLocal
