package com.softwaremill.demo.step1

import org.supler.Supler._

object Forms {
  val trollForm = form[Troll](f => List(
    f.field(_.firstName).label("First name"),
    f.field(_.height).label("Height (cm)").validate(gt(100), le(299)),
    f.field(_.bio).label("Bio"),
    f.selectOneField(_.gender)(_.toString).possibleValues(_ => Gender.values.toList).label("Gender")
  ))
}