package io.mattmoore.scala.compiler.plugins

import dotty.tools.dotc.*
import dotty.tools.dotc.ast.tpd.*
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.core.Phases.Phase
import dotty.tools.dotc.plugins.ResearchPlugin
import dotty.tools.dotc.transform.*

class InspectorResearchPlugin extends ResearchPlugin {
  override val name: String = "inspector"
  override val description: String = "inspector research plugin"

  def init(options: List[String], phases: List[List[Phase]])(implicit ctx: Context): List[List[Phase]] =
    phases.map {
      _.map {
        case phase if phase.phaseName == "inlining" =>
          println("REPLACING INLINING PHASE")
          new CustomInliningPhase
        case other =>
          other
      }
    }
}

class CustomInliningPhase extends Inlining {
  private[this] val compileTimeResults = scala.collection.concurrent.TrieMap.empty[String, Long]

  override def runOn(units: List[CompilationUnit])(using Context): List[CompilationUnit] = {
    println("start inlining")
    val result = super.runOn(units)
    println("end inlining")
    compileTimeResults.toList.sortBy(-_._2).take(50).foreach(println)
    result
  }
  override def run(using c: Context): Unit = {
    val start = System.currentTimeMillis()
    super.run
    val end = System.currentTimeMillis()
    compileTimeResults += (c.compilationUnit.source.path -> (end - start))
  }
}
