package domain {
  case class User(id: Long, name: String, email: String)
}

package messages {
  trait ApplicationError {
    def message: String
  }
  case class InvalidEmailId(email: String) extends ApplicationError {
    override def message: String = s"Email id $email not found."

  }
}

package algebra {

  import domain._

  trait UserRepository[F[_]] {
    def insert(user: User): F[Long]
    def findByEmail(email: String): F[Either[messages.ApplicationError, User]]
  }
}

package service {

  import domain._

  class UserService[F[_]](repo: algebra.UserRepository[F]) {

    def create(user: User): F[Long] = repo.insert(user)
    def findByEmail(email: String)  = repo.findByEmail(email)
  }
}

package interperter {

  package quill {

    package object schema {
      import io.getquill._
      lazy val context = new SqliteJdbcContext(LowerCase, "ctx")

    }

    class UserRepository[F[_]] private extends algebra.UserRepository[F] {

      import schema.context._

      private lazy val users = quote {
        querySchema[domain.User]("users")
      }
      override def insert(user: domain.User): F[Long] = {

        schema.context.run(
          quote(
            users.insert(lift(user)),
          ).returningGenerated(_.id),
        )
        ???
      }

      override def findByEmail(email: String): F[Either[messages.ApplicationError, domain.User]] = ???

    }

  }
}
