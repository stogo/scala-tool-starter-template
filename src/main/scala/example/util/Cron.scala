package example.util

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.markatta.akron._

trait Cron {

  // Should be injected or implicit maybe?
  val system = ActorSystem("system")

  private val crontab = system.actorOf(CronTab.props, "crontab")

  def scheduleCronJob(cronStr : String, f: => Unit) : ActorRef = {
    val job = system.actorOf(Props(new CronJob(f)))
    crontab ! CronTab.Schedule(job, "tick", CronExpression(cronStr))
    job
  }

  private class CronJob(f: => Unit) extends Actor {
    def receive = { case _ => f }
  }

}