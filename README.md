# Scala Compiler Plugin Example

Publish it as jar and use in your project by adding to build.sbt following lines:
```scala
autoCompilerPlugins := true
addCompilerPlugin("io.mattmoore" %% "scala-compiler-plugin-example" % "0.0.1-SNAPSHOT")
```