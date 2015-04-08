organization  := "com.softwaremill"

name := "supler-pres"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.5"

val sprayVersion  = "1.3.1"

libraryDependencies ++= Seq(
  "com.softwaremill.supler" %% "supler" % "0.3.0",
  // spray/akka
  "com.typesafe.akka" %% "akka-actor" % "2.3.4",
  "io.spray" %% "spray-can" % sprayVersion,
  "io.spray" %% "spray-routing" % sprayVersion,
  "io.spray" %% "spray-httpx" % sprayVersion,
  // util
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.scalatest" %% "scalatest" % "2.2.2" % "test",
  "joda-time" % "joda-time" % "2.5",
  "org.joda" % "joda-convert" % "1.7"
)
