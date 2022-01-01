package v5

import slick.jdbc.JdbcBackend

trait SlickDbComponent {
  
  val db: JdbcBackend#DatabaseDef

}
