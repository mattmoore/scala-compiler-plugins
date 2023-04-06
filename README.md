# Scala 2 / Scala 3 Compiler Plugin Examples

Examples of how to build Scala compiler plugins for both Scala 2 and Scala 3.

## Why would I write a compiler plugin?

Compiler plugins allow you to extend the Scala programming language.

You could enhance compile-time checking - for example, compile-time checking of division by zero, which is one of the examples contained in this project. Compile-time checking of code is a useful feature because it allows us to catch possible issues before we try to run the program.

Another reason might be extending the language with DSLs, or replacing existing code with different code - perhaps replacing placeholders with expanded functionality like automatic logging, or auto-generating code.

## A note on Scala 2 / Scala 3 Compatibility

The Scala 3 compiler is also called `dotty`.

Compiler plugins for Scala 2 and Scala 3 are incompatible. Scala 3 has changed the plugin system, and it is quite different from the way Scala 2 works. Scala 3 removed the older analysis plugins that Scala 2 had. Instead, it now provides the `StandardPlugin` and `ResearchPlugin` types.

`StandardPlugin`: What you'll use in 99% of cases, given that `ResearchPlugin` does not work with stable production builds of Scala 3. You will typically want to use a `StandardPlugin` for any plugins intended for general use. One major difference from Scala 2 to Scala 3 is that the new `StandardPlugin` has no ability to modify type information at compile time anymore, unlike Scala 2's `AnalyzerPlugin`. This is to prevent a compiler author from breaking type safety.

`ResearchPlugin`: Allows you to fully control all phases across the compilation pipeline. For more information, see [Changes in Compiler Plugins](https://dotty.epfl.ch/docs/reference/changed-features/compiler-plugins.html). Using a `ResearchPlugin` requires a snapshot or nightly version of the Scala 3 compiler to run. If you try using them with the stable release of Scala 3, the compiler won't emit a warning, the research plugin simply won't work. If you find yourself scratching your head, check that you are using an appropriate snapshot version of the compiler.

## Repo Structure

There are two categories in this repo:

1. [plugins](plugins) The Scala compiler plugins.
2. [use-plugins](use-plugins) Examples using the plugins created above. Each project in here is named after the plugin it uses.

## Example Plugins

The first step here is to compile and publish all the plugins locally.

From the shell:

```shell
sbt publishPluginsLocal
```
Or from an sbt session (by typing `sbt` and hitting enter):

```shell
publishPluginsLocal
```

Note: `publishPluginsLocal` is a custom `sbt` task. It invokes the following `sbt` commands:

```shell
sbt clean compile package publishLocal
```

Or from an sbt session:
```shell
clean;compile;package;publishLocal
```

### [Division by Zero Plugin](plugins/division-by-zero) (`StandardPlugin` example)

<details>
<summary>Checks at compile time for division by zero</summary>
Use the plugin in your project by adding to build.sbt following lines:

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
</details>

### [Inspector Plugin](plugins/inspector) (`ResearchPlugin` example)

<details>
<summary>Replaces Scala 3 default inlining phase</summary>

Use the plugin in your project by adding to build.sbt following lines:

```scala
autoCompilerPlugins := true
addCompilerPlugin("io.mattmoore.scala.compiler.plugins" %% "inspector" % "0.0.1-SNAPSHOT")
resolvers += Resolver.mavenLocal
```

The `build.sbt` configuration above is in the [use-plugins/inspector/build.sbt](use-plugins/inspector/build.sbt) file already. You can test this existing project. From the project's root directory:

Next, compile the example project. From your shell:

```shell
sbt useInspector/clean useInspector/compile
```

Or from an `sbt` session:

```shell
useInspector/clean;useInspector/compile
```

You'll notice the following output from the compiler:

```shell
REPLACING INLINING PHASE
start inlining
end inlining
(/.../scala-compiler-plugin-examples/use-plugins/inspector/src/main/scala/io/mattmoore/scala/compiler/plugins/Inspector.scala,0)
```

What's happened is we've replaced the default `inlining` phase of the Scala 3 compiler with our own custom phase. The custom phase executes the standard `inlining` phase, but wraps the `inlining` phase with benchmarking info.
</details>
