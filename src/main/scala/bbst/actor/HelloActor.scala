package bbst.actor

import akka.actor.{Actor, ActorRef}
import bbst.model.People

class HelloActor(outputActor: ActorRef) extends Actor {
  override def receive: Receive = {
    case message: People =>
      var res: String = null
      message.sex match {
        case "man" => res = s"hello Mr.${message.name}"
        case "woman" => res = s"hello Ms.${message.name}"
        case _ => res = s"hello ${message.name}"
      }
      outputActor ! res
    case unknown =>
      println(s"unknown message: $unknown")
  }
}
