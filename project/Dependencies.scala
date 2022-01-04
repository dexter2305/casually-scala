import sbt._

object Dependencies {
  //scalatest with scalacheck integration
  lazy val scalatest     = "org.scalatest"     %% "scalatest"       % "3.2.9"
  lazy val scalatestplus = "org.scalatestplus" %% "scalacheck-1-15" % "3.2.9.0"

  //zio2
  lazy val zio = "dev.zio" %% "zio" % "1.0.12"

  //pureconfig
  lazy val pureconfig = "com.github.pureconfig" %% "pureconfig" % "0.17.1"

  //slf4j
  lazy val slf4j_api    = "org.slf4j" % "slf4j-api"    % "1.7.32"
  lazy val slf4j_scribe = "com.outr" %% "scribe-slf4j" % "3.0.2"

  // slick3
  lazy val slick3          = "com.typesafe.slick" %% "slick"          % "3.3.3"
  lazy val slick_hikari_cp = "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3"

  //postgres driver
  lazy val postgres = "org.postgresql" % "postgresql" % "42.3.1"

  //h2 driver
  lazy val h2 = "com.h2database" % "h2" % "1.4.185"

  //scalatra and friends
  private lazy val scalatra_version = "2.7.0"
  lazy val jetty_server             = "org.eclipse.jetty" % "jetty-webapp"      % "9.4.44.v20210927"
  lazy val servlet_api              = "javax.servlet"     % "javax.servlet-api" % "3.1.0"
  lazy val scalatra                 = "org.scalatra"     %% "scalatra"          % s"$scalatra_version"
  lazy val scalatra_json            = "org.scalatra"     %% "scalatra-json"     % s"$scalatra_version"
  lazy val json4s_jackson           = "org.json4s"       %% "json4s-jackson"    % "3.5.5"
}
