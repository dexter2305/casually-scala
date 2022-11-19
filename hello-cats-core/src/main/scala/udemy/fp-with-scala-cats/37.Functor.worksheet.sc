import cats.Functor
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

case class Secret[A](value: A) {
  override def toString: String = {
    val digest = MessageDigest.getInstance("SHA-1")
    val hash   = digest.digest(value.toString().getBytes(StandardCharsets.UTF_8))
    new String(hash, StandardCharsets.UTF_8)
  }
}

object Secret {

  implicit val functorSecret: Functor[Secret] = new Functor[Secret] {
    override def map[A, B](fa: Secret[A])(f: A => B): Secret[B] = Secret(f(fa.value))
  }
}

val secret    = Secret[String]("dexter")
val upperCase = Functor[Secret].map(secret)(_.toUpperCase())
upperCase.value

// exercise

val optionFunctor: Functor[Option] = new Functor[Option] {
  override def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa match {
    case None    => None
    case Some(a) => Some(f(a))
  }
}
val some1                          = Option(1)
val none                           = Option.empty[Int]

// test optionFunctor
optionFunctor.map(some1)(_ + 1) == Some(2)
optionFunctor.map(none)(_ + 1) == None

val listFunctor: Functor[List] = new Functor[List] {
  override def map[A, B](fa: List[A])(f: A => B): List[B] = fa match {
    case Nil          => Nil
    case head :: next => f(head) :: map(next)(f)
  }
}
// test listFunctor
listFunctor.map(List(1, 2, 3))(_ * 3) == List(3, 6, 9)
