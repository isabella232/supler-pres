package com.softwaremill.demo.complete

import akka.actor.ActorSystem
import org.json4s.JsonAST.{JString, JValue}
import org.supler.Supler
import org.supler.field.ActionResult
import spray.http.MediaTypes
import spray.http.StatusCodes._
import spray.httpx.Json4sSupport
import spray.routing.{Route, SimpleRoutingApp}

object ServerComplete extends App with Json4sSupport with SimpleRoutingApp {

  implicit val actorSystem = ActorSystem()
  implicit val json4sFormats = org.json4s.DefaultFormats

  import com.softwaremill.demo.complete.forms2._
  import com.softwaremill.demo.complete.Instances._

  var troll = aTroll

  val saveAction = Supler.action[Troll]("save") { t =>
    troll = t
    println(s"Persisted: $troll")
    ActionResult.custom(JString("Persisted: " + troll))
  }.label("Save").validateAll()

  val trollFormWithSave = trollForm + saveAction

  def getJson(route: Route) = get { respondWithMediaType(MediaTypes.`application/json`) { route } }

  startServer(interface = "localhost", port = 8080) {
    path("rest" / "form1.json") {
      getJson {
        complete {
          trollFormWithSave(troll).generateJSON
        }
      } ~
      post {
        entity(as[JValue]) { jvalue =>
          complete {
            trollFormWithSave(troll).process(jvalue).generateJSON
          }
        }
      }
    } ~
    pathPrefix("site") {
      getFromResourceDirectory("")
    } ~
    path("") {
      redirect("/site/complete.html", Found)
    }
  }

  println(s"Server starting... open http://localhost:8080")
}