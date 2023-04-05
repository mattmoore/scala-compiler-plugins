package io.mattmoore.scala.compiler.plugins

import dotty.tools.dotc.*
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.core.Phases.Phase
import dotty.tools.dotc.plugins.ResearchPlugin
import dotty.tools.dotc.transform.*

class InspectorResearchPlugin extends ResearchPlugin {
  val name: String = "inspector"
  override val description: String = "inspector research plugin"

  def init(options: List[String], phases: List[List[Phase]])(implicit ctx: Context): List[List[Phase]] =
    phases
}
