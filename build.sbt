import Dependencies.*

ThisBuild / version := "0.0.1-SNAPSHOT"
ThisBuild / sbtPlugin := false
ThisBuild / organization := "io.mattmoore.scala.compiler.plugins"

lazy val root = (project in file("."))
  .aggregate(
    divisionByZeroPlugin,
    inspectorPlugin
  )
  .settings(
    crossScalaVersions := List(scala2Version, scala3Version),
    publish / skip := true
  )

lazy val divisionByZeroPlugin = project in file("plugins/division-by-zero")
lazy val inspectorPlugin = project in file("plugins/inspector")

lazy val useDivisionByZero = project in file("use-plugins/division-by-zero")
lazy val useInspector = project in file("use-plugins/inspector")

resolvers += Resolver.mavenLocal

lazy val publishPluginsLocal = taskKey[Unit]("myTask")
publishPluginsLocal := {
  Command.process("clean", state.value)
  Command.process("compile", state.value)
  Command.process("package", state.value)
  Command.process("publishLocal", state.value)
}
