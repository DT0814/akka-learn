package bbst.actor

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit, TestProbe}
import org.mockito.Mockito.{times, verify}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import org.slf4j.Logger
import org.mockito.Mockito._

class PrintActorTest()
  extends TestKit(ActorSystem("TestSystem"))
    with ImplicitSender
    with AnyWordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "An Print actor" must {

    "should print message" in {
      val logger: Logger = mock(classOf[Logger])
      val printActor = system.actorOf(Props(classOf[PrintActor],logger), name = "printActor")

      printActor ! "some message"

      verify(logger, times(1)).info("some message")
    }

  }
}
