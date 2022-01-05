import Dependencies._

Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / scalaVersion     := "2.13.7"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "l8.io"
ThisBuild / organizationName := "l8"
ThisBuild / scalacOptions    := scalacCustomOptions

lazy val scalacCustomOptions = Seq(
  "-deprecation",
  //"-Ylog-classpath",
)


lazy val root = (project in file("."))
  .settings(
    name := "casually-scala",
  )
  .aggregate(helloslick, helloscalatra)

lazy val helloslick = (project in file("hello-slick"))
  .settings(
    name := "hello-slick3",
    libraryDependencies ++= Seq(
      slick3,
      slick_hikari_cp,
      postgres,
      h2,
      slf4j_api,
      slf4j_scribe,
    ),
  )

  lazy val helloscalatra = (project in file("hello-scalatra"))
  .settings(
    name := "hello-scalatra",
    libraryDependencies ++= Seq(
      slf4j_api,
      slf4j_scribe,
      servlet_api, 
      scalatra,
      scalatra_json,
      json4s_jackson,
      jetty_server % "compile;container",
    ),
  ).enablePlugins(JettyPlugin)

