package bbst.actor

import akka.actor.Actor

class PrintActor() extends Actor {
  override def receive: Receive = {
    case message: String =>
      println(s"$message")
  }
}
