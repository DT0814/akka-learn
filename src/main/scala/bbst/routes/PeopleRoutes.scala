package bbst.routes


import akka.actor.typed.scaladsl.AskPattern._
import akka.actor.typed.{ActorRef, ActorSystem}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import bbst.registry.PeopleRegistry
import bbst.registry.PeopleRegistry.{GetPeople, GetPeopleResponse}

import scala.concurrent.Future


class PeopleRoutes(peopleRegistry: ActorRef[PeopleRegistry.Command])(implicit val system: ActorSystem[_]) {

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
  import bbst.util.JsonFormats._

  private implicit val timeout: Timeout = Timeout.create(system.settings.config.getDuration("my-app.routes.ask-timeout"))

  def getPeople(name: String): Future[GetPeopleResponse] =
    peopleRegistry.ask(GetPeople(name, _))

  val peopleRoutes: Route =
    pathPrefix("people") {
      concat(
        path(Segment) { name =>
          concat(
            get {
              rejectEmptyResponse {
                onSuccess(getPeople(name)) { response =>
                  complete(response.maybePeople)
                }
              }
            }
          )
        })
    }
}
