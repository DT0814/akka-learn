package bbst


import akka.actor.{ActorSystem, Props}
import bbst.actor.{HelloActor, PrintActor}
import bbst.model.People
import org.slf4j.{Logger, LoggerFactory}

object Boot extends App {
  val system = ActorSystem("HelloSystem")
  val logger: Logger = LoggerFactory.getLogger(classOf[PrintActor])
  val printActor = system.actorOf(Props(classOf[PrintActor], logger), name = "printActor")
  val helloActor = system.actorOf(Props(classOf[HelloActor], printActor), name = "helloActor")

  helloActor ! People("zhangSan", "man")
  helloActor ! People("lisi", "woman")
  helloActor ! "unknown type message"
  Thread.sleep(500)
  system.terminate()
}
