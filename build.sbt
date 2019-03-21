name := "typeclasses"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.github.mpilquist" %% "simulacrum" % "0.13.0"
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)