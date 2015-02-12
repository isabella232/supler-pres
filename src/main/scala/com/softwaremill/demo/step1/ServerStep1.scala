package com.softwaremill.demo.step1

import akka.actor.ActorSystem
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

  startServer(interface = "localhost", port = 8080) {
    path("rest" / "form1.json") {
      getJson {
        complete {
          trollForm(troll).generateJSON
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