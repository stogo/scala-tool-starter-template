# Scala project template

Sbt template project for creating simple scala console applications.

### Dev & Deploy

This project supports "profiles".
Profiles are used to select configurations from `./conf/env/<profile>/` folder. 

To Compile with specific profile, use jvm parameter `env` like:

 ```
 sbt -Denv=dev
 sbt -Denv=test
 ```
 
Create package for deployment with profile key 
 ```
 sbt -Denv=stage universal:packageBin
 sbt -Denv=prod universal:packageBin
 ```

Or you can define System env variable: `BUILD_ENV`

Profile `Development` is in use by default if profile info is not passed. 

Profiles are defined in
[./project/Profiles.scala]()