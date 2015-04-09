package com.softwaremill.demo.step2

import com.softwaremill.demo.step2.Country.Country
import com.softwaremill.demo.step2.Gender.Gender

case class Troll(firstName: String, lastName: String, height: Int, bio: Option[String], gender: Gender,
  likesMountains: List[Mountain])

object Gender extends Enumeration {
  type Gender = Value
  val Male, Female = Value
}

case class Mountain(name: String, height: Int, country: Country, range: String, snowy: Boolean)

object Mountain {
  val empty = Mountain("", 0, Country.Norway, "", snowy = false)
}

object Country extends Enumeration {
  type Country = Value
  val Norway, Poland = Value

  def rangesForCountry(c: Country) = c match {
    case Norway => List("Norefjell", "Trollheimen", "Smiubelgen")
    case Poland => List("Bieszczady", "Tatry", "Beskidy")
  }
}

object Instances {
  val mountain1 = Mountain("Gråfjell", 1468, Country.Norway, "Norefjell", snowy = true)
  val mountain2 = Mountain("Trolla", 1850, Country.Norway, "Trollheimen", snowy = true)
  val mountain3 = Mountain("Trolltinden", 2018, Country.Norway, "Smiubelgen", snowy = false)

  val aTroll = Troll("Zaxabar", "Harft", 205, None, Gender.Male,
    List(mountain1, mountain2, mountain3))
}