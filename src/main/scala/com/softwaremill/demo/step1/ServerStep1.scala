package com.softwaremill.demo.step1

import akka.actor.ActorSystem
import org.json4s.JValue
import org.json4s.JsonAST.JNothing
import org.supler.Supler
import org.supler.field.ActionResult
import spray.http.MediaTypes
import spray.http.StatusCodes._
import spray.httpx.Json4sSupport
import spray.routing.{Route, SimpleRoutingApp}

/**
 * - basic form definition
 * - getting the form
 * - displaying on the frontend
 * - basic validation
 */
object ServerStep1 extends App with Json4sSupport with SimpleRoutingApp {

  implicit val actorSystem = ActorSystem()
  implicit val json4sFormats = org.json4s.DefaultFormats

  import com.softwaremill.demo.step1.Forms._
  import com.softwaremill.demo.step1.Instances._

  var troll = aTroll

  def getJson(route: Route) = get { respondWithMediaType(MediaTypes.`application/json`) { route } }

  val saveAction = Supler.action[Troll]("save") { t =>
    println(s"Received: $t")
    ActionResult.custom(JNothing)
  }.label("Save").validateAll()

  val trollFormWithSave = trollForm + saveAction

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
      redirect("/site/index.html", Found)
    }
  }

  println(s"Server starting... open http://localhost:8080")
}