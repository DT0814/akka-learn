package bbst

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

case class People(name: String, sex: String)

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

class PrintActor() extends Actor {
  override def receive: Receive = {
    case message: String =>
      println(s"$message")
  }
}

object Boot extends App {
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val printActor = system.actorOf(Props[PrintActor], name = "printActor")
  val helloActor = system.actorOf(Props(classOf[HelloActor], printActor), name = "helloActor")

  helloActor ! People("zhangSan", "man")
  helloActor ! People("lisi", "woman")
  helloActor ! "unknown type message"
  Thread.sleep(500)
  system.terminate()
}
