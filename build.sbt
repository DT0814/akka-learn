import sbt.Keys.libraryDependencies

name := "akka-learn"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies ++= {
  val akkaVersion = "2.6.15"
  val akkaHttpVersion = "10.2.4"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-http"                % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json"     % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion,
    "com.typesafe.akka" %% "akka-stream"              % akkaVersion,
    "org.slf4j" % "slf4j-api" % "1.7.30",
    "org.slf4j" % "slf4j-simple" % "1.7.30",

    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "org.scalatest" %% "scalatest" % "3.1.4" % Test,
    "org.scalatestplus" %% "mockito-3-4" % "3.2.9.0" % Test
  )
}
