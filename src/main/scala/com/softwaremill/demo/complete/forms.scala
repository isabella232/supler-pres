package com.softwaremill.demo.complete

import org.supler.Supler._
import org.supler.field.ActionResult

object Forms {
  val mountainForm = form[Mountain](f => List(
    f.field(_.name).label("label_mountain_name"),
    f.field(_.height).label("Height (meters)").validate(gt(0), le(8848)),
    f.field(_.range).label("Range").possibleValues(_ => List("Norefjell", "Trollheimen", "Smiubelgen")),
    f.field(_.snowy).label("Snowy")
  ))

  val heightField = field[Troll, Int](_.height).label("Height (cm)")

  val trollForm = form[Troll](f => List(
    f.field(_.firstName).label("First name"),
    heightField,
    f.action("grow") { t => ActionResult(t.copy(height = t.height + 10)) }.label("Grow"),
    f.field(_.bio).label("Bio"),
    f.field(_.gender).label("Gender").possibleValues(_ => List("male", "female")).renderHint(asRadio()),
    f.subform(_.likesMountains, mountainForm).label("Likes mountains")
  ))
}