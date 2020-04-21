package io.mattmoore

import scala.tools.nsc
import nsc.Global
import nsc.Phase
import nsc.plugins.Plugin
import nsc.plugins.PluginComponent

class CompilerPlugin(val global: Global) extends Plugin {

  import global._

  val name = "divbyzero"
  val description = "checks for division by zero"
  val components = List[PluginComponent](Component)

  private object Component extends PluginComponent {
    val global: CompilerPlugin.this.global.type = CompilerPlugin.this.global
    val runsAfter = List[String]("refchecks")
    val phaseName = CompilerPlugin.this.name

    def newPhase(_prev: Phase) = new DivByZeroPhase(_prev)

    class DivByZeroPhase(prev: Phase) extends StdPhase(prev) {
      override def name = CompilerPlugin.this.name

      def apply(unit: CompilationUnit): Unit = {
        for (tree@Apply(Select(rcvr, nme.DIV), List(Literal(Constant(0)))) <- unit.body
             if rcvr.tpe <:< definitions.IntClass.tpe) {
          global.reporter.error(tree.pos, "definitely division by zero")
        }
      }
    }

  }

}
