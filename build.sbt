name := "ScalaByExample"

version := "1.0"

autoCompilerPlugins := true

addCompilerPlugin("org.scala-lang.plugins" % "scala-continuations-plugin_2.11.2" % "1.0.2")

scalacOptions += "-P:continuations:enable"

scalaVersion := "2.11.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
libraryDependencies += "org.scala-lang.plugins" % "scala-continuations-library_2.11" % "1.0.2"

