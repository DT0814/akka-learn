package bbst.util

import akka.serialization.Serialization
import bbst.model.People
import bbst.registry.PeopleRegistry.{ActionPerformed, PeopleList}
import spray.json.{DefaultJsonProtocol, JsArray, JsValue, JsonFormat, JsonReader, RootJsonFormat}

object JsonFormats {

  import DefaultJsonProtocol._

  implicit val peopleJsonFormat: RootJsonFormat[People] = jsonFormat2(People)
  implicit val peopleListJsonFormat: RootJsonFormat[PeopleList] = jsonFormat1(PeopleList)

  implicit val actionPerformedJsonFormat: RootJsonFormat[ActionPerformed] = jsonFormat1(ActionPerformed)

}
