package bbst

import akka.actor.{ActorSystem, Props}
import bbst.actor.{HelloActor, PrintActor}
import bbst.model.People

object Boot extends App {
  val system = ActorSystem("HelloSystem")

  val printActor = system.actorOf(Props[PrintActor], name = "printActor")
  val helloActor = system.actorOf(Props(classOf[HelloActor], printActor), name = "helloActor")

  helloActor ! People("zhangSan", "man")
  helloActor ! People("lisi", "woman")
  helloActor ! "unknown type message"
  Thread.sleep(500)
  system.terminate()
}
