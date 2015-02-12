package com.softwaremill.demo.step2

import org.supler.Supler._
import org.supler.field.ActionResult

object Forms {
  val heightField = field[Troll, Int](_.height).label("label_troll_height").validate(ge(0), lt(1000))

  val trollForm = form[Troll](f => List(
    f.field(_.firstName).label("First name"),
    heightField,
    f.action("grow") { t => ActionResult(t.copy(height = t.height + 10)) }.label("Grow"),
    f.field(_.bio).label("Bio"),
    f.field(_.gender).label("Gender").possibleValues(_ => List("male", "female")).renderHint(asRadio())
  ))
}