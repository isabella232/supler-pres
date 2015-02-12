package com.softwaremill.demo.complete

case class Troll(firstName: String, height: Int, bio: Option[String],
  gender: String, likesMountains: List[Mountain])

case class Mountain(name: String, height: Int, range: String, snowy: Boolean)

object Instances {
  val mountain1 = Mountain("Gråfjell", 1468, "Norefjell", snowy = true)
  val mountain2 = Mountain("Trolla", 1850, "Trollheimen", snowy = true)
  val mountain3 = Mountain("Trolltinden", 2018, "Smiubelgen", snowy = false)

  val aTroll = Troll("Zaxabar", 205,None, "male",
    List(mountain1, mountain2, mountain3))
}