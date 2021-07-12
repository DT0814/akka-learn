package bbst.actor

import akka.actor.{Actor, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit, TestProbe}
import bbst.model.People
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class HelloActorTest()
  extends TestKit(ActorSystem("TestSystem"))
    with ImplicitSender
    with AnyWordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "An hello actor" must {

    "should send hello message to actor ref" in {
      val prob = TestProbe()
      val actorRef = system.actorOf(Props(new Actor() {
        override def receive: Receive = {
          case message: String =>
            prob.ref ! message
        }
      }), name = "mockActorForTest")
      val helloActor = system.actorOf(Props(classOf[HelloActor], actorRef), name = "helloActor")

      helloActor ! People("zhangSan", "man")

      prob.expectMsg("hello Mr.zhangSan")
    }

  }
}
