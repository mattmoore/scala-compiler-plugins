import Dependencies._

lazy val useInspector = (project in file("."))
  .settings(
    name := "Using the divison-by-zero plugin.",
    scalaVersion := scala3Version,
    autoCompilerPlugins := true,
    addCompilerPlugin("io.mattmoore.scala.compiler.plugins" %% "inspector" % "0.0.1-SNAPSHOT")
  )
