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

lazy val `udemy-advanced-scala` = (project in file("udemy-advanced-scala"))
  .settings(
    name := "udemy-advanced-scala",
    libraryDependencies ++= Seq(
    ),
  )

lazy val `hello-http4s` = (project in file("hello-http4s"))
  .settings(
    name := "hello-http4s",
    libraryDependencies ++= Seq(
      http4s_dsl withJavadoc,
      http4s_blaze_server withJavadoc,
      http4s_circe,
      cats_effect withJavadoc,
      slf4j_scribe,
      circe_generic withJavadoc,
      circe_literal withJavadoc,
      scalatest,
      scalatestplus,
    ),
  )

lazy val `hello-cats` = (project in file("hello-cats"))
  .settings(
    name := "hello-cats",
    libraryDependencies ++= Seq(
      cats_effect withJavadoc,
    ),
  )

lazy val `hello-http4s-twirl` = (project in file("hello-https-twirl"))
  .settings(
    name := "hello-http4s-twirl",
    libraryDependencies ++= Seq(
      http4s_blaze_server,
      http4s_dsl withJavadoc,
      http4s_twirl withJavadoc,
      http4s_circe withJavadoc,
      circe_generic withJavadoc,
      circe_literal withJavadoc,
      fs2_core withJavadoc,
      slf4j_scribe % Runtime,
    ),
  )
  .enablePlugins(SbtTwirl)

lazy val challenges = (project in file("challenges"))
  .settings(
    name := "challenges",
    libraryDependencies ++= Seq(
      scalatest     % Test withJavadoc,
      scalatestplus % Test withJavadoc,
    ),
  )

lazy val `hello-fs2` = (project in file("hello-fs2"))
  .settings(
    name := "hello-fs2",
    libraryDependencies ++= Seq(
      fs2_core withJavadoc,
      fs2_io withJavadoc,
      cats_effect withJavadoc,
    ),
  )

lazy val `udemy-scala-essentials` = (project in file("udemy-scala-essentials"))
  .settings(
    name := "udemy-scala-essentials",
  ) 