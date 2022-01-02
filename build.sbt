name := "playdemo"
 
version := "1.0" 
      
lazy val `playdemo` = (project in file(".")).enablePlugins(PlayScala)

      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.13.5"

libraryDependencies ++= Seq( ehcache , ws , specs2 % Test , guice )

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  "com.h2database" % "h2" % "2.0.204",
  "mysql" % "mysql-connector-java" % "8.0.25"
)