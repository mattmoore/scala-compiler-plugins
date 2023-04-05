package io.mattmoore.scala.compiler.plugins

import dotty.tools.dotc.report
import dotty.tools.dotc.ast.Trees.*
import dotty.tools.dotc.ast.tpd
import dotty.tools.dotc.core.Constants.Constant
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.core.Decorators.*
import dotty.tools.dotc.core.StdNames.*
import dotty.tools.dotc.core.Symbols.*
import dotty.tools.dotc.plugins.{PluginPhase, StandardPlugin}
import dotty.tools.dotc.transform.{PickleQuotes, Staging}

class DivisionByZero extends StandardPlugin:
  val name: String = "divideZero"
  override val description: String = "divide zero check"

  def init(options: List[String]): List[PluginPhase] =
    (new DivisionByZeroPhase) :: Nil

class DivisionByZeroPhase extends PluginPhase:
  import tpd.*

  val phaseName = "divideZero"

  override val runsAfter: Set[String] = Set(Staging.name)
  override val runsBefore: Set[String] = Set(PickleQuotes.name)

  override def transformApply(tree: Apply)(implicit ctx: Context): Tree =
    tree match
      case Apply(Select(rcvr, nme.DIV), List(Literal(Constant(0))))
        if rcvr.tpe <:< defn.IntType =>
        report.error("Attempting division by zero.", tree.sourcePos)
      case _ =>
        ()
    tree
