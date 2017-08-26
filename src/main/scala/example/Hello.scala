package example

import example.util.{Banner, Cron, Helpers}

// Main app
object Hello extends Banner with App {

  def greeting = "hello"

  new DbTest

  new CronTest
}


// Cron example
class CronTest extends Helpers with Cron {

  val cronStr = conf.getString("example.hello.cron")

  val cronJob = scheduleCronJob(cronStr, logger.info("cron acivated function"))

  logger.info(" **************************************** ")
  logger.info(" * Waiting two minutes until shutDown.. * ")
  logger.info(" **************************************** ")

  Thread.sleep(1000*120)
  system.terminate()
}

// Database query example
class DbTest {
  import scalikejdbc._
  import scalikejdbc.config._

  // DBs.setup/DBs.setupAll loads specified JDBC driver classes.
  DBs.setupAll()

  case class Member(id:Long, name:String)

  val members = DB readOnly { implicit session =>
    sql"select id, name from members".map(r=> Member(r.long(1),r.string(2))).list.apply()
  }

  println("Members:")
  members.foreach(println)

  DBs.closeAll()
}