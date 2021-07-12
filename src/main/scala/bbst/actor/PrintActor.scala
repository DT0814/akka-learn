package bbst.actor


import akka.actor.Actor
import org.slf4j.Logger

class PrintActor(logger: Logger) extends Actor {
  override def receive: Receive = {
    case message: Any =>
      logger.info(s"$message")
  }
}
