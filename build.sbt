import sbt.Keys.libraryDependencies

name := "akka-learn"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies ++= {
  val akkaVersion = "2.6.13"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-core" % "10.2.4",
    "com.typesafe.akka" %% "akka-http" % "10.2.4",
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "org.scalatest" %% "scalatest" % "3.1.4" % Test,
    "org.slf4j" % "slf4j-api" % "1.7.30",
    "org.slf4j" % "slf4j-simple" % "1.7.30",
    "org.scalatestplus" %% "mockito-3-4" % "3.2.9.0" % Test
  )
}
