package com.softwaremill.demo.step2

import org.supler.Supler._
import org.supler.field.ActionResult

object Forms {
  val mountainForm = form[Mountain](f => List(
    f.field(_.name).label("Name"),
    f.field(_.height).label("Height (meters)").validate(gt(0), le(8848)),
    f.selectOneField(_.range)(identity).possibleValues(_ => List("Norefjell", "Trollheimen", "Smiubelgen")).label("Range"),
    f.field(_.snowy).label("Snowy")
  ))

  val heightField = field[Troll, Int](_.height).label("label_troll_height").validate(ge(0), lt(1000))

  val trollForm = form[Troll](f => List(
    f.field(_.firstName).label("First name"),
    heightField,
    f.action("grow") { t => ActionResult(t.copy(height = t.height + 10)) }.label("Grow"),
    f.field(_.bio).label("Bio"),
    f.selectOneField(_.gender)(identity).possibleValues(_ => List("male", "female")).label("Gender").renderHint(asRadio()),
    f.subform(_.likesMountains, mountainForm).label("Likes mountains")
  ))
}