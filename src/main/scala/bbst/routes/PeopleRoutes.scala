package bbst.routes


import akka.actor.typed.scaladsl.AskPattern._
import akka.actor.typed.{ActorRef, ActorSystem}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import bbst.model.People
import bbst.registry.PeopleRegistry
import bbst.registry.PeopleRegistry._

import scala.concurrent.Future


class PeopleRoutes(peopleRegistry: ActorRef[PeopleRegistry.Command])(implicit val system: ActorSystem[_]) {

  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
  import bbst.util.JsonFormats._

  private implicit val timeout: Timeout = Timeout.create(system.settings.config.getDuration("my-app.routes.ask-timeout"))

  def getPeople(name: String): Future[GetPeopleResponse] =
    peopleRegistry.ask(GetPeople(name, _))

  def getAllPeople: Future[PeopleList] =
    peopleRegistry.ask(GetAllPeople)

  def createPeople(people: People): Future[ActionPerformed] =
    peopleRegistry.ask(CreatePeople(people, _))

  def deletePeople(name: String): Future[ActionPerformed] =
    peopleRegistry.ask(DeletePeople(name, _))

  val peopleRoutes: Route =
    pathPrefix("people") {
      concat(
        //#users-get-delete
        pathEnd {
          concat(
            get {
              complete(getAllPeople)
            },
            post {
              entity(as[People]) { people =>
                onSuccess(createPeople(people)) { performed =>
                  complete((StatusCodes.Created, performed))
                }
              }
            })
        },
        path(Segment) { name =>
          concat(
            get {
              rejectEmptyResponse {
                onSuccess(getPeople(name)) { response =>
                  complete(response.maybePeople)
                }
              }
            },
            delete {
              //#users-delete-logic
              onSuccess(deletePeople(name)) { performed =>
                complete((StatusCodes.OK, performed))
              }
              //#users-delete-logic
            })
        })
    }
}
