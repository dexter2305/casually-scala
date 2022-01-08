import Dependencies._

Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / scalaVersion     := "2.13.7"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "l8.io"
ThisBuild / organizationName := "l8"
ThisBuild / scalacOptions    := scalacCustomOptions

lazy val scalacCustomOptions = Seq(
  "-deprecation",
  //"-Ypartial-unification"
  //"-Ylog-classpath",
)

lazy val root = (project in file("."))
  .settings(
    name := "casually-scala",
  )
  .aggregate(`hello-slick`, `hello-scalatra`)

lazy val `hello-slick` = (project in file("hello-slick"))
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

lazy val `hello-scalatra` = (project in file("hello-scalatra"))
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
  )
  .enablePlugins(JettyPlugin)

lazy val udemyAdvancedScala = (project in file("udemy-advanced-scala"))
  .settings(
    name := "udemy-advanced-scala",
    libraryDependencies ++= Seq(
    ),
  )

lazy val `hello-http4s` = (project in file("hello-http4s"))
  .settings(
    name := "hello-http4s",
    libraryDependencies ++= Seq(
      http4s_dsl withJavadoc(), 
      http4s_blaze_server withJavadoc() withSources(),
      http4s_circe,
      cats_effect withJavadoc(),
      slf4j_scribe,
      circe_generic withJavadoc(),
    ),
  )

lazy val `hello-cats-effect` = (project in file("hello-cats-effect"))
  .settings(
    name := "hello-cats-effect",
    libraryDependencies ++= Seq(
      cats_effect,
    )
  )


  