package bbst.registry

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}
import bbst.model.People

import scala.collection.immutable


object PeopleRegistry {

  // actor protocol
  sealed trait Command

  final case class PeopleList(peopleList: immutable.List[People])


  final case class GetPeople(name: String, replyTo: ActorRef[GetPeopleResponse]) extends Command

  final case class GetAllPeople(replyTo: ActorRef[PeopleList]) extends Command

  final case class CreatePeople(people: People, replyTo: ActorRef[ActionPerformed]) extends Command

  final case class DeletePeople(name: String, replyTo: ActorRef[ActionPerformed]) extends Command

  final case class GetPeopleResponse(maybePeople: Option[People])

  final case class ActionPerformed(description: String)

  def apply(): Behavior[Command] = registry(Set.empty)

  private def registry(peoples: Set[People]): Behavior[Command] =
    Behaviors.receiveMessage {
      case GetPeople(name, replyTo) =>
        replyTo ! GetPeopleResponse(peoples.find(_.name == name))
        Behaviors.same
      case CreatePeople(people, replyTo) =>
        replyTo ! ActionPerformed(s"people ${people.name} created.")
        registry(peoples + people)
      case GetAllPeople(replyTo) =>
        replyTo ! PeopleList(peoples.toList)
        Behaviors.same
      case DeletePeople(name, replyTo) =>
        replyTo ! ActionPerformed(s"people $name deleted.")
        registry(peoples.filterNot(_.name == name))
    }
}
