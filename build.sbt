import Dependencies._

Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / scalaVersion     := "2.13.7"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "l8.io"
ThisBuild / organizationName := "l8"
ThisBuild / scalacOptions    := scalacCustomOptions

lazy val root = (project in file("."))
  .settings(
    name := "casually-scala",
    libraryDependencies ++= Seq(
      scalatest     % Test,
      scalatestplus % Test,
    ),
  )
  .aggregate(helloslick)

lazy val helloslick = (project in file("hello-slick"))
  .settings(
    name := "hello-slick3",
    libraryDependencies ++= Seq(
      scalatest     % Test,
      scalatestplus % Test,
      slick3,
      slick_hikari_cp,
      postgres,
      h2,
      slf4j_api,
      slf4j_scribe,
    ),
  )

val scalacCustomOptions = Seq(
  "-deprecation",
)
