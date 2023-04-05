import sbt.*

object Dependencies {
  lazy val scala2Version = "2.13.10"
  lazy val scala3Version = "3.2.2"
  lazy val scala3NightlyVersion = "3.2.2-RC1-bin-20221103-bf808b3-NIGHTLY"

  lazy val scala2Compiler = "org.scala-lang" % "scala-compiler" % scala2Version
  lazy val scala3Compiler = "org.scala-lang" %% "scala3-compiler" % scala3Version
}
