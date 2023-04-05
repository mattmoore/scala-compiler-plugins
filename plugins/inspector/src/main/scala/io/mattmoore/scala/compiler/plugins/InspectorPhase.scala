package io.mattmoore.scala.compiler.plugins

import dotty.tools.dotc._

import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.core.Phases.Phase
import transform._
import ast.tpd

class InspectorPhase extends MegaPhase.MiniPhase {
    import tpd._

    val phaseName: String = "InspectorPhase"
    private var enterSym: Symbol = _

    override val runsAfter = Set(Pickler.name)

    override def transformDefDef(tree: DefDef)(using Context): Tree = {
        println("TEST")
        tree
    }
}
