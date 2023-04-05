# Scala 2 / Scala 3 Compiler Plugin Examples

This is an example of how to build a simple Scala compiler plugin. The plugin example checks for division by zero at compile-time and fail to compile if division by zero is attempted.

Compile-time checking of code is a useful feature because it allows us to catch possible issues before we try to run the program. Scala does a lot of awesome checking at compile time, but there are some things it doesn't do (such as division by zero).

## Repo Structure

There are two categories in this repo:

1. [plugins](plugins) The Scala compiler plugins.
2. [use-plugins](use-plugins) Examples using the plugins created above. Each project in here is named after the plugin it uses.

## List of Plugins

### Division by Zero

#### A simple Scala project that uses the [division-by-zero plugin](plugins/division-by-zero).

First, compile and publish the plugin as a jar file to your local Ivy repo:

From the shell:

```shell
sbt clean compile package publishLocal
```
Or from an sbt session (by typing `sbt` and hitting enter):

```shell
clean;compile;package;publishLocal
```

Next, use the plugin in your project by adding to build.sbt following lines:

```scala
autoCompilerPlugins := true
addCompilerPlugin("io.mattmoore.scala.compiler.plugins" %% "division-by-zero" % "0.0.1-SNAPSHOT")
resolvers += Resolver.mavenLocal
```

The `build.sbt` configuration above is in the [use-plugins/division-by-zero/build.sbt](use-plugins/division-by-zero/build.sbt) file already. You can test this existing project. From the project's root directory:

From your shell:

```shell
sbt useDivisionByZero/clean useDivisionByZero/compile
```

Or from an `sbt` session:

```shell
useDivisionByZero/clean;useDivisionByZero/compile
```

You can experiment with turning the plugin on or off. Just comment the `addCompilerPlugin` line out to disable it.

When the plugin is disabled, the [division-by-zero example](use-plugins/division-by-zero) project will compile successfully. When you run it, the program will fail because it attempts to do division by zero.

When the plugin is enabled, the project will fail to compile, throwing an error that division by zero is being attempted.

## Scala 2 vs Scala 3

Compiler plugins for Scala 2 and Scala 3 are incompatible. Scala 3 has changed the plugin system and it is quite different from the way Scala 2 works.

Scala 3 removed the older analysis plugins that Scala 2 had. Instead, it now provides the `StandardPlugin` and `ResearchPlugin` types. Standard plugins do not have the ability to modify type information at compile time anymore. This is to prevent a compiler author from breaking type safety. If you need to fully control all phases, you would instead create a research plugin that allows full control of the compilation pipeline. For more information, see [Changes in Compiler Plugins](https://dotty.epfl.ch/docs/reference/changed-features/compiler-plugins.html).
