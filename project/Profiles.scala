
import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin

import com.typesafe.sbt.packager.Keys._
import com.typesafe.sbt.packager.universal.UniversalPlugin.autoImport.Universal
import com.typesafe.sbt.packager.MappingsHelper._


/** sets the build environment */
object ProfilesPlugin extends AutoPlugin {

  // make sure it triggers automatically
  override def trigger = AllRequirements
  override def requires = JvmPlugin

  object autoImport {
    object BuildEnv extends Enumeration {
      val Production, Stage, Test, Development = Value
    }

    val buildEnv = settingKey[BuildEnv.Value]("The current build environment")
  }
  import autoImport._

  override lazy val projectSettings: Seq[Setting[_]] = Seq(
    buildEnv := {
      sys.props.get("env")
        .orElse(sys.env.get("BUILD_ENV"))
        .flatMap {
          case "prod" => Some(BuildEnv.Production)
          case "test" => Some(BuildEnv.Test)
          case "stage" => Some(BuildEnv.Stage)
          case "dev" => Some(BuildEnv.Development)
          case _ => None
        }
        .getOrElse(BuildEnv.Development)
    },
    // give feed back
    onLoadMessage := {
      // depend on the old message as well
      val defaultMessage = onLoadMessage.value
      val env = buildEnv.value
      s"""|$defaultMessage
          |Running in build environment: $env""".stripMargin
    },
    unmanagedResourceDirectories in Compile += {
      val folder = buildEnv.value.toString.toLowerCase
      baseDirectory.value / "conf" /"env" / folder
    },
    mappings in Universal ++= directory(baseDirectory.value / "conf" / "base"),
    mappings in Universal ++= {
      val folder = buildEnv.value.toString.toLowerCase
      directory(baseDirectory.value / "conf" / "env" / folder).map(t => (t._1, t._2.replace(folder,"conf")))
    }
  )

  val profileInPackageNameSettings = Seq(
    packageName in Universal := {
      name.value + "-" + buildEnv.value.toString.toLowerCase + "-" + version.value
    },
    executableScriptName in Universal := {
      executableScriptName.value + "-" + buildEnv.value.toString.toLowerCase
    },
  )

}