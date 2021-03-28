import Dependencies._

ThisBuild / scalaVersion     := "2.13.5"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "io.mattmoore"

lazy val root = (project in file("."))
  .settings(
    name := "Using the division-by-zero plugin.",
    libraryDependencies += scalaTest % Test
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.

autoCompilerPlugins := true

addCompilerPlugin("io.mattmoore.scala2.compiler.plugins" %% "division-by-zero" % "0.0.1-SNAPSHOT")

resolvers += Resolver.mavenLocal
