package io.mattmoore

import scala.tools.nsc.{Global, Phase}
import scala.tools.nsc.plugins._
import scala.tools.nsc.transform._

class CompilerPlugin(override val global: Global) extends Plugin {

  override val name: String = "scala-compiler-plugin"
  override val description: String = "scala compiler plugin simple example"

  override val components: List[PluginComponent] = List(new CompilerPluginComponent(global))
}

class CompilerPluginComponent(val global: Global) extends PluginComponent with TypingTransformers {
  import global._
  override val phaseName: String = "compiler-plugin-phase"
  override val runsAfter: List[String] = List("parser")

  override def newPhase(prev: Phase): Phase = new StdPhase(prev) {
    override def apply(unit: global.CompilationUnit): Unit = {
      unit.body = new MyTypingTransformer(unit).transform(unit.body)
    }
  }

  class MyTypingTransformer(unit: CompilationUnit) extends TypingTransformer(unit) {
    override def transform(tree: Tree) = tree match {
      case dd: DefDef if (dd.mods.annotations.size > 0) =>
        println(dd)
        val ddd = treeCopy.DefDef(dd, dd.mods, dd.name,
          dd.tparams, dd.vparamss, dd.tpt, Block(
            q"""println("Inside - before")""",
            DefDef(Modifiers(), TermName("runMethod"), List(),
              List(), TypeTree(), dd.rhs),
            q"val r = runMethod",
            q"""println("Inside - after")""",
            q"r"
          ))
        println(ddd)
        ddd
      case _ => super.transform(tree)
    }
  }
  def newTransformer(unit: CompilationUnit) =
    new MyTypingTransformer(unit)
}
