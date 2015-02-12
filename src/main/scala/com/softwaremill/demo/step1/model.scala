package com.softwaremill.demo.step1

case class Troll(firstName: String, height: Int, bio: Option[String], gender: String)

object Instances {
  val aTroll = Troll("Zaxabar", 205, None, "male")
}