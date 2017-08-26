package example.util

import com.github.lalyos.jfiglet.FigletFont

/**
  * Created by tlehtonen on 23/03/2017.
  */
trait Banner extends Helpers {

  val banner = conf.getString("banner").split("\n")
    .map(FigletFont.convertOneLine("classpath:/slant.flf",_)).mkString("\n")

  logger.info("\n" + banner)

}
