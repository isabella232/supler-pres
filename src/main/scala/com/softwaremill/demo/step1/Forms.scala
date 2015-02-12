package com.softwaremill.demo.step1

import org.supler.Supler._

object Forms {
  val heightField = field[Troll, Int](_.height).label("Height (cm)")

  val trollForm = form[Troll](f => List(
    f.field(_.firstName).label("First name"),
    heightField,
    f.field(_.bio).label("Bio"),
    f.field(_.gender).label("Gender").possibleValues(_ => List("male", "female"))
  ))
}