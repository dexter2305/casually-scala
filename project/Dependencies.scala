import sbt._

object Dependencies {

  object org {

    object scalameta {

      private val artefact     = moduleId("org.scalameta")
      private val munitVersion = "0.7.29"
      val munit                = artefact("munit")(munitVersion)
      val `munit-scalacheck`   = artefact("munit-scalacheck")(munitVersion)

    }

    object typelevel {

      private val artefact                = moduleId("org.typelevel")
      val `cats-core`                     = artefact("cats-core")("2.9.0")
      val `cats-effect`                   = artefact("cats-effect")("3.1.0")
      val `scalacheck-effect-munit`       = artefact("scalacheck-effect-munit")("1.0.4")
      val `munit-cats-effect-3`           = artefact("munit-cats-effect-3")("1.0.7")
      val `log4scats-core`                = artefact("log4cats-core")("2.5.0")
      val `log4scats-slf4j`               = artefact("log4cats-slf4j")("2.5.0")
      val `cats-effect-testing-scalatest` = artefact("cats-effect-testing-scalatest")("1.5.0")

    }
    object http4s {

      private val artefact      = moduleId("org.http4s")
      private val version       = "0.23.7"
      val `http4s-dsl`          = artefact("http4s-dsl")(version)
      val `http4s-blaze-server` = artefact("http4s-blaze-server")(version)
      val `http4s-circe`        = artefact("http4s-circe")(version)
      val `http4s-twirl`        = artefact("http4s-twirl")(version)

    }

    object tpolecat {

      private val artefact  = moduleId("org.tpolecat")
      private val version   = "1.0.0-RC1"
      val `doobie-core`     = artefact("doobie-core")(version)
      val `doobie-hikari`   = artefact("doobie-hikari")(version)
      val `doobie-postgres` = artefact("doobie-postgres")(version)

    }

    object scalatest     {
      val scalatest = "org.scalatest" %% "scalatest" % "3.2.16"
    }
    object scalatestplus {
      val `scalacheck-1-17` = "org.scalatestplus" %% "scalacheck-1-17" % "3.2.16.0"
    }

    object slf4j {
      val `slf4j-api` = "org.slf4j" % "slf4j-api" % "1.7.32"
    }

    object postgresql {
      val postgresql = "org.postgresql" %% "postgressql" % "42.3.1"
    }

    object eclipse {
      object jetty {
        val `jetty-webapp` = "org.eclipse.jetty" %% "jetty-webapp" % "9.4.44.v20210927"
      }
    }

    object json4s {
      val `json4s-jackson` = "org.json4s" %% "json4s-jackson" % "3.5.5"
    }

    object scalatra {

      private val artefact = moduleId("org.scalatra")
      private val version  = "2.7.0"
      val scalatra         = artefact("scalatra")(version)
      val `scalatra-json`  = artefact("scalatra-json")(version)

    }

  }

  object com {

    object softwaremill {
      object sttp {
        object tapir {

          private val artefact          = moduleId("com.softwaremill.sttp.tapir")
          private val version           = "1.2.6"
          val `tapir-core`              = artefact("tapir-core")(version)
          val `tapir-json-circe`        = artefact("tapir-json-circe")(version)
          val `tapir-http4s-server`     = artefact("tapir-http4s-server")(version)
          val `tapir-swagger-ui-bundle` = artefact("tapir-swagger-ui-bundle")(version)

        }
      }
    }
    object github       {
      object pureconfig {
        val pureconfig = "com.github.pureconfig" %% "pureconfig" % "0.17.1"
      }
    }
    object outr         {
      val `scribe-slf4j` = "com.outr" %% "scribe-slf4j" % "3.0.2"
    }

    object typesafe {

      private val artefact  = moduleId("com.typesafe.slick")
      private val version   = "3.3.3"
      val slick             = artefact("slick")(version)
      val `slick-hikari-cp` = artefact("slick")(version)

    }

    object h2database {
      val h2 = "com.h2database" % "h2" % "1.4.185"
    }

  }

  object ch {
    object qos {
      object logback {
        val `logback-classic` = "ch.qos.logback" % "logback-classic" % "1.2.10"
      }
    }
  }

  object javax {
    object servlet {
      val `javax.servlet-api` = "javax.servlet" %% "javax.servlet-api" % "3.1.0"
    }
  }

  object co {
    object fs2 {

      private val artefact = moduleId("co.fs2")
      private val version  = "3.2.4"
      val `fs2-core`       = artefact("fs2-core")(version)
      val `fs2-io`         = artefact("fs2-io")(version)

    }
  }

  object io {

    object circe {

      private val artefact = moduleId("io.circe")
      private val version  = "0.14.1"
      val `circe-core`     = artefact("circe-core")(version)
      val `circle-generic` = artefact("circe-generic")(version)
      val `circe-parser`   = artefact("circe-parser")(version)
      val `circe-literal`  = artefact("circe-literal")(version)

    }

    object getquill {

      private val artefact = moduleId("io.getquill")
      private val version  = "3.12.0"
      val `quill-jdbc`     = artefact("quill-jdbc")(version)
      val `quill-async`    = artefact("quill-async")(version)

    }

  }
  ///

  val moduleId: String => String => String => ModuleID = (g: String) => (a: String) => (v: String) => g %% a % v

  ///
}
