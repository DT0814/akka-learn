package bbst.util

import bbst.model.People
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

object JsonFormats {

  import DefaultJsonProtocol._

  implicit val peopleJsonFormat: RootJsonFormat[People] = jsonFormat2(People)

}
