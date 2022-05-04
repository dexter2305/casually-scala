def f[X](xs: List[X]): List[X] = {
  xs match {
    case Nil     => Nil
    case y :: ys => f(ys) ::: List(y)
  }
}

def g[X](xs: List[X], ys: List[X]): List[X] = {
  xs match {
    case Nil     => ys
    case z :: zs => g(zs, z :: ys)
  }
}

f(List(1, 2, 3))
g(List(1, 2, 3), List(4, 5, 6))
g(List(4, 5, 6), List(1, 2, 3))

f(List(1, 2, 3) ::: List(4, 5, 6))
f(List(4, 5, 6)) ::: f(List(1, 2, 3))
g(List(1, 2, 3), List())

def f[A](a: A, xs: List[A]): List[List[A]] = {
  xs match {
    case Nil     => (a :: Nil) :: Nil
    case y :: ys => (a :: xs) :: (f(a, ys).map(zs => (y :: zs)))
  }
}

println(f(1, List(2, 3, 4)))

List(1, 2) :: List(3) :: Nil
List(List(1, 2, 3), Nil, List(2)).map(_.length)

def f1(x: List[List[Int]]): List[Int] = for {
  y <- x;
  z <- y
} yield z + 1

def foldLeft[A, B](f: (A, B) => A, e: A, xs: List[B]): A = xs match {
  case Nil     => e
  case y :: ys => foldLeft(f, f(e, y), ys)
}
def foo(s: String, t: String)                            = "$" + s + "/" + t

foldLeft[String, String](foo, "", List("hello", "world", "for", "scala"))

def foo[A](xs: List[A]): List[A] = xs match {
  case Nil     => Nil
  case y :: ys => bar(ys)
}

def bar[A](xs: List[A]): List[A] = xs match {
  case Nil     => Nil
  case y :: ys => y :: foo(ys)
}

foo(List(1, 2, 3, 4, 5, 6, 7))

def foo13(xss: List[List[Int]]) = for (xs <- xss; if xs.length < 3; x <- xs.reverse) yield x

foo13(List(List(0, 1, 2), List(1, 2), List(), List(3, 4)))

List(1, 2) ::: List(3, 4)

def f[A, B](xs: List[(A, B)]): (List[A], List[B]) = xs match {

  case Nil     => (Nil, Nil)
  case y :: ys => {
    val ps = f(ys)
    (y._1 :: ps._1, y._2 :: ps._2)
  }
}

val l = List(
  (1, ('a', 6)),
  (2, ('b', 7)),
  (3, ('c', 8)),
  (4, ('d', 9)),
  (5, ('e', 10)),
)

f(l)

def f[A](xs: List[A], ys: List[A], zs: List[A]): List[A] = xs match {
  case Nil     => zs
  case u :: us => f(ys, us, u :: zs)
}
{
  f(List(1, 2, 3, 4, 5), List(6, 7, 8), Nil)
}
{
  def f(xs: List[Int], y: List[Int]) = {
    xs :: y
  }
}
