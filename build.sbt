import Dependencies._

Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / scalaVersion       := "2.13.7"
ThisBuild / version            := "0.1.0"
ThisBuild / organization       := "l8.io"
ThisBuild / organizationName   := "l8"
ThisBuild / scalacOptions      := customScalacOptions
ThisBuild / testOptions        := customTestOptions
ThisBuild / watchBeforeCommand := Watch.clearScreen

lazy val customScalacOptions = Seq(
  "-deprecation",
  //"-Ypartial-unification"
)

lazy val customTestOptions = Seq(
  Tests.Argument(TestFrameworks.ScalaTest, "-oSD"),
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
      slick,
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

lazy val `udemy-rtjvm-advanced-scala` = (project in file("udemy-rtjvm-advanced-scala"))
  .settings(
    name := "udemy-rtjvm-advanced-scala",
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

lazy val `hello-cats-effect` = (project in file("hello-cats-effect"))
  .settings(
    name := "hello-cats-effect",
    libraryDependencies ++= Seq(
      cats_effect withJavadoc,
    ),
  )

lazy val `hello-cats-core` = (project in file("hello-cats-core"))
  .settings(
    name := "hello-cats-core",
    libraryDependencies ++= Seq(
      cats_core withJavadoc,
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

lazy val `hello-fs2`      = (project in file("hello-fs2"))
  .settings(
    name := "hello-fs2",
    libraryDependencies ++= Seq(
      fs2_core withJavadoc,
      fs2_io withJavadoc,
      cats_effect withJavadoc,
    ),
  )
lazy val `hello-log4cats` = (project in file("hello-log4cats"))
  .settings(
    name := "hello-log4cats",
    libraryDependencies ++= Seq(
      cats_effect,
      log4cats_slfj withJavadoc,
      logback_classic,
    ),
  )

lazy val `hello-circe` = (project in file("hello-circe"))
  .settings(
    name := "hello-circe",
    libraryDependencies ++= Seq(
      circe_core withJavadoc(),
      circe_generic withJavadoc(),
      circe_parser withJavadoc,
    ),
  )

lazy val `udemy-scala-essentials` = (project in file("udemy-scala-essentials"))
  .settings(
    name := "udemy-scala-essentials",
  )
