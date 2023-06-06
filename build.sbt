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
  "-deprecation"
  // "-Ypartial-unification"
)

lazy val customTestOptions = Seq(
  Tests.Argument(TestFrameworks.ScalaTest, "-oSD")
)

lazy val root = (project in file("."))
  .settings(
    name := "casually-scala"
  )
  .aggregate(`hello-slick`, `hello-scalatra`)

lazy val `hello-slick` = (project in file("hello-slick"))
  .settings(
    name := "hello-slick3",
    libraryDependencies ++= Seq(
      com.typesafe.slick,
      com.typesafe.`slick-hikari-cp`,
      org.postgresql.postgresql,
      com.h2database.h2,
      org.slf4j.`slf4j-api`,
      com.outr.`scribe-slf4j`
    )
  )

lazy val `hello-scalatra` = (project in file("hello-scalatra"))
  .settings(
    name := "hello-scalatra",
    libraryDependencies ++= Seq(
      org.slf4j.`slf4j-api`,
      com.outr.`scribe-slf4j`,
      javax.servlet.`javax.servlet-api`,
      org.scalatra.scalatra,
      org.scalatra.`scalatra-json`,
      org.json4s.`json4s-jackson`,
      org.eclipse.jetty.`jetty-webapp` % "compile;container"
    )
  )
  .enablePlugins(JettyPlugin)

lazy val `udemy-rtjvm-advanced-scala` = (project in file("udemy-rtjvm-advanced-scala"))
  .settings(
    name := "udemy-rtjvm-advanced-scala",
    libraryDependencies ++= Seq(
    )
  )

lazy val `hello-http4s` = (project in file("hello-http4s"))
  .settings(
    name := "hello-http4s",
    libraryDependencies ++= Seq(
      org.http4s.`http4s-dsl`,
      org.http4s.`http4s-blaze-server`,
      org.http4s.`http4s-circe`,
      org.typelevel.`cats-effect`,
      com.outr.`scribe-slf4j`,
      Dependencies.io.circe.`circle-generic`,
      Dependencies.io.circe.`circe-literal`
    ),
    libraryDependencies ++= Seq(
      org.scalatest.scalatest,
      org.scalatestplus.`scalacheck-1-17`
    ).map(_ % Test)
  )

lazy val `hello-cats-effect` = (project in file("hello-cats-effect"))
  .settings(
    name := "hello-cats-effect",
    libraryDependencies ++= Seq(
      org.typelevel.`cats-effect` withJavadoc
    )
  )

lazy val `hello-cats-core` = (project in file("hello-cats-core"))
  .settings(
    name := "hello-cats-core",
    libraryDependencies ++= Seq(
      org.typelevel.`cats-core` withJavadoc
    )
  )

lazy val `hello-http4s-twirl` = (project in file("hello-https-twirl"))
  .settings(
    name := "hello-http4s-twirl",
    libraryDependencies ++= Seq(
      org.http4s.`http4s-blaze-server`,
      org.http4s.`http4s-dsl`,
      org.http4s.`http4s-twirl`,
      org.http4s.`http4s-circe`,
      Dependencies.io.circe.`circle-generic`,
      Dependencies.io.circe.`circe-literal`,
      co.fs2.`fs2-core`,
      com.outr.`scribe-slf4j`
    )
  )
  .enablePlugins(SbtTwirl)

lazy val challenges = (project in file("challenges"))
  .settings(
    name := "challenges",
    libraryDependencies ++= Seq(
      org.scalatest.scalatest,
      org.scalatestplus.`scalacheck-1-17`
    ).map(_ % Test withJavadoc ())
  )

lazy val `hello-fs2`      = (project in file("hello-fs2"))
  .settings(
    name := "hello-fs2",
    libraryDependencies ++= Seq(
      co.fs2.`fs2-core`,
      co.fs2.`fs2-io`,
      org.typelevel.`cats-effect`
    )
  )
lazy val `hello-log4cats` = (project in file("hello-log4cats"))
  .settings(
    name := "hello-log4cats",
    libraryDependencies ++= Seq(
      org.typelevel.`cats-effect`,
      org.typelevel.`log4scats-slf4j`,
      ch.qos.logback.`logback-classic`
    )
  )

lazy val `hello-circe` = (project in file("hello-circe"))
  .settings(
    name := "hello-circe",
    libraryDependencies ++= Seq(
      Dependencies.io.circe.`circe-core`,
      Dependencies.io.circe.`circle-generic`,
      Dependencies.io.circe.`circe-parser`
    )
  )

lazy val `udemy-scala-essentials` = (project in file("udemy-scala-essentials"))
  .settings(
    name := "udemy-scala-essentials"
  )

lazy val `hello-doobie` = (project in file("hello-doobie"))
  .settings(
    name := "hello-doobie",
    libraryDependencies ++= Seq(
      org.tpolecat.`doobie-core`,
      org.tpolecat.`doobie-hikari`,
      org.tpolecat.`doobie-postgres`
    )
  )

lazy val `hello-tapir` = (project in file("hello-tapir"))
  .settings(
    libraryDependencies ++= Seq(
      org.typelevel.`cats-core`,
      org.typelevel.`cats-effect`,
      com.softwaremill.sttp.tapir.`tapir-core`,
      com.softwaremill.sttp.tapir.`tapir-http4s-server`,
      com.softwaremill.sttp.tapir.`tapir-json-circe`,
      com.softwaremill.sttp.tapir.`tapir-swagger-ui-bundle`,
      Dependencies.io.circe.`circle-generic`,
      org.http4s.`http4s-blaze-server`,
      ch.qos.logback.`logback-classic`
    )
  )

lazy val `hello-quill` = (project in file("hello-quill"))
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.io.getquill.`quill-jdbc`,
      org.postgresql.postgresql,
      ch.qos.logback.`logback-classic`
    )
  )

lazy val `hello-tagless-final` = (project in file("hello-tagless-final"))
  .settings(
    libraryDependencies ++= Seq(
      org.typelevel.`cats-core`,
      org.typelevel.`cats-effect`
    ),
    libraryDependencies ++= Seq(
      org.typelevel.`cats-effect-testing-scalatest`,
      org.scalatest.scalatest,
      org.scalatestplus.`scalacheck-1-17`,
      org.scalameta.munit,
      org.scalameta.`munit-scalacheck`,
      org.typelevel.`scalacheck-effect-munit`,
      org.typelevel.`munit-cats-effect-3`
    ).map(_ % Test withJavadoc ())
  )
