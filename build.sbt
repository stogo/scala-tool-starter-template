import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies += scalaTest % Test,
    libraryDependencies ++= common,
    libraryDependencies ++= scalikeJdbc,
    unmanagedResourceDirectories in Compile += baseDirectory.value / "conf" / "base",
    ProfilesPlugin.profileInPackageNameSettings
  )
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(ClasspathJarPlugin)
