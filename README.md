# Scala Compiler Plugin Example

This is an example of how to build a simple Scala compiler plugin. The plugin example checks for division by zero at compile-time and fail to compile if division by zero is attempted.

Compile-time checking of code is a useful feature because it allows us to catch possible issues before we try to run the program. Scala does a lot of awesome checking at compile time, but there are some things it doesn't do (such as division by zero).

## Projects

There are two sbt projects in this repo:

1. [plugin](plugin) The Scala compiler plugin itself.
1. [use-plugin](use-plugin) A simple Scala project that uses the plugin.

First, compile and publish the plugin as a jar file to your local Ivy repo:

```scala
sbt compile package publishLocal
```

Next, use the plugin in your project by adding to build.sbt following lines:

```scala
autoCompilerPlugins := true
addCompilerPlugin("io.mattmoore" %% "scala-compiler-plugin-example" % "0.0.1-SNAPSHOT")
resolvers += Resolver.mavenLocal
```

## Running the Experiment

The `build.sbt` configuration above is in the [use-plugin](use-plugin/build.sbt) file already.

You can experiment with turning the plugin on or off. Just comment the `addCompilerPlugin` line out to disable it.

When the plugin is disabled, the [use-plugin](use-plugin) project will compile successfully. When you run it, the program will fail because it attempts to do division by zero.

When the plugin is enabled, the project will fail to compile, throwing an error that division by zero is being attempted.
