import sbt._

object Dependencies {
  //scalatest with scalacheck integration
  lazy val scalatest     = "org.scalatest"     %% "scalatest"       % Version.ScalatestVersion
  lazy val scalatestplus = "org.scalatestplus" %% "scalacheck-1-15" % Version.ScalatestPlusVersion

  //pureconfig
  lazy val pureconfig = "com.github.pureconfig" %% "pureconfig" % Version.PureConfigVersion

  //slf4j
  lazy val slf4j_api       = "org.slf4j"      % "slf4j-api"       % Version.Slf4jApiVersion
  lazy val slf4j_scribe    = "com.outr"      %% "scribe-slf4j"    % Version.Slf4jScribeVersion
  lazy val logback_classic = "ch.qos.logback" % "logback-classic" % Version.LogbackClassicVersion

  // slick3
  lazy val slick           = "com.typesafe.slick" %% "slick"          % Version.SlickVersion
  lazy val slick_hikari_cp = "com.typesafe.slick" %% "slick-hikaricp" % Version.SlickVersion

  //postgres driver
  lazy val postgres = "org.postgresql" % "postgresql" % Version.PostgresDriverVersion

  //h2 driver
  lazy val h2 = "com.h2database" % "h2" % Version.H2DriverVersion

  lazy val jetty_server   = "org.eclipse.jetty" % "jetty-webapp"      % Version.JettyServerVersion
  lazy val servlet_api    = "javax.servlet"     % "javax.servlet-api" % Version.ServletApiVersion
  lazy val json4s_jackson = "org.json4s"       %% "json4s-jackson"    % Version.Json4sJacksonVersion

  lazy val scalatra      = "org.scalatra" %% "scalatra"      % Version.ScalatraVersion
  lazy val scalatra_json = "org.scalatra" %% "scalatra-json" % Version.ScalatraVersion

  lazy val http4s_dsl          = "org.http4s" %% "http4s-dsl"          % Version.Http4sVersion
  lazy val http4s_blaze_server = "org.http4s" %% "http4s-blaze-server" % Version.Http4sVersion
  lazy val http4s_circe        = "org.http4s" %% "http4s-circe"        % Version.Http4sVersion
  lazy val http4s_twirl        = "org.http4s" %% "http4s-twirl"        % Version.Http4sVersion

  lazy val cats_core   = "org.typelevel" %% "cats-core"   % Version.CatsCoreVersion
  lazy val cats_effect = "org.typelevel" %% "cats-effect" % Version.CatsEffectVersion

  lazy val fs2_core = "co.fs2" %% "fs2-core" % Version.FS2Version
  lazy val fs2_io   = "co.fs2" %% "fs2-io"   % Version.FS2Version

  lazy val circe_core    = "io.circe" %% "circe-core"    % Version.CirceVersion
  lazy val circe_generic = "io.circe" %% "circe-generic" % Version.CirceVersion
  lazy val circe_parser  = "io.circe" %% "circe-parser"  % Version.CirceVersion
  lazy val circe_literal = "io.circe" %% "circe-literal" % Version.CirceVersion

  lazy val tapir_core                     = "com.softwaremill.sttp.tapir" %% "tapir-core"              % Version.TapirVersion
  lazy val tapir_json_circe               = "com.softwaremill.sttp.tapir" %% "tapir-json-circe"        % Version.TapirVersion
  lazy val tapir_http4sServer_interpreter = "com.softwaremill.sttp.tapir" %% "tapir-http4s-server"     % Version.TapirVersion
  lazy val tapir_swagger_ui_bundle        = "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % Version.TapirVersion

  lazy val log4cats_core = "org.typelevel" %% "log4cats-core"  % Version.Log4catsVersion
  lazy val log4cats_slfj = "org.typelevel" %% "log4cats-slf4j" % Version.Log4catsVersion

  lazy val doobie_core   = "org.tpolecat" %% "doobie-core"     % Version.DoobieVersion
  lazy val doobie_hikari = "org.tpolecat" %% "doobie-hikari"   % Version.DoobieVersion
  lazy val doobie_pg     = "org.tpolecat" %% "doobie-postgres" % Version.DoobieVersion

  lazy val quill_jdbc  = "io.getquill" %% "quill-jdbc"  % Version.QuillVersion
  lazy val quill_async = "io.getquill" %% "quill-async" % Version.QuillVersion

  object Version {
    val SlickVersion          = "3.3.3"
    val CatsCoreVersion       = "2.7.0"
    val CatsEffectVersion     = "3.3.3"
    val FS2Version            = "3.2.4"
    val ScalatraVersion       = "2.7.0"
    val Http4sVersion         = "0.23.7"
    val TapirVersion          = "1.2.6"
    val CirceVersion          = "0.14.1"
    val Json4sJacksonVersion  = "3.5.5"
    val ServletApiVersion     = "3.1.0"
    val JettyServerVersion    = "9.4.44.v20210927"
    val H2DriverVersion       = "1.4.185"
    val PostgresDriverVersion = "42.3.1"
    val Slf4jApiVersion       = "1.7.32"
    val Slf4jScribeVersion    = "3.0.2"
    val PureConfigVersion     = "0.17.1"
    val ScalatestVersion      = "3.2.9"
    val ScalatestPlusVersion  = "3.2.9.0"
    val Log4catsVersion       = "2.5.0"
    val LogbackClassicVersion = "1.2.10"
    val DoobieVersion         = "1.0.0-RC1"
    val QuillVersion          = "3.12.0"
  }
}
