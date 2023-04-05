import Dependencies.*

lazy val useInspector = (project in file("."))
  .settings(
    name := "Using the inspector plugin.",
    scalaVersion := scala3NightlyVersion,
    autoCompilerPlugins := true,
    addCompilerPlugin("io.mattmoore.scala.compiler.plugins" %% "inspector" % "0.0.1-SNAPSHOT")
  )
