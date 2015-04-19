package com.softwaremill.demo.step2

import com.softwaremill.demo.step2.Gender.Gender

case class Troll(firstName: String, lastName: String, height: Int, bio: Option[String], gender: Gender)

object Gender extends Enumeration {
  type Gender = Value
  val Male, Female = Value
}

object Instances {
  val aTroll = Troll("Zaxabar", "Harft", 205, None, Gender.Male)
}