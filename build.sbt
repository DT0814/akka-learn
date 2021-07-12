name := "akka-learn"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies ++= {
  val akkaVersion = "2.6.13"
  Seq(
    "com.typesafe.akka" %% "akka-actor"      % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core"  % "10.2.4",
    "com.typesafe.akka" %% "akka-http"  % "10.2.4",
  )
}
