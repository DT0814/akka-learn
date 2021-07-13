package bbst.registry

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}
import bbst.model.People


object PeopleRegistry {

  // actor protocol
  sealed trait Command

  final case class GetPeople(name: String, replyTo: ActorRef[GetPeopleResponse]) extends Command

  final case class GetPeopleResponse(maybePeople: Option[People])

  def apply(): Behavior[Command] = registry(Set.empty)

  private def registry(people: Set[People]): Behavior[Command] =
    Behaviors.receiveMessage {
      case GetPeople(name, replyTo) =>
        //        replyTo ! PeopleResponse(people.find(_.name == name))
        replyTo ! GetPeopleResponse(Option.apply[People](People(name,"man")))
        Behaviors.same
    }
}
