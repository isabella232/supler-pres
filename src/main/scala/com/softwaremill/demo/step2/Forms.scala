package com.softwaremill.demo.step2

import org.supler.Message
import org.supler.Supler._
import org.supler.field.ActionResult

object Forms {
  val customValidator = custom[Troll, Int]((v, m) => v != 129, (v, m) => Message("Cannot be 129"))

  val heightField = field[Troll, Int](_.height).label("label_troll_height").validate(ge(0), lt(1000), customValidator)

  val trollForm = form[Troll](f => List(
    f.field(_.firstName).label("First name") || f.field(_.lastName).label("Last name"),
    heightField,
    f.action("grow") { t => ActionResult(t.copy(height = t.height + 10)) }.label("Grow"),
    f.field(_.bio).label("Bio"),
    f.selectOneField(_.gender)(_.toString).possibleValues(_ => Gender.values.toList).label("Gender").renderHint(asRadio())
  ))
}