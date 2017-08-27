import sbt._

object Dependencies {

  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.3"

  lazy val common = Seq(

    "com.typesafe"               %  "config"           % "1.3.1",
    "com.typesafe.scala-logging" %% "scala-logging"    % "3.7.2",
    "ch.qos.logback"             %  "logback-classic"  % "1.2.3",

    // Akka actors are usefull executing concurrent tasks
    "com.typesafe.akka" %% "akka-actor" % "2.5.4",

    // Akka cron tool
    "com.markatta" %% "akron" % "1.2",

    // json4s, fast and reliable json library (jackson wrapper).
    // "org.json4s" %% "json4s-jackson" % "3.5.3",

    // Play WS is a powerful HTTP Client library
    // "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.0.4"

    // Ascii art thing..
    "com.github.lalyos" % "jfiglet" % "0.0.8",
  )


  lazy val scalikeJdbc = Seq(
    "org.scalikejdbc"    %% "scalikejdbc"          % "3.0.2",
    "org.scalikejdbc"    %% "scalikejdbc-config"   % "3.0.2",
    "mysql"              %  "mysql-connector-java" % "5.1.16",
    "com.h2database"     %  "h2"                   % "1.4.+",  // For dev and tests
    "org.apache.commons" %  "commons-dbcp2"        % "2.1.1",
  )

}
