package com.softwaremill.demo

import com.softwaremill.demo.Gender.Gender

case class Troll(firstName: String, height: Int, bio: Option[String], gender: Gender)

object Gender extends Enumeration {
  type Gender = Value
  val Male, Female = Value
}

object Instances {
  val aTroll = Troll("Zaxabar", 205, None, Gender.Male)
}