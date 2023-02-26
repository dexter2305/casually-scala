import io.getquill._
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
object PlayWithQuill extends App {

  val pgDataSource = new org.postgresql.ds.PGSimpleDataSource()
  pgDataSource.setUser("postgres")
  pgDataSource.setPassword("password")
  val config       = new HikariConfig()
  config.setDataSource(pgDataSource)
  val ctx          = new PostgresJdbcContext(LowerCase, new HikariDataSource(config))
  import ctx._

  case class City(id: Int, name: String, countryCode: String, district: String, population: Int)

  case class Country(
    code: String,
    name: String,
    continent: String,
    region: String,
    surfaceArea: Double,
    indepYear: Option[Int],
    population: Int,
    lifeExpectancy: Option[Double],
    gnp: Option[scala.math.BigDecimal],
    gnpold: Option[scala.math.BigDecimal],
    localName: String,
    governmentForm: String,
    headOfState: Option[String],
    capital: Option[Int],
    code2: String,
  )
  case class CountryLanguage(countrycode: String, language: String, isOfficial: Boolean, percentage: Double)

  val x: List[City] = ctx.run(query[City])

  

  println(x.size)

}
