import cats.syntax.applicative
import cats.Applicative
sealed trait Validated[+A]
case class Valid[A](a: A)                  extends Validated[A]
case class Invalid[A](errors: Seq[String]) extends Validated[A]

def validateName(name: String): Validated[String] = if (name.forall(_.isLetter)) Valid(name) else Invalid(List("Name must contain only alphabets"))

def validateAge(age: Int): Validated[Int] = if (age > 18) Valid(age) else Invalid(List("age must be greater than 18"))

case class Person(name: String, age: Int)

def validatePerson(p: Person): Validated[Person] = {
  (validateName(p.name), validateAge(p.age)) match {
    case (Valid(name), Valid(age))                 => Valid(p)
    case (Invalid(nameErrors), Invalid(ageErrors)) => Invalid(nameErrors ++ ageErrors)
    case (Invalid(nameErrors), _)                  => Invalid(nameErrors)
    case (_, Invalid(ageErrors))                   => Invalid(ageErrors)
  }
}

val p = Person("dexter123", 10)
validatePerson(p)
validatePerson(p.copy(name = "dexter"))
validatePerson(p.copy(age = 100))
validatePerson(p.copy(name = "dexter", age = 19))

def map2[A, B, C](va: Validated[A], vb: Validated[B])(f: (A, B) => C): Validated[C] = (va, vb) match {
  case (Valid(a), Valid(b))     => Valid(f(a, b))
  case (Invalid(a), Invalid(b)) => Invalid(a ++ b)
  case (Invalid(a), _)          => Invalid(a)
  case (_, Invalid(b))          => Invalid(b)
}

object Validated {

  implicit val applicativeValidated = new Applicative[Validated] {

    override def pure[A](a: A): Validated[A]                                     = Valid(a)
    override def ap[A, B](ff: Validated[A => B])(fa: Validated[A]): Validated[B] =
      // (ff, fa) match {
      //   case (Valid(f), Valid(a))       => Valid(f(a))
      //   case (Invalid(ea), Invalid(eb)) => Invalid(ea ++ eb)
      //   case (Invalid(e), _)            => Invalid(e)
      //   case (_, Invalid(e))            => Invalid(e)
      // }
      map2(ff, fa)((f, a) => f(a))

    def map2[A, B, C](va: Validated[A], vb: Validated[B], f: (A, B) => C): Validated[C] =
      (va, vb) match {
        case (Valid(a), Valid(b))     => Valid(f(a, b))
        case (Invalid(a), Invalid(b)) => Invalid(a ++ b)
        case (Invalid(a), _)          => Invalid(a)
        case (_, Invalid(b))          => Invalid(b)
      }
  }
}

val optionApplicative = new Applicative[Option] {

  override def ap[A, B](ff: Option[A => B])(fa: Option[A]): Option[B] =
    (ff, fa) match {
      case (Some(f), Some(a)) => Some(f(a))
      case _                  => None
    }

  override def pure[A](x: A): Option[A] = Some(x)

}
optionApplicative.pure[Int](10)
optionApplicative.map2(Some(1), Some(10))(_ * _)
optionApplicative.map2[String, String, String](None, None)(_ + _)

val listApplicative = new Applicative[List] {

  override def ap[A, B](ff: List[A => B])(fa: List[A]): List[B] =
    (ff, fa) match {
      case (a :: fa, f :: ff) => ???
      case _                  => Nil

    }

  override def pure[A](x: A): List[A] = List[A](x)

}
