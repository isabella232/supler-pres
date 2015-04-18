package com.softwaremill.demo.step3

import org.supler.Message
import org.supler.Supler._
import org.supler.field.ActionResult

object Forms {
  val customValidator = custom[Troll, Int]((v, t) => v != 129, (v, t) => Message("Cannot be 129"))

  def mountainForm(deleteAction: Mountain => ActionResult[Mountain]) = form[Mountain](f => List(
    f.field(_.name).label("Name"),
    f.field(_.height).label("Height (meters)").validate(gt(0), le(8848)),
    f.selectOneField(_.country)(_.toString).possibleValues(_ => Country.values.toList).label("Country"),
    f.selectOneField(_.range)(identity).possibleValues(m => Country.rangesForCountry(m.country)).label("Range"),
    f.field(_.snowy).label("Snowy"),
    f.action("delete")(deleteAction).label("Delete")
  ))

  val heightField = field[Troll, Int](_.height).label("label_troll_height").validate(ge(0), lt(1000), customValidator)

  val trollForm = form[Troll](f => List(
    f.field(_.firstName).label("First name") || f.field(_.lastName).label("Last name"),
    heightField,
    f.action("grow") { t => ActionResult(t.copy(height = t.height + 10)) }.label("Grow"),
    f.field(_.bio).label("Bio"),
    f.selectOneField(_.gender)(_.toString).possibleValues(_ => Gender.values.toList).label("Gender").renderHint(asRadio()),
    f.subform(_.likesMountains, mountainForm(f.parentAction((t, index, m) => ActionResult(deleteMountain(t, m)))))
      .renderHint(asTable())
      .label("Likes mountains"),
    f.action("addmountain")(t => ActionResult(t.copy(likesMountains = t.likesMountains :+ Mountain.empty)))
      .label("Add mountain")
  ))

  def deleteMountain(t: Troll, m: Mountain): Troll = t.copy(likesMountains = t.likesMountains diff List(m))
}