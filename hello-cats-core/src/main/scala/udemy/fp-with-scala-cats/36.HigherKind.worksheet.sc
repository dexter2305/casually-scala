import cats.Applicative

case class Account(id: Int)
trait Database[F[_]] {
  def find(id: Int): F[Account]
}
import scala.concurrent.Future
object LiveDatabaseFuture extends Database[Future] {
  override def find(id: Int): Future[Account] = ???
}
object LiveDB             extends Database[List]   {

  override def find(id: Int): List[Account] = ???

}
object LiveDatabaseOption extends Database[Option] {
  override def find(id: Int): Option[Account] = ???
}

def traverse[G[_]: Applicative, A, B, F[_]](fa: F[A])(f: => A => G[B]): G[F[B]]      = ???
def traverse[G[_], A, B, F[_]](f: => A => G[B])(implicit a: Applicative[G]): G[F[B]] = ???
// A = *
// B = *
// F = * -> *
// G = * -> *
// F[A] = *
// G[F[B]] = *
// Applicative = (* -> *) -> *
