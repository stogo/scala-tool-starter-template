package example.util


import com.typesafe.config.{Config, ConfigFactory}
import com.typesafe.scalalogging.LazyLogging

//import java.text.SimpleDateFormat
//import org.json4s.DefaultFormats

trait Helpers extends LazyLogging {

  lazy val conf : Config = ConfigFactory.load()

//  implicit val ec = scala.concurrent.ExecutionContext.Implicits.global

//  implicit val formats = new DefaultFormats {
//    override def dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//  }

}
